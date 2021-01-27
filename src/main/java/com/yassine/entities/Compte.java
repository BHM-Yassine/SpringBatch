package com.yassine.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

	@Id
	private int id;
	private double solde;
	
	public Compte(int id, double solde) {
		this.id = id;
		this.solde = solde;
	}

	@OneToMany(mappedBy = "compte")
	List<Transaction> transactions = new ArrayList<Transaction>();
}
