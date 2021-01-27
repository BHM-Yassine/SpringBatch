package com.yassine.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yassine.dao.CompteRepository;
import com.yassine.entities.Transaction;
import com.yassine.entities.TransactionHolder;

@Component
public class TransactionProcess implements ItemProcessor<TransactionHolder, Transaction> {
	
	@Autowired
	CompteRepository compteRepo;

	@Override
	public Transaction process(TransactionHolder item) throws Exception {
		System.out.println("Process Runned");
		System.out.println(item.toString());
		
		Transaction tr = new Transaction();

		tr.setId(item.getIdTransaction());
		tr.setMontant(item.getMontant());
		tr.setDateTransaction(item.getDateTransaction());
		tr.setDateDebit(new Date());
		tr.setCompte(compteRepo.getById(item.getIdCompte()));

		return tr;
	}

}
