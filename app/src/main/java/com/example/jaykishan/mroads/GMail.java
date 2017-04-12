package com.example.jaykishan.mroads;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GMail {

    final String emailPort = "587";// gmail's smtp port
    final String smtpAuth = "true";
    final String starttls = "true";
    final String emailHost = "smtp.gmail.com";

    private String fromEmail="jayakishan100@gmail.com";
    private String fromPassword="52701021";
    private String toEmail;
    private String emailSubject="Mroads";
    private String emailBody="Message Drafted";

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;


    public GMail(
                 String toEmail) {
        this.toEmail = toEmail;

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.starttls.enable", starttls);
//        Log.i("GMail", "Mail server properties set.");
    }

    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);


        emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));

//            Log.i("GMail","toEmail: "+toEmail);
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail.toString()));

        emailMessage.setSubject(emailSubject);

        Multipart multipart = new MimeMultipart("related");

        BodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(emailBody, "text/html");
        multipart.addBodyPart(htmlPart);
        emailMessage.setContent(multipart);
        // for a html email


//        Log.i("GMail", "Email Message created.");
        return emailMessage;
    }

    public int sendEmail() throws AddressException, MessagingException {

        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);
//        Log.i("GMail","allrecipients: "+emailMessage.getAllRecipients().toString());
        try {
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        }
        catch (SendFailedException ex)
        {
            return 0;
        }

            transport.close();

//        Log.i("GMail", "Email sent successfully.");
        return 1;
    }

}