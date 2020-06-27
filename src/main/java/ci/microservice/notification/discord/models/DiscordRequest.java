package ci.microservice.notification.discord.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString
@Builder
@Document(collection = "discordrequest")
public class DiscordRequest {
    @Id
    String idRequest;

    public DiscordRequest(String idRequest) {
        this.idRequest = idRequest;
    }

 /*   @Setter
    String name;
    @Setter
    String userName;
    @Setter
    String phone;*/


    public String getidRequest(){
        return this.idRequest;
    }

}


