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
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(mail.getEmailDesti());
        message.setSubject(mail.getTypeMail().toString());
        switch (mail.getTypeMail()){
            case Prise_en_compte :
                helper.setText("Bonjour,<br><br> Merci pour votre commande n°" + mail.getCommande() + ", elle a bien été prise en compte," +
                        "<br>Vous aurez des nouvelles lors de l'avancement ! <br><br>" +
                        "Merci encore :) <br><br>" +
                        "PCDO.", true);
                javaMailSender.send(message);
                break;
            case Valide:
                helper.setText("Bonjour,<br><br> Voici quelques nouvelles de votre commande n°" + mail.getCommande() + "," +
                        "<br>Elle a été validé et ne devrait plus tarder à être prepare! <br><br>" +
                        "Merci encore :) <br><br>" +
                        "PCDO.", true);
                javaMailSender.send(message);
                break;
            case Prepare:
                helper.setText("Bonjour,<br><br> Voici quelques nouvelles de votre commande n°" + mail.getCommande() + "," +
                        "<br>Elle est maintenant prepare et ne devrait plus tarder à être expédié! <br><br>" +
                        "Merci encore :) <br><br>" +
                        "PCDO.", true);
                javaMailSender.send(message);
                break;
            case Expedie :
                helper.setText("Bonjour,<br><br> Voici quelques nouvelles de votre commande n°" + mail.getCommande() + "," +
                        "<br>Elle a été expédié et ne devrait plus tarder à être livré! <br>" +
                        "Vous devriez recevoir un mail du transporteur dans les plus bref délais.<br><br>" +
                        "Merci encore :) <br><br>" +
                        "PCDO.", true);
                javaMailSender.send(message);
                break;
            case Livre:
                helper.setText("Bonjour,<br><br> Voici quelques nouvelles de votre commande n°" + mail.getCommande() + "," +
                        "<br>Selon notre transporteur, elle a été livré à votre domicile :) <br><br>" +
                        "Merci encore :) <br><br>" +
                        "PCDO.", true);
                javaMailSender.send(message);
                break;
            case Bienvenue:
                helper.setText("Éh bonjour,<br><br> Merci de vous être inscrit sur notre site,<br>" +
                        "Nous vous souhaitons un moment tout a fait sympathique.<br><br>" +
                        "Merci encore :) <br><br>" +
                        "PCDO.", true);
                javaMailSender.send(message);
                break;
            case Aurevoir:
                helper.setText("Bonjour,<br><br> Nous sommes triste de vous voir partir," +
                        "Aurevoir, gros bisous :)<br><br>" +
                        "PCDO.", true);
                javaMailSender.send(message);
                break;
        }
    }

}
