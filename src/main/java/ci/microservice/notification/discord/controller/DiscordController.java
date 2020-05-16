package ci.microservice.notification.discord.controller;

import ci.microservice.notification.adresseMail.models.AdresseMail;
import ci.microservice.notification.adresseMail.services.AdresseMailService;
import ci.microservice.notification.discord.service.DiscordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/notification/discord")
public class DiscordController {

    @Autowired
    DiscordService discordService;

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/test")
    public String sayGreeting() {
        return "Hello Mouna";
    }


    @GetMapping("/build/add")
    @ResponseBody
    public String addBuild(@RequestParam String id) {
        DiscordService.notify(id);
        return "ID: " + id;
    }


}

