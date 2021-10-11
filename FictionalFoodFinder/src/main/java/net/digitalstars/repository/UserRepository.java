package net.digitalstars.repository;

import com.digitalstars.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

    public List<User> findAllByEmail(String email);
}//UserRepository
