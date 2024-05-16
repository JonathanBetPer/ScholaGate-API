package me.scholagate.api.services;

import me.scholagate.api.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private Environment env;

    @Autowired
    private JavaMailSender emailSender;
    private final String RUTA_APP = "http://localhost:5173/passwd/";

    @Autowired
    public EmailService(JavaMailSender emailSender, Environment env) {
        this.emailSender = emailSender;
        this.env = env;
    }

    private void sendEmail(String mailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

    public void sendPasswordSetupEmail( Usuario usuario, String token) {
        sendEmail(
            usuario.getCorreo(),
            "Bienvenido " + usuario.getNombre() + " !",
            "Por favor, haz clic clic en el siguiente enlace para completar el registro en ScholaGate" +
                    "\n" + RUTA_APP+token +
                    "\n \n Recuerda que este enlace expirará en 2 horas. Si necesitas otro enlace, por favor, contacta con el administrador del sitio."
        );

    }

    public void sendAdminEmail(Usuario usuarioAdmin, String token) {

        sendEmail(
            usuarioAdmin.getCorreo(),
            "Bienvenido!",
            "Por favor, para terminar de configurar la app, accede al siguiente enlace haciendo clic: \n\n "
                + RUTA_APP+token +
                    "\n\n Este enlace caduca en 24 horas."
        );

    }

}