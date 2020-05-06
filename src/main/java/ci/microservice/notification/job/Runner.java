package ci.microservice.notification.job;

import ci.microservice.notification.utils.Sender;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

public class Runner implements ApplicationRunner {


    private final Logger logger = Logger.getLogger(Sender.class);

    @Value("${microservice.notification.build.status.to}")
    private String emailBuildStatusTo;


    @Resource
    private Sender sender;

    public void run(ApplicationArguments args) throws Exception {
        logger.info("The beginning to launch the job");
        exportDetailsOfBuild();
        logger.info("finish to launch the job");
    }

    /**
     * @throws MessagingException
     */
    public void exportDetailsOfBuild() throws MessagingException {
        logger.info("The beginning to export the build status");
        sender.sendMail(emailBuildStatusTo, "Build status");
        logger.info("Finish to export the build status");
    }

}
