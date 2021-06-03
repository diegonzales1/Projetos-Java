package com.example.starter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.starter.api.model.DesafioModel;

@Repository
public interface DesafioRepository extends JpaRepository<DesafioModel, Long> {

}
