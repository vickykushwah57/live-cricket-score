
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

class Mailer {

    static String from = System.getenv("MAIL_USERNAME");
    static String password = System.getenv("MAIL_PASSWORD");

    public static void send(String to, String sub, String msg) {
        // Setup mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // STARTTLS

        // Create session object
        Session session = Session.getInstance(props,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);

            // Send message
            Transport.send(message);
            System.out.println("message sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
