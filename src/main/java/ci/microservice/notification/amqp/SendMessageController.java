package ci.microservice.notification.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    private final RabbitTemplate rabbitTemplate;

    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_NAME,
                "mike.springmessages", message);
        return "We have sent a message! :" + message;
    }
}