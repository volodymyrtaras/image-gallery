package gallery.api;

import gallery.model.User;
import gallery.service.UserService;
import gallery.service.validation.EmailValidationService;
import gallery.session.SessionData;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;

    @Setter(onMethod = @__(@Autowired))
    private EmailValidationService emailValidationService;

    @PostMapping(value = "/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public User login(@RequestBody User user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new Exception("Email and password could not be empty.");
        }

        User actualUser = userService.findByEmail(email);

        if (!actualUser.getPassword().equals(password)) {
            throw new Exception("Wrong password for email " + email);
        }

        SessionData.currentUser = actualUser;

        return user;
    }

    @PostMapping(value = "/logout")
    @CrossOrigin(origins = "http://localhost:4200")
    public void logout() {

        SessionData.currentUser = null;
    }

    @GetMapping(value = "/user")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getCurrentUser() throws Exception {

        if (SessionData.currentUser != null) {
            return userService.findByEmail(SessionData.currentUser.getEmail());
        }
        return null;
    }

    @PostMapping(value = "/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public User register(@RequestBody User user) throws Exception {

        String email = user.getEmail();

        if (!StringUtils.isEmpty(email) && emailValidationService.isEmailAlreadyRegistered(email)) {
            throw new Exception("User with email " + email + " is already exist.");
        }

        return userService.save(user);
    }
}
