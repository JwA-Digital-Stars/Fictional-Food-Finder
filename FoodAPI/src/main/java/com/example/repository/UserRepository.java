package com.example.repository;

import com.example.model.User;
import com.example.model.User.UserID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserID>{

}//UserRepository
