package com.example.demo.repositories;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Client;
@Repository
@Table (name="Clients")
public interface ClientRepository extends JpaRepository<Client,String> {

}
