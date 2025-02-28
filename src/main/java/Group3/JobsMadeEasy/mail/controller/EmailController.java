package Group3.JobsMadeEasy.mail.controller;


import Group3.JobsMadeEasy.mail.dao.IEmailDao;
import Group3.JobsMadeEasy.mail.model.EmailData;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @description: It will handle all the mail related request.
 */
@RestController
public class EmailController {

    private final IEmailDao emailDao;
    private final EmailData emailData;

    public EmailController(IEmailDao emailDao, EmailData emailData) {
        this.emailDao = emailDao;
        this.emailData = emailData;
    }

    /**
     *
     * @param mail
     * @return it will send mail using SMTP protocol
     * @throws MessagingException
     * @throws IOException
     */
    @PostMapping(value = "/send_mail/{mail}")
    public String sendEmail(@PathVariable String mail) throws MessagingException, IOException {
        emailData.setData("Congratulation!! You have been selected for interview. \n Please provide your " +
                "availability." + "\n\n\n\n\n" + "Thanks and Regards," + "\n" + "HR, Jobs Made Easy");
        emailDao.sendMail(emailData,mail);
        return "Email sent successfully";
    }
}
