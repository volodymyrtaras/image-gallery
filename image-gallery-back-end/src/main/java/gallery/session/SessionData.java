package gallery.session;

import gallery.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SessionData {

    public static User currentUser = null;
    public static Integer currentImageId = null;
}
