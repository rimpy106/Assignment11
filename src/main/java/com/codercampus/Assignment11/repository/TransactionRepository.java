
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
	Map<Long, Transaction> transactionDataMap = new HashMap<>();

	public TransactionRepository() {
		super();
		populateData();
		populateDataforMap();
	}

	public List<Transaction> findAll() {
		Comparator<Transaction> comparator = (c1, c2) -> {
			return c1.getDate().compareTo(c2.getDate());
		};

		Collections.sort(transactions, comparator);

		/*
		 * for(int i=0;i<transactions.size();i++) {
		 * transactionMap.put(transactions.get(i).getId(), transactions.get(i)); }
		 */
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

	private void populateDataforMap() {
		for (int i = 0; i < transactions.size(); i++) {
			transactionDataMap.put(transactions.get(i).getId(), transactions.get(i));
		}
	}

	public Transaction getTransById(Long transactionId) {
		System.out.println("transactionId : "+transactionId );
		System.out.println(transactionDataMap.containsKey(transactionId));	
		
		Transaction singleTransactionByID = (Transaction)transactionDataMap.entrySet() 
				                                                           .stream() 
				                                                           .filter(entry->entry.getKey().equals(transactionId))
		                                                                   .map(Map.Entry::getValue) 
		                                                                   .findFirst() 
		                                                                   .get();		                                        

		System.out.println(singleTransactionByID);

		return singleTransactionByID;
	}

}
