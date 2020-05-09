package ci.microservice.notification.adresseMail.dao;

import ci.microservice.notification.adresseMail.models.AdresseMail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AdresseMailRepository extends MongoRepository<AdresseMail, String> {


}