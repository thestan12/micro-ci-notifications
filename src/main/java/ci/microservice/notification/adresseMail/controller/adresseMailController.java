package ci.microservice.notification.adresseMail.controller;

import ci.microservice.notification.adresseMail.dao.AdresseMailRepository;
import ci.microservice.notification.adresseMail.models.AdresseMail;
import ci.microservice.notification.adresseMail.services.AdresseMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/notification")
public class adresseMailController {

    @Autowired
    AdresseMailService adresseMailService;

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/test")
    public String sayGreeting() {
        return "Hello Mouna";

    }

    //@CrossOrigin
    @PostMapping("/address")
    public AdresseMail addAdresseMail(@RequestBody AdresseMail mail) {
        return adresseMailService.addAdressesMail(mail);
    }

    //@CrossOrigin
    @GetMapping("/addresses")
    public List<AdresseMail> getAdresseMail() {
        return adresseMailService.getAdressesMail();
    }
}
