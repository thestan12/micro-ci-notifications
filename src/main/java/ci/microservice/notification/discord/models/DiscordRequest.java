package ci.microservice.notification.discord.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class DiscordRequest {

    @Id
    String idRequest;

 /*   @Setter
    String name;
    @Setter
    String userName;
    @Setter
    String phone;*/


    public DiscordRequest(String requestId) {
        this.idRequest = requestId;
    }

}


