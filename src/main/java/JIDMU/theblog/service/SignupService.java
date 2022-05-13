package JIDMU.theblog.service;

import JIDMU.theblog.model.User;
import JIDMU.theblog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SignupService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public int getValidationStatus(User user, String passwordValidator){
        if (user.getFirstName().isBlank() || user.getFirstName().isEmpty()){
            return 101;
        }
        if (user.getLastName().isBlank() || user.getLastName().isEmpty()){
            return 102;
        }
        if (user.getUsername().isBlank() || user.getUsername().isEmpty()){
            return 103;
        }
        if (user.getPassword().isBlank() || user.getPassword().isEmpty()){
            return 104;
        }
        if (!passwordValidator.equals(user.getPassword())){
            return 105;
        }
        return 1;
    }

    public String createUser(User user, String passwordValidator) {
        User newUser = new User();
        int validationStatus = getValidationStatus(user, passwordValidator);
        if (validationStatus == 1){
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(hashedPassword);
            repository.save(newUser);
            return "success";
        }
        return "error: " + validationStatus;
    }

    public User getUser(String username) {
        return repository.findByUsername(username);
    }
}
