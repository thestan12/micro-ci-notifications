package com.notifiation.microservice.AdresseMail.services;


import com.notifiation.microservice.AdresseMail.dao.AdresseMailRepository;
import com.notifiation.microservice.AdresseMail.models.AdresseMail;
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
