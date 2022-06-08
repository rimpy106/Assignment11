
package com.codercampus.Assignment11.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.codercampus.Assignment11.domain.Transaction;

@Repository
public class TransactionRepository {
	private List<Transaction> transactions = new ArrayList<>(100);
	Map<Long, Transaction> transactionMapData = new HashMap<>();

	public TransactionRepository() {
		super();
		populateData();
		populateTransactionMapData();
	}

	public List<Transaction> findAll() {
		Comparator<Transaction> comparator = (c1, c2) -> {
			return c1.getDate().compareTo(c2.getDate());
		};
		Collections.sort(transactions, comparator);
		return transactions;
	}

	@SuppressWarnings("unchecked")
	private void populateData() {
		try (FileInputStream fileInputStream = new FileInputStream("transactions.txt");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			this.transactions = (List<Transaction>) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void populateTransactionMapData() {
		for (int i = 0; i < transactions.size(); i++) {
			transactionMapData.put(transactions.get(i).getId(), transactions.get(i));
		}
	}

	public Transaction getTransById(Long transactionId) {
		/*
		 * System.out.println("transactionId : "+transactionId );
		 * System.out.println(transactionDataMap.containsKey(transactionId));
		 */	
	Transaction singleTransactionByID = (Transaction)transactionMapData.entrySet() 
				                                                        .stream() 
				                                                        .filter(entry->entry.getKey().equals(transactionId))
		                                                                .map(Map.Entry::getValue) 
		                                                                .findFirst() 
		                                                                .get();	

	return singleTransactionByID;
	}
}
