package ci.microservice.notification.utils;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class Sender {
    private final Logger logger = Logger.getLogger(Sender.class);

    @Value("${microservice.notification.subjectForBuildStatus}")
    private String emailSubjectForEstimatedNavs;


    @Value("${microservice.notification.mail.comming.from}")
    private String emailCommingFrom;

    private final String emailBodyForBuildStatus = "<font face=\"arial\">Please find in the mail the the build status.<br>" +
            "Should you have any question regarding this mail, thank you to contact <a href = \"mailto: msouissi2@myges.fr\">" +
            ".<br>Best regards,<br><br><strong>The micro-service notification team</strong></font>";
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * @throws MessagingException the Exception
     */
    public void sendMail(String emailTo, String fileName) throws MessagingException {

        logger.info("sending message to email address : " + emailTo + " in process ...");
        MimeMessage messenge = javaMailSender.createMimeMessage();
        MimeMessageHelper sendMail = new MimeMessageHelper(messenge, true, "UTF-8");
        sendMail.setTo(emailTo.split(";"));
        sendMail.setFrom(emailCommingFrom);
        sendMail.setSubject(emailSubjectForEstimatedNavs);
        sendMail.setText(emailBodyForBuildStatus, true);
        javaMailSender.send(messenge);
        logger.info("The message was sent succesfully to : " + emailTo);
    }
}
