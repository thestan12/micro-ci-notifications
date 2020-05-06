package ci.microservice.notification.adresseMail.services;

import ci.microservice.notification.adresseMail.dao.AdresseMailRepository;
import ci.microservice.notification.adresseMail.models.AdresseMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdresseMailServiceImpl implements AdresseMailService {
    @Autowired
    AdresseMailRepository adresseMailRepository;

    @Override
    public AdresseMail addAdressesMail() {
        return null;
    }

    @Override
    public AdresseMail getAdressesMail() {
        return null;
    }


}
