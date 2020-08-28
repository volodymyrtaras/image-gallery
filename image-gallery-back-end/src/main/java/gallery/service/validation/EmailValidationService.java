package gallery.service.validation;

public interface EmailValidationService {

    boolean isEmailAlreadyRegistered(String email) throws Exception;
}
