package JIDMU.theblog.service;

import JIDMU.theblog.model.User;
import JIDMU.theblog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class SignupService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public int createUser(User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getUsername());

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(hashedPassword);
        String hashedConfirmPassword = passwordEncoder.encode(user.getConfirmPassword());
        newUser.setConfirmPassword(hashedConfirmPassword);
        
        if (user.getConfirmPassword().equals(user.getPassword())){
            repository.save(newUser);
        }
        return 1;

    }

    public User getUser(String username) {
        return repository.findByUsername(username);
    }
}
