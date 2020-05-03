package com.notifiation.microservice;

import com.notifiation.microservice.AdresseMail.dao.AdresseMailRepository;
import com.notifiation.microservice.AdresseMail.models.AdresseMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;


//@ComponentScan({"com.notifiation.microservice.utils"})
@Configuration
@SpringBootApplication
public class MicroserviceApplication {


    @Autowired
    AdresseMailRepository adresseMailRepository;
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }



    @Bean
    CommandLineRunner runner(AdresseMailRepository adresseMailRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                adresseMailRepository.deleteAll();
                adresseMailRepository.saveAll(
                        List.of(
                                new AdresseMail("mouna89@gmail.com","HICHRI Mouna","minimino","0769061956")
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
