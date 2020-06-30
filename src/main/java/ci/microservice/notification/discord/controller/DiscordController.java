package ci.microservice.notification.discord.controller;

import ci.microservice.notification.adresseMail.models.AdresseMail;
import ci.microservice.notification.discord.models.DiscordRequest;
import ci.microservice.notification.discord.service.BotComponent;
import ci.microservice.notification.discord.service.DiscordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/discord")
public class DiscordController {

    @Autowired
    BotComponent botComponent;

    @GetMapping("/test")
    public String addDataTest() {
        return "o";
    }

    @GetMapping("/ttt")
    @ResponseBody
    public String getFoos(@RequestParam String buildId) {
        botComponent.notify(buildId);
        return "hi";
    }



}
