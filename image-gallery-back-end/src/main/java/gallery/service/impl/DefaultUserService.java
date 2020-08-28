package gallery.service.impl;

import gallery.model.User;
import gallery.repository.UserRepository;
import gallery.service.UserService;
import gallery.session.SessionData;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ConstantConditions")
@Service
@Transactional
public class DefaultUserService implements UserService {

    @Setter(onMethod = @__(@Autowired))
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("User with email " + email + " is not exist.");
        }

        return user;
    }

    @Override
    public User getCurrentUser() {

        return userRepository.findByEmail(SessionData.currentUser.getEmail());
    }

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }
}
