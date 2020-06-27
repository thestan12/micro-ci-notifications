package ci.microservice.notification.adresseMail.services;

import ci.microservice.notification.adresseMail.models.AdresseMail;

import java.util.List;


public interface AdresseMailService {

    List<AdresseMail> getAdressesMail();

    AdresseMail addAdressesMail(AdresseMail adresseMail);

}
