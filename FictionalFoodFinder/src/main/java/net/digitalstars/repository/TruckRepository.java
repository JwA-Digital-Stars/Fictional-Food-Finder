package net.digitalstars.repository;

import java.util.Optional;
import net.digitalstars.model.Owner;
import net.digitalstars.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, String>{
    public Optional<Truck> findByOwner(Owner owner_email);
}//TruckRepository
