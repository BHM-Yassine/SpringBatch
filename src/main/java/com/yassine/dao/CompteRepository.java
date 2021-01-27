package com.yassine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

	Compte getById(Integer id);
	
}
