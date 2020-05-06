package ci.microservice.notification.adresseMail.services;

import ci.microservice.notification.adresseMail.dao.AdresseMailRepository;
import ci.microservice.notification.adresseMail.models.AdresseMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdresseMailServiceImpl implements AdresseMailService {
    @Autowired
    AdresseMailRepository adresseMailRepository;


    @Override
    public List<AdresseMail> getAdressesMail() {

        return adresseMailRepository.findAll();
    }

    @Override
    public AdresseMail addAdressesMail(AdresseMail adresseMail) {
        AdresseMail adresse = new AdresseMail(adresseMail.getAdresse());
        adresse = adresseMailRepository.save(adresse);
        return adresse;
    }


}
