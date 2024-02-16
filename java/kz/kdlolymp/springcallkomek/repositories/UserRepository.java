package kz.kdlolymp.springcallkomek.repositories;

import kz.kdlolymp.springcallkomek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findUserById(Long Id);

    List<User> findAll();

}
