package com.java.assignment.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java.assignment.bean.Transaction;

@Service
public class transactionServiceImpl{
	
	public Map<String, Integer> getRewardPoints(List<Transaction> transactionList) {
		Map<String, Integer> monthSpent = new HashMap<>();
		
		transactionList.forEach(transaction -> {
			String month = transaction.getTransactionDate().getMonth().name();
			int rewards = 0;
			if(transaction.getAmountSpent() < 50) {
				rewards = 0;
			} else if(transaction.getAmountSpent() <=100 && transaction.getAmountSpent() > 50) {
				rewards = transaction.getAmountSpent() - 50;
			} else if(transaction.getAmountSpent() > 100) {
				rewards = ((transaction.getAmountSpent()-50)/50) + (2* transaction.getAmountSpent()-100);
			}

			if(monthSpent.containsKey(month)){
				
				int total = monthSpent.get(month) + rewards;
				monthSpent.put(month, total);
			} else {
				monthSpent.put(month, rewards);
			}
		});
		return monthSpent;
	}
}
