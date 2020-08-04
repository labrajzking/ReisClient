package com.example.demo.repositories;

import javax.persistence.Table;


import com.example.demo.entities.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Table (name="users")
@Repository
public interface UserRepository extends JpaRepository <UserE,Integer>{
	UserE findByUsername(String username);
}
