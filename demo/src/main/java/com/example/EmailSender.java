package com.example;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    Seats seat = new Seats();
    User customer = new User("", "", "", "", "");

    public void setSeats(Seats seats) {
        seat = seats;
    }

    public Seats getSeat() {
        return seat;
    }

    public void setUser(User user) {
        customer = user;
    }

    public User getUser() {
        return customer;
    }
    
    public static void sendEmail(String recipientEmail, Seats seat, User customer) {
        final String senderEmail = "JavaAirlines@outlook.com"; // email del mittente
        final String password = "VQ6cw2FV9wmMeAJ"; // password outlook

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Prenotazione Volo Java Airlines");
            message.setText("Ciao " + customer.getName() + " " + customer.getSurname() + ", ecco la tua prenotazione per il volo " + seat.getFlightCode() + ". Grazie per aver scelto JavaAirlines!");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendEmailWithAttachment(String recipientEmail, String attachmentPath, Seats seat, User customer) {
        final String senderEmail = "JavaAirlines@outlook.com"; 
        final String password = "VQ6cw2FV9wmMeAJ"; 

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com"); // Outlook SMTP server
        props.put("mail.smtp.port", "587"); // Outlook SMTP port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Prenotazione Volo Java Airlines");

            // Create MimeMultipart
            MimeMultipart multipart = new MimeMultipart();

            // Create text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Ciao " + customer.getName() + " " + customer.getSurname() + ", ecco la tua prenotazione per il volo " + seat.getFlightCode() + ". Grazie per aver scelto JavaAirlines!");

            // Create attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(attachmentPath); // Path to your attachment file

            // Add parts to multipart
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            // Set content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
