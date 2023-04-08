package fr.serveurregistremail.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class ConsumerConfig {

    @Bean
    public Queue autoDeleteQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    public Binding binding(DirectExchange direct,
                           Queue autoDeleteQueue) {
        return BindingBuilder.bind(autoDeleteQueue)
                .to(direct)
                .with("key.1");
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(25);

        //0773099444
        mailSender.setUsername("pcdosarl@gmail.com");
        mailSender.setPassword("ecdxasxttdkjifrf");

        //mailSender.setUsername("postmaster@sandbox851e555630e242c6850cb5ca1c8936d7.mailgun.org");
        //mailSender.setPassword("3c3230f6bca798f825c8c963ea5a6a43-81bd92f8-a44c0369");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //props.put("mail.smtps.ssl.trust", "smtp.mailgun.org");

        return mailSender;
    }
}

