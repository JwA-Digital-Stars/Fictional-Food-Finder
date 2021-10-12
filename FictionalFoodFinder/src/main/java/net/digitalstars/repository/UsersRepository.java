package net.digitalstars.repository;

import net.digitalstars.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users, Integer>{

    public List<Users> findAll();
    public List<Users> findAllByEmail(String email);
    
}//UserRepository
