package br.iesb.cco.apipoo.service;

import br.iesb.cco.apipoo.dto.UserDTO;
import br.iesb.cco.apipoo.model.UserEntity;
import br.iesb.cco.apipoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Scope("singleton")
public class AuthService {

    public static final Pattern VALID_EMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private UserRepository repo;

    @Autowired
    private JavaMailSender mailSender;
    public boolean sendEmail(String toEmail,
                             String subject,
                             String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("asureteste123@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        try {
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String login(UserDTO user) {
        String email = user.getEmail();
        String pass = user.getPassword();

        for (UserEntity u : repo.findAll()) {
            if (u.getEmail().equals(email) && u.getPassword().equals(pass)) {
                return u.getToken();
            }
        }
        return null;
    }

    public List<UserDTO> getUsers(String name) {
        String filter = name != null ? name : "";
        Optional<List<UserEntity>> result = repo.findByNameContaining(filter);

        List<UserDTO> list = new ArrayList<>();

        if (result.isPresent()) {
            List<UserEntity> users = result.get();
            for (UserEntity u : users) {
                UserDTO dto = new UserDTO(u.getId(), u.getEmail(), u.getPassword());
                list.add(dto);
            }
        }

        return list;
    }

    public int signup(UserDTO user) {
        Matcher matcher = VALID_EMAIL.matcher(user.getEmail());
        if (!matcher.find()) {
            return 1;
        }

        if (user.getPassword().length() < 6) {
            return 2;
        }

        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        String token = UUID.randomUUID().toString();
        entity.setToken(token);

        repo.save(entity);

        return 0;
    }

    public int forgotPassword(UserDTO user) {
        Matcher matcher = VALID_EMAIL.matcher(user.getEmail());
        if (!matcher.find()) {
            return 1;
        }
        String forgot = "Recuperando senha";
        if (!sendEmail(user.getEmail(),forgot, UUID.randomUUID().toString())) {
            return 2;
        }
        return 0;
    }

}
