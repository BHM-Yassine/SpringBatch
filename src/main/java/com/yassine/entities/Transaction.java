package com.yassine.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transaction {

	@Id
	private int id;
	private double montant;
	private Date dateTransaction;
	private Date dateDebit;

	@JsonIgnore
	@ManyToOne
	private Compte compte;
}
