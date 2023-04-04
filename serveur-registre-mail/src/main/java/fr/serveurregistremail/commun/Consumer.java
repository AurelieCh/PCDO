package fr.serveurregistremail.commun;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class Consumer {
    @Autowired
    JavaMailSender javaMailSender;
    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void receive(Mail mail) throws MessagingException {
        System.out.println(" [x] Received email for'" + mail.getEmailDesti() + "' to '" + mail.getTypeMail().toString() + "'");
        //TODO Envois de mail
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(mail.getEmailDesti());
        message.setSubject(mail.getTypeMail().toString());
        switch (mail.getTypeMail()){
            case Prise_en_compte :
                helper.setText("Bonjour,\n Merci pour votre commande, elle a bien ete prise en compte," +
                        "\nVous aurez des nouvelles lors de l'avancement ! \n\n" +
                        "Merci encore :) \n" +
                        "PCDO. \n(desole on gere pas les accents)", true);
                javaMailSender.send(message);
                break;
            case Valide:
                helper.setText("Bonjour,\n Voici quelques nouvelles de votre commande," +
                        "\nElle a ete valide et ne devrait plus tarder a etre prepare! \n\n" +
                        "Merci encore :) \n" +
                        "PCDO. \n(desole on gere pas les accents)", true);
                javaMailSender.send(message);
                break;
            case Prepare:
                helper.setText("Bonjour,\n Voici quelques nouvelles de votre commande," +
                        "\nElle est maintenant prepare et ne devrait plus tarder a etre expedie! \n\n" +
                        "Merci encore :) \n" +
                        "PCDO. \n(desole on gere pas les accents)", true);
                javaMailSender.send(message);
                break;
            case Expedie :
                helper.setText("Bonjour,\n Voici quelques nouvelles de votre commande," +
                        "\nElle a ete expedie et ne devrait plus tarder à être livre! \n" +
                        "Vous devriez recevoir un mail du transporteur dans les plus bref delais.\n\n" +
                        "Merci encore :) \n" +
                        "PCDO. \n(desole on gere pas les accents)", true);
                javaMailSender.send(message);
                break;
            case Livre:
                helper.setText("Bonjour,\n Voici quelques nouvelles de votre commande," +
                        "\nSelon notre transporteur, elle a ete livre à votre domicile :) \n\n" +
                        "Merci encore :) \n" +
                        "PCDO. \n(desole on gere pas les accents)", true);
                javaMailSender.send(message);
                break;
            case Bienvenue:
                helper.setText("Eh bonjour,\n Merci de vous etre inscrit sur notre site," +
                        "Nous vous souhaitons un moment tout a fait sympathique.\n\n" +
                        "Merci encore :) \n" +
                        "PCDO. \n(desole on gere pas les accents)", true);
                javaMailSender.send(message);
                break;
            case Aurevoir:
                helper.setText("Bonjour,\n Nous sommes triste de vous voir partir," +
                        "Aurevoir, gros bisous :)" +
                        "PCDO. \n(desole on gere pas les accents)", true);
                javaMailSender.send(message);
                break;
        }
    }

}
