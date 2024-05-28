package dishes;

import dishes.database.Database;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;


public class Buying {
    private static final Log log = LogFactory.getLog(Buying.class);
    Database database;

    public Buying(Database database) {
        this.database = database;
    }


    private String titledish = "";
    private String fullname = "";
    private String toEmail = " ";
    String email;
    private String address = "";
    private int quantity = 0;

    public Buying() {
        this.titledish = titledish;
        this.fullname = fullname;
        this.toEmail = email;
        this.address = address;
        this.quantity = quantity;
    }

    public String getTitleDish() {
        return titledish;
    }

    public String getFullName() {
        return fullname;
    }

    public String getEmail() {
        return toEmail;
    }

    public String getAddress() {
        return address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTitleDish(String titledish) {
        this.titledish = titledish;
    }

    public void setFullName(String fullName) {
        this.fullname = fullName;
    }

    public void setEmail(String email) {
        this.toEmail = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Dish> getDishByNameFromDB(String name) throws Exception {
        return database.getDishByName(name);
    }

    public String addDish(Dish dish) throws Exception {
        if (database.addDish(dish)) {
            return "You add dish";
        } else {
            throw new Exception("the dish was not added");

        }
    }

    public void sendEmail(String titledish, String fullname, String toEmail, String address, int quantity) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dogss7655@gmail.com", "Dog7655))");
            }
        });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dogss7655@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            String subject = "Placing an order";
            String text = "Hello, " + fullname
                    + System.lineSeparator() + "Thanks for buying in CREAM SODA. You successfully bought this dish " + "" + titledish.toUpperCase()
                    + System.lineSeparator() + "I hope you enjoy your order.Enjoy your meal"
                    + System.lineSeparator() + "Recipient Address : " + address
                    + System.lineSeparator() + "Yours faithfully, Cream soda! ";
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Email didn't send");
        }
    }
}
