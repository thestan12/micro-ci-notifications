package ci.microservice.notification;

import ci.microservice.notification.adresseMail.dao.AdresseMailRepository;
import ci.microservice.notification.adresseMail.models.AdresseMail;
import ci.microservice.notification.job.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SpringBootApplication
@Configuration
public class NotificationApplication {
    @Autowired
    AdresseMailRepository adresseMailRepository;
    public static void main(String[] args)
    {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AdresseMailRepository adresseMailRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                adresseMailRepository.deleteAll();
                adresseMailRepository.saveAll(
                        List.of(
                                new AdresseMail("mouna89@gmail.com")
                        )
                );

            }
        };
    }

    @Bean
    public ApplicationRunner runner() {
        return new Runner();
    }
}
