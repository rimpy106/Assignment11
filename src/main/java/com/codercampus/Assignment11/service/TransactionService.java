package com.codercampus.Assignment11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transRepo;

	public List<Transaction> findAll() {
		return transRepo.findAll();
	}

	public Transaction getTransById(Long transactionId) {
		// TODO Auto-generated method stub
		return transRepo.getTransById(transactionId);
	}

}
