package com.example.demo.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Criminal;
@Repository
public interface CriminalRepository extends JpaRepository <Criminal,Integer>{
@Query(value="SELECT * FROM finallist f where f.clients_client_code not in (SELECT clients_client_code from referencelist)",
			nativeQuery=true)
public	List<List<Object>> FinalResults();
}
