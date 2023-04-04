package fr.serveurregistremail.commun;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    JavaMailSender javaMailSender;
    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void receive(Mail mail) {
        System.out.println(" [x] Received email for'" + mail.getEmailDesti() + "' to '" + mail.getTypeMail().toString() + "'");
        //TODO Envois de mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getEmailDesti());
        message.setFrom("nepasrepondre@pcdo.fr");
        message.setSubject(mail.getTypeMail().toString());
        switch (mail.getTypeMail()){
            case Prise_en_compte :
                message.setText("Bonjour,\n Merci pour votre commande, elle a bien été prise en compte," +
                        "\nVous aurez des nouvelles lors de l'avancement ! \n\n" +
                        "Merci encore :) \n" +
                        "PCDO.");
                javaMailSender.send(message);
                break;
            case Valide:
                message.setText("Bonjour,\n Voici quelques nouvelles de votre commande," +
                        "\nElle a été validé et ne devrait plus tarder à être préparé! \n\n" +
                        "Merci encore :) \n" +
                        "PCDO.");
                javaMailSender.send(message);
                break;
            case Prepare:
                message.setText("Bonjour,\n Voici quelques nouvelles de votre commande," +
                        "\nElle est maintenant préparé et ne devrait plus tarder à être expédié! \n\n" +
                        "Merci encore :) \n" +
                        "PCDO.");
                javaMailSender.send(message);
                break;
            case Expedie :
                message.setText("Bonjour,\n Voici quelques nouvelles de votre commande," +
                        "\nElle a été expédié et ne devrait plus tarder à être livré! \n" +
                        "Vous devriez recevoir un mail du transporteur dans les plus bref délais.\n\n" +
                        "Merci encore :) \n" +
                        "PCDO.");
                javaMailSender.send(message);
                break;
            case Livre:
                message.setText("Bonjour,\n Voici quelques nouvelles de votre commande," +
                        "\nSelon notre transporteur, elle a été livré à votre domicile :) \n\n" +
                        "Merci encore :) \n" +
                        "PCDO.");
                javaMailSender.send(message);
                break;
            case Bienvenue:
                message.setText("Bonjour,\n Merci de vous être inscrit sur notre site," +
                        "Nous vous souhaitons un agrébale moment.\n\n" +
                        "Merci encore :) \n" +
                        "PCDO.");
                javaMailSender.send(message);
                break;
            case Aurevoir:
                message.setText("Bonjour,\n Nous sommes désolé de vous voir partir," +
                        "À la prochaine :)" +
                        "PCDO.");
                javaMailSender.send(message);
                break;
        }
    }

}
