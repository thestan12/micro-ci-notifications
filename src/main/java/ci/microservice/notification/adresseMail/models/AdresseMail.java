package ci.microservice.notification.adresseMail.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "mails")


public class AdresseMail {


    @Id
    String idMail;
    @Setter
    String adresse;
    @Setter
    String password;
 /*   @Setter
    String name;
    @Setter
    String userName;
    @Setter
    String phone;*/


    public AdresseMail(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return this.adresse;
    }
}
