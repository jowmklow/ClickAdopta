package local.gonzalez.clickadopta;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//clase que permitira la conexion a un mail publico, creado exclusivamente para esta aplicacion
public class MailIntentService extends IntentService {
    public static final String ACTION_SEND_MAIL = "Enviar un email";
    public static final String MAIL = "MAIL";
    public static final String MSG = "CONTENT";

    //implementamos variables estaticas, donde estas siempre seran las mismaas
    public MailIntentService() {
        super("MailIntentService");
    }

    public static void startActionSendMail(Context context, String mail, String entrada //asunto
    ) {
        //esto es el asunto del mensaje
        Intent intent = new Intent(context, MailIntentService.class);
        intent.setAction(ACTION_SEND_MAIL);
        intent.putExtra(MAIL, mail);
        intent.putExtra(MSG, entrada);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            //si el intent es tiene contenido esntonces
            final String action = intent.getAction();
            //introduce dentro del mensaje el siguiente contenido
            if (ACTION_SEND_MAIL.equals(action)) {
                final String subj = getString(R.string.msgRegisterBarSubject);
                final String msg = "<strong>Correo</strong> <br>" + intent.getStringExtra(MAIL) + "<br> <strong>Mensaje</strong> <br>" + intent.getStringExtra(MSG);
                handleActionSendMail(msg, subj);
                //envia el mensaje
            }
        }
    }

    private void handleActionSendMail(String msg, String subject) {
        final String emailFrom = "mybossboyss@gmail.com";
        final String passwordFrom = "joel1995";
        //direccion mail con su contraseña

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.googlemail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        //propiedades predefinidas con los sockets y puertos

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, passwordFrom);
                //recoge la autenticacion de la sesion, recoge dichos valores
            }
        });

        if (session != null) {
            MimeMessage mime = new MimeMessage(session);
            //ejecuta la sesion

            try { //ejecuta en la sesion un mensaje donde introduzca los valores del mai y contraseña
                mime.setFrom(new InternetAddress(emailFrom));
                mime.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(emailFrom));
                mime.setSubject(subject);
                mime.setSentDate(new Date());
                mime.setContent(msg, "text/html; charset=utf-8");
                Transport.send(mime);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}