package com.generation.Junit2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.Junit2.model.ContatoModel;



public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {

}
