
package skaleup.losMisReport.ExcelReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import skaleup.losMisReport.model.MisNames;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class MisExcelReport {

    @Autowired
    MisNames misNames;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${ugro.mis.folder}")
    String folderPath;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void sendMisEmail(String sub, String from, String[] to, String[] cc) throws MessagingException {
        LOGGER.info("We are initiating the message");
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        String todaysDate = dateformat.format(date);
        String nameOfFile = sub.substring(0, sub.length() - 15);
        String text = "Hi team,\n    Please Find the " + nameOfFile + " " + " report for the date "
                + todaysDate
                + "\n" + "\nThanks and regards," + "\n" + " UGRO Capital";
        helper.setFrom(from);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(nameOfFile);
        helper.setText(text);
        FileSystemResource fS = new
                FileSystemResource(new File(
                folderPath + sub));
        helper.addAttachment(fS.getFilename(), fS);
        mailSender.send(message);
        LOGGER.info("Message has been send");
        File f = new File(
                folderPath + sub);
        f.delete();
    }
}

