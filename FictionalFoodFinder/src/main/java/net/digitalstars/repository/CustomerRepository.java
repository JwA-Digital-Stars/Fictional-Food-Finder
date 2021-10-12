package net.digitalstars.repository;

import net.digitalstars.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, String>{
    
}//UserRepository
