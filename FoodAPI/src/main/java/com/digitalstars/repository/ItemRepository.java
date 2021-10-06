package com.digitalstars.repository;

import com.digitalstars.model.Item;
import com.digitalstars.model.Item.ItemID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, ItemID>{

}//ItemRepository
