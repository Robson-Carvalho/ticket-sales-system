package main.java.UEFS.system.service;

import main.java.UEFS.system.interfaces.IService;
import main.java.UEFS.system.model.Transaction;
import main.java.UEFS.system.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

public class TransactionService implements IService<Transaction> {
    private final TransactionRepository transactionRepository;

    public TransactionService() {this.transactionRepository = new TransactionRepository();}

    @Override
    public Transaction create(Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(UUID id) {
        return transactionRepository.findById(id);
    }

    @Override
    public void update(Transaction transaction) {
        transactionRepository.update(transaction);
    }

    @Override
    public void delete(UUID id) {
        transactionRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        transactionRepository.deleteAll();
    }
}
