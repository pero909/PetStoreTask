package com.example.petstore.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    LocalDateTime dateOfExecution;
    Integer numOfFailedTransactions;
    Integer numOfSuccessfulTransactions;

    public Transaction(LocalDateTime dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
        this.numOfFailedTransactions=0;
        this.numOfSuccessfulTransactions=0;
        dateOfExecution.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
    }
   public void addSuccessfulTransaction(){
        numOfSuccessfulTransactions++;
    }
   public void addFailedTransaction(){
        numOfFailedTransactions++;
    }

    @Override
    public String toString() {
        return String.format("Transaction time:%s %n " +
                "Successful transactions:%d %n" +
                "Failed transactions:%d",dateOfExecution,numOfSuccessfulTransactions,numOfFailedTransactions);
    }
}
