package ci.microservice.notification.discord.dao;

import ci.microservice.notification.discord.models.DiscordRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface RequestRepository extends MongoRepository<DiscordRequest, String> {


}