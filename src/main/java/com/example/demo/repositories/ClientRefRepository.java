package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.ReferenceResults;

public interface ClientRefRepository extends JpaRepository<ReferenceResults,String> {

}
