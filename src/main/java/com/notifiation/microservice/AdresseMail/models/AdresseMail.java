package com.notifiation.microservice.AdresseMail.models;


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
    String name;
    @Setter
    String userName;
    @Setter
    String phone;


    public AdresseMail(String adresse, String name, String userName, String phone){
        this.adresse=adresse;
        this.name= name;
        this.userName=userName;
        this.phone=phone;
    }
}
