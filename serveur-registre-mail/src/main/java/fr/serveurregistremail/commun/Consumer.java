package fr.serveurregistremail.commun;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void receive(Mail mail) {
        System.out.println(" [x] Received email for'" + mail.getEmailDesti() + "' to '" + mail.getTypeMail().toString() + "'");
        //TODO Envois de mail ici

    }

}
