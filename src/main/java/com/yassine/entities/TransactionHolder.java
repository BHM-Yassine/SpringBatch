package com.yassine.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHolder {
	private int idTransaction;
	private int idCompte;
	private double montant;
	private Date dateTransaction;
}
