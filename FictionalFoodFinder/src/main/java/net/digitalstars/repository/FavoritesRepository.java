package net.digitalstars.repository;

import com.digitalstars.model.Customer;
import com.digitalstars.model.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository{

    public List<Favorite> findAllByCustomer(Customer customer);
}//FavoritesRepository
