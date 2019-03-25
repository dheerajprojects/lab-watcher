package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;

@Service
public class EmailService {

    static Logger logger = LoggerFactory.getLogger(EmailService.class);

    // Recipient's email ID needs to be mentioned.
    public static String TO_ADDRESS = "DheerajKumar.Gopali@in.pega.com";
    // Sender's email ID needs to be mentioned
    public static String FROM_ADDRESS = "DheerajKumar.Gopali@in.pega.com";
    // Setup mail server
    public static String SMTP_SERVER = "EXINTOPEN.rpega.com";
    public static String subject = "testsubject";
    public static String body = "<h1> Hello </h1>";

    public static void sendEmail() {
        // Get system properties
        Properties properties = new Properties();
        // Setup mail server
        properties.setProperty("mail.smtp.host", SMTP_SERVER);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session); // Create a default MimeMessage object.
            message.setContent(message, "text/html");
            message.setFrom(new InternetAddress(FROM_ADDRESS));// Set From: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO_ADDRESS));// Set To: header field of the header.
            message.setSubject(subject);// Set Subject: header field
            message.setText(body);

            Transport.send(message);// Send message

            logger.info("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void sendEmail(ScenarioDataDTO scenarioDataDTO) {
        subject = scenarioDataDTO.getTestname() + " is varied on build : " + scenarioDataDTO.getLatestbuild();
        constructBody(scenarioDataDTO);
        sendApacheCommonsEmail();
    }

    public static void sendApacheCommonsEmail() {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(SMTP_SERVER);
            email.addTo(TO_ADDRESS);
            email.setFrom(FROM_ADDRESS);
            email.setSubject(subject);
            // set the html message
            email.setHtmlMsg(body);

            // set the alternative message
            email.setTextMsg("Your email client does not support HTML messages");

            // send the email
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void constructBody(ScenarioDataDTO scenarioDataDTO) {
        String body1 = "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" xmlns=\"http://www.w3.org/TR/REC-html40\">\n" +
                "<head>\n" +
                "    <meta http-equiv=Content-Type content=\"text/html; charset=us-ascii\">\n" +
                "    <meta name=Generator content=\"Microsoft Word 15 (filtered medium)\">\n" +
                "    <style>\n" +
                "        <!--\n" +
                "        /* Font Definitions */\n" +
                "        @font-face\n" +
                "        {font-family:\"Cambria Math\";\n" +
                "            panose-1:2 4 5 3 5 4 6 3 2 4;}\n" +
                "        @font-face\n" +
                "        {font-family:Calibri;\n" +
                "            panose-1:2 15 5 2 2 2 4 3 2 4;}\n" +
                "        /* Style Definitions */\n" +
                "        p.MsoNormal, li.MsoNormal, div.MsoNormal\n" +
                "        {margin:0cm;\n" +
                "            margin-bottom:.0001pt;\n" +
                "            font-size:11.0pt;\n" +
                "            font-family:\"Calibri\",sans-serif;\n" +
                "            mso-fareast-language:EN-US;}\n" +
                "        a:link, span.MsoHyperlink\n" +
                "        {mso-style-priority:99;\n" +
                "            color:#0563C1;\n" +
                "            text-decoration:underline;}\n" +
                "        a:visited, span.MsoHyperlinkFollowed\n" +
                "        {mso-style-priority:99;\n" +
                "            color:#954F72;\n" +
                "            text-decoration:underline;}\n" +
                "        span.EmailStyle17\n" +
                "        {mso-style-type:personal-compose;\n" +
                "            font-family:\"Calibri\",sans-serif;\n" +
                "            color:windowtext;}\n" +
                "        .MsoChpDefault\n" +
                "        {mso-style-type:export-only;\n" +
                "            font-family:\"Calibri\",sans-serif;\n" +
                "            mso-fareast-language:EN-US;}\n" +
                "        @page WordSection1\n" +
                "        {size:612.0pt 792.0pt;\n" +
                "            margin:72.0pt 72.0pt 72.0pt 72.0pt;}\n" +
                "        div.WordSection1\n" +
                "        {page:WordSection1;}\n" +
                "        -->\n" +
                "    </style>\n" +
                "    <!--[if gte mso 9]>\n" +
                "    <xml>\n" +
                "        <o:shapedefaults v:ext=\"edit\" spidmax=\"1026\" />\n" +
                "    </xml>\n" +
                "    <![endif]--><!--[if gte mso 9]>\n" +
                "    <xml>\n" +
                "        <o:shapelayout v:ext=\"edit\">\n" +
                "            <o:idmap v:ext=\"edit\" data=\"1\" />\n" +
                "        </o:shapelayout>\n" +
                "    </xml>\n" +
                "    <![endif]-->\n" +
                "</head>\n" +
                "<body lang=EN-IN link=\"#0563C1\" vlink=\"#954F72\">\n" +
                "<div class=WordSection1>\n" +
                "    <p class=MsoNormal>\n" +
                "        Hi Performance Team,\n" +
                "        <o:p></o:p>\n" +
                "    </p>\n" +
                "    <p class=MsoNormal>\n" +
                "            <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                "               <o:p>&nbsp;</o:p>\n" +
                "            </span>\n" +
                "    </p>\n" +
                "    <p class=MsoNormal>\n" +
                "            <span lang=EN-US style='mso-fareast-language:EN-IN'>\n";


        ParamDataDTO totalreqtimeData = scenarioDataDTO.getMap().get("totalreqtime");
        String body2 = "";
        if (totalreqtimeData != null) {
            body2 = "Lab watcher has noticed a <b>" + totalreqtimeData.getVariedBy() + "</b>% ";
            if (totalreqtimeData.isDegraded())
                body2 += "<b>degradation</b> ";
            if (totalreqtimeData.isImproved())
                body2 += "<b>improvement</b> ";
            body2 += " in <b>" + totalreqtimeData.getBuildLabel() + "</b> build w.r.t <b>totalreqtime </b>in <b>" + totalreqtimeData.getScenarioName() + " </b>scenario.\n";
        }

        String body3 = " <o:p></o:p>\n" +
                "            </span>\n" +
                "    </p>\n" +
                "    <p class=MsoNormal>\n" +
                "            <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                "               <o:p>&nbsp;</o:p>\n" +
                "            </span>\n" +
                "    </p>\n" +
                "    <p class=MsoNormal>\n" +
                "            <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                "               The following are the variations identified in individual parameters:\n" +
                "               <o:p></o:p>\n" +
                "            </span>\n" +
                "    </p>\n" +
                "    <p class=MsoNormal>\n" +
                "            <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                "               <o:p>&nbsp;</o:p>\n" +
                "            </span>\n" +
                "    </p>\n" +
                "    <table class=MsoTable15Grid4Accent1 border=1 cellspacing=0 cellpadding=0 style='border-collapse:collapse;border:none'>\n" +
                "        <tr>\n" +
                "            <td width=89 valign=top style='width:66.75pt;border:solid #4472C4 1.0pt;border-right:none;background:#4472C4;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                "                <p class=MsoNormal>\n" +
                "                    <b>\n" +
                "                        <span lang=EN-US style='color:white;mso-fareast-language:EN-IN'>\n" +
                "                           Parameter Name\n" +
                "                           <o:p></o:p>\n" +
                "                        </span>\n" +
                "                    </b>\n" +
                "                </p>\n" +
                "            </td>\n" +
                "            <td width=89 valign=top style='width:66.75pt;border-top:solid #4472C4 1.0pt;border-left:none;border-bottom:solid #4472C4 1.0pt;border-right:none;background:#4472C4;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                "                <p class=MsoNormal>\n" +
                "                    <b>\n" +
                "                        <span lang=EN-US style='color:white;mso-fareast-language:EN-IN'>\n" +
                "                           Last N valid records\n" +
                "                           <o:p></o:p>\n" +
                "                        </span>\n" +
                "                    </b>\n" +
                "                </p>\n" +
                "            </td>\n" +
                "            <td width=89 valign=top style='width:66.8pt;border-top:solid #4472C4 1.0pt;border-left:none;border-bottom:solid #4472C4 1.0pt;border-right:none;background:#4472C4;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                "                <p class=MsoNormal>\n" +
                "                    <b>\n" +
                "                        <span lang=EN-US style='color:white;mso-fareast-language:EN-IN'>\n" +
                "                           Mean\n" +
                "                           <o:p></o:p>\n" +
                "                        </span>\n" +
                "                    </b>\n" +
                "                </p>\n" +
                "            </td>\n" +
                "            <td width=89 valign=top style='width:66.8pt;border-top:solid #4472C4 1.0pt;border-left:none;border-bottom:solid #4472C4 1.0pt;border-right:none;background:#4472C4;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                "                <p class=MsoNormal>\n" +
                "                    <b>\n" +
                "                        <span lang=EN-US style='color:white;mso-fareast-language:EN-IN'>\n" +
                "                           Standard Deviation\n" +
                "                           <o:p></o:p>\n" +
                "                        </span>\n" +
                "                    </b>\n" +
                "                </p>\n" +
                "            </td>\n" +
                "            <td width=89 valign=top style='width:66.8pt;border-top:solid #4472C4 1.0pt;border-left:none;border-bottom:solid #4472C4 1.0pt;border-right:none;background:#4472C4;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                "                <p class=MsoNormal>\n" +
                "                    <b>\n" +
                "                        <span lang=EN-US style='color:white;mso-fareast-language:EN-IN'>\n" +
                "                           Param Value\n" +
                "                           <o:p></o:p>\n" +
                "                        </span>\n" +
                "                    </b>\n" +
                "                </p>\n" +
                "            </td>\n" +
                "            <td width=89 valign=top style='width:66.8pt;border-top:solid #4472C4 1.0pt;border-left:none;border-bottom:solid #4472C4 1.0pt;border-right:none;background:#4472C4;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                "                <p class=MsoNormal>\n" +
                "                    <b>\n" +
                "                        <span lang=EN-US style='color:white;mso-fareast-language:EN-IN'>\n" +
                "                           Variation %\n" +
                "                           <o:p></o:p>\n" +
                "                        </span>\n" +
                "                    </b>\n" +
                "                </p>\n" +
                "            </td>\n" +
                "            <td width=89 valign=top style='width:66.8pt;border:solid #4472C4 1.0pt;border-left:none;background:#4472C4;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                "                <p class=MsoNormal>\n" +
                "                    <b>\n" +
                "                        <span lang=EN-US style='color:white;mso-fareast-language:EN-IN'>\n" +
                "                           Status\n" +
                "                           <o:p></o:p>\n" +
                "                        </span>\n" +
                "                    </b>\n" +
                "                </p>\n" +
                "            </td>\n" +
                "        </tr>\n";

        String body4 = "";
        int row = 0;
        for (String param : scenarioDataDTO.getMap().keySet()) {
            ParamDataDTO paramDataDTO = scenarioDataDTO.getMap().get(param);
            String status;
            if (paramDataDTO.isDegraded())
                status = "<o:p style=\"color:red;\">Degraded</o:p>\n";
            else
                status = "<o:p style=\"color:blue;\">Improved</o:p>\n";

            body4 += "        <tr>\n";

            if (row % 2 == 0) {
                body4 += "            <td width=89 valign=top style='width:66.75pt;border:solid #8EAADB 1.0pt;border-top:none;background:#D9E2F3;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                    <b>\n" +
                        "                        <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                           <o:p>" + paramDataDTO.getParamName() + "</o:p>\n" +
                        "                        </span>\n" +
                        "                    </b>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.75pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;background:#D9E2F3;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getNoOfValidRecords() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;background:#D9E2F3;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getMean() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;background:#D9E2F3;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getStandardDeviation() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;background:#D9E2F3;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getParamValue() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;background:#D9E2F3;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getVariedBy() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;background:#D9E2F3;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        status+
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n";
            } else {
                body4 += "            <td width=89 valign=top style='width:66.75pt;border:solid #8EAADB 1.0pt;border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                    <b>\n" +
                        "                        <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                           <o:p>" + paramDataDTO.getParamName() + "</o:p>\n" +
                        "                        </span>\n" +
                        "                    </b>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.75pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getNoOfValidRecords() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getMean() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getStandardDeviation() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getParamValue() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        "                        <o:p>" + paramDataDTO.getVariedBy() + "</o:p>\n" +
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n" +
                        "            <td width=89 valign=top style='width:66.8pt;border-top:none;border-left:none;border-bottom:solid #8EAADB 1.0pt;border-right:solid #8EAADB 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>\n" +
                        "                <p class=MsoNormal>\n" +
                        "                     <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                        status+
                        "                     </span>\n" +
                        "                </p>\n" +
                        "            </td>\n";
            }
            body4 += "</tr>";
            row++;
        }

        String body5 = "    </table>\n" +
                "    <p class=MsoNormal>\n" +
                "            <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                "               <o:p>&nbsp;</o:p>\n" +
                "            </span>\n" +
                "    </p>\n" +
                "    <p class=MsoNormal>\n" +
                "            <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                "               Regards,\n" +
                "               <o:p></o:p>\n" +
                "            </span>\n" +
                "    </p>\n" +
                "    <p class=MsoNormal>\n" +
                "            <span lang=EN-US style='mso-fareast-language:EN-IN'>\n" +
                "               Excavators\n" +
                "               <o:p></o:p>\n" +
                "            </span>\n" +
                "    </p>\n" +
                "    <p class=MsoNormal>\n" +
                "        <o:p>&nbsp;</o:p>\n" +
                "    </p>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        body = body1 + body2 + body3 + body4 + body5;
    }
}
