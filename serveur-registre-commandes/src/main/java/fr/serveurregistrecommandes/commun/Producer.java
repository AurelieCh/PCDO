package fr.serveurregistrecommandes.commun;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    public void send(Mail mail) {
        template.convertAndSend(direct.getName(), "key.1", mail);
    }

}
