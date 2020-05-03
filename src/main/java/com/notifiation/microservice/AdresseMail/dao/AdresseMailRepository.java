package com.notifiation.microservice.AdresseMail.dao;


import com.notifiation.microservice.AdresseMail.models.AdresseMail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseMailRepository extends MongoRepository<AdresseMail,String> {


}