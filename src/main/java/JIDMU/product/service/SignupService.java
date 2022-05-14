package JIDMU.product.service;

import JIDMU.product.model.User;
import JIDMU.product.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import JIDMU.product.dto.SignupDTO;
import java.time.Instant;

@Service
public class SignupService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public boolean isValid(SignupDTO user, String passwordValidator){
        if (!passwordValidator.equals(user.getPassword())){
            return false;
        }
        return true;
    }

    public boolean createUser(SignupDTO user, String passwordValidator) {
        User newUser = modelMapper.map(user, User.class);
        if (isValid(user, passwordValidator)){
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            newUser.setPassword(hashedPassword);
            newUser.setCreatedAt(Instant.now());
            repository.save(newUser);
            return true;
        }
        return false;
    }

    public User getUser(String username) {
        return repository.findByUsername(username);
    }
}
