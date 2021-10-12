package net.digitalstars.repository;

import net.digitalstars.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ownerRepository")
public interface OwnerRepository extends JpaRepository<Owner, String>{
    
}//UserRepository
