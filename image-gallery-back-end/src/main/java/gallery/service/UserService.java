package gallery.service;

import gallery.model.User;

import java.util.List;

public interface UserService {

    User findByEmail(String email) throws Exception;

    User getCurrentUser();

    List<User> findAll();

    User save(User user);
}
