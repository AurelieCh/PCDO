package fr.serveurregistremail;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServeurRegistreMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServeurRegistreMailApplication.class, args);
    }

}