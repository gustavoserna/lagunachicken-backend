package com.digitaul.lagunachicken.utility;

import com.digitaul.lagunachicken.domain.dto.MailDTO;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailUtility {

    //@Value("${serlo.app.emailRemitente}")
    static String emailRemitente = "alertasflotilla@lagunachicken.mx";

    //@Value("${serlo.app.passwordRemitente}")
    //static String passwordRemitente = "Alef#123456#";
    static String passwordRemitente = "Afl.12345678.";

    //@Value("${serlo.app.emailDestinatario}")
    static String emailDestinatario = "sernagustavo3@gmail.com";

    public static void sendEmail (MailDTO mailDTO) {
        Properties props = new Properties();
        //props.put("mail.smtp.host", "mail.lagunachicken.mx");
        props.put("mail.smtp.host", "mail.lagunachicken.mx");
        props.put("mail.smtp.port", "26");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Autenticación del remitente
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailRemitente, passwordRemitente);
            }
        };

        // Creación de la sesión de correo
        Session session = Session.getInstance(props, auth);

        try {
            // Creación del mensaje
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(emailRemitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));
            mensaje.setSubject(mailDTO.getAsunto());
            mensaje.setText(mailDTO.getCuerpo());

            // Envío del mensaje
            Transport.send(mensaje);

            System.out.println("Correo electrónico enviado correctamente.");
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
        }
    }

}
