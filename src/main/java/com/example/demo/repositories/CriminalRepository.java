package com.example.demo.repositories;
import java.util.List;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Criminal;
@Repository
@Table(name="Persons")
public interface CriminalRepository extends JpaRepository <Criminal,Integer>{
@Query(value="SELECT f.*,cl.whole_name,crim.score,crim.person_whole_name FROM ((finallist f INNER JOIN clients cl ON cl.client_code=f.clients_client_code)INNER JOIN Persons crim ON crim.person_id=f.matched_criminals_person_id) WHERE f.clients_client_code NOT IN (SELECT ref.clientsreference_client_code from referencelist ref)",
			nativeQuery=true)
public	List<List<Object>> FinalResults1();
@Query(value="SELECT ref.*,cl.whole_name,crim.score,crim.person_whole_name FROM ((referencelist ref INNER JOIN clients cl ON cl.client_code=ref.clientsreference_client_code)INNER JOIN Persons crim ON crim.person_id=ref.matched_reference_criminals_person_id) WHERE ref.clientsreference_client_code NOT IN (SELECT f.clients_client_code from finallist f)",
nativeQuery=true)
public	List<List<Object>> FinalResults2();
@Query(value="SELECT crim.* from persons crim where crim.person_id= :person_id",nativeQuery=true)
Criminal findByCode(@Param("person_id")Integer person_id);
}
