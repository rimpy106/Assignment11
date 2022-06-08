package com.codercampus.Assignment11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;


@Controller
public class TransactionController {

	@Autowired
	private TransactionService transService;

	@GetMapping("/transactions")
	public String getAllTransactionsData(ModelMap model) {
		List<Transaction> getAllData = transService.findAll();
		model.put("getAllData", getAllData);
		System.out.println(getAllData+"\n");
		return "transactions";

	}

	
	@GetMapping("/transactions/{transactionId}")
	public String getTransaction(ModelMap model, @PathVariable Long transactionId) {
		Transaction singleTransaction = transService.getTransById(transactionId);
		System.out.println("singleTransaction :"+singleTransaction);
		model.put("singleTransaction", singleTransaction);
		return "transactiondetail";
	}

	/*
	 * @PostMapping("/transactions/{transactionId}") public String
	 * postTransaction(@PathVariable Integer transactionId, Transaction transaction)
	 * { transaction = transService.getTransById(transactionId);;
	 * System.out.println(transaction); return "redirect:/transactions/" +
	 * transactionId; }
	 */

}
