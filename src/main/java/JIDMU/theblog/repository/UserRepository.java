package JIDMU.theblog.repository;

import JIDMU.theblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    // SELECT * FROM User WHERE username = ‘username in parameter’
    User findByUsername(String username);

}
