package net.digitalstars.repository;

import com.digitalstars.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, String>{

}//TruckRepository
