package com.yassine.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yassine.dao.CompteRepository;
import com.yassine.dao.TransactionRepository;
import com.yassine.entities.Compte;
import com.yassine.entities.Transaction;

@Component
public class TransactionWriter implements ItemWriter<Transaction> {

	@Autowired
	TransactionRepository transRepo;
	
	@Autowired
	CompteRepository compteRepo;
	
	@Override
	public void write(List<? extends Transaction> items) throws Exception {
		System.out.println("Writer Runned");
		
		items.forEach(item -> {
			Compte cmpt = compteRepo.getById(item.getCompte().getId());
			cmpt.setSolde(cmpt.getSolde() - item.getMontant());
			compteRepo.save(cmpt);
			transRepo.save(item);
		});

	}

}
