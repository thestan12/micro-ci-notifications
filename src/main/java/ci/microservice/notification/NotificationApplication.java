package ci.microservice.notification;

import ci.microservice.notification.adresseMail.dao.AdresseMailRepository;
import ci.microservice.notification.adresseMail.models.AdresseMail;

import ci.microservice.notification.discord.service.DiscordService;
import ci.microservice.notification.discord.service.DiscordServiceImpl;
import ci.microservice.notification.job.Runner;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;


@SpringBootApplication
@Configuration
public class NotificationApplication {
    @Autowired
    AdresseMailRepository adresseMailRepository;

    public static void main(String[] args) {
        DiscordService service = new DiscordServiceImpl();
        service.discordConnexion();

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
