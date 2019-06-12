package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Locale;

@Service
public class TemplateEmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(ScenarioDataDTO scenarioDataDTO) {

    }

    /*
     * Send HTML mail with inline image
     */
    public String sendMailWithInline(
            String recipientName,
            String recipientEmail,
            MultipartFile image,
            Locale locale)
            throws MessagingException, IOException {
        //TODO yet to code this.
        /*this.emailService.sendMailWithInline(
                recipientName, recipientEmail, image.getName(),
                image.getBytes(), image.getContentType(), locale);*/
        return "redirect:sent.html";

    }
}
