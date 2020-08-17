package com.example.demo.repositories;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Client;
@Repository
@Table (name="Clients")
public interface ClientRepository extends JpaRepository<Client,String> {
@Query (value="SELECT cl.* from clients cl where cl.client_code= :client_code",nativeQuery=true)
	Client findByCode(@Param("client_code")String client_code);

}
