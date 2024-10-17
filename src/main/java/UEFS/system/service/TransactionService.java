/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

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
