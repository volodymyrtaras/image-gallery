package gallery.service.validation.impl;

import gallery.model.User;
import gallery.service.UserService;
import gallery.service.validation.EmailValidationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultEmailValidationService implements EmailValidationService {

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;

    @Override
    public boolean isEmailAlreadyRegistered(String email) {

        boolean isEmailRegistered = false;
        List<User> users = userService.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                isEmailRegistered = true;
                break;
            }
        }

        return isEmailRegistered;
    }
}
