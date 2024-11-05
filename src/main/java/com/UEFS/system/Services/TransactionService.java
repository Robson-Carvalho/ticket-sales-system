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

package main.java.com.UEFS.system.Services;

import main.java.com.UEFS.system.Interfaces.IService;
import main.java.com.UEFS.system.Models.Transaction;
import main.java.com.UEFS.system.Repositories.TransactionRepository;

import java.util.List;
import java.util.UUID;

/**
 * Classe de serviço para gerenciar operações relacionadas a transações.
 * Implementa a interface IService para a entidade Transaction.
 */
public class TransactionService implements IService<Transaction> {
    private final TransactionRepository transactionRepository;

    /**
     * Construtor que inicializa o repositório de transações.
     */
    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
    }

    /**
     * Cria uma nova transação e a salva no repositório.
     *
     * @param transaction Transação a ser criada.
     * @return A transação criada.
     */
    @Override
    public Transaction create(Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    /**
     * Retorna todas as transações salvas no repositório.
     *
     * @return Lista de todas as transações.
     */
    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    /**
     * Busca uma transação pelo seu ID.
     *
     * @param id ID da transação a ser buscada.
     * @return Transação encontrada ou null se não existir.
     */
    @Override
    public Transaction getById(UUID id) {
        return transactionRepository.findById(id);
    }

    /**
     * Atualiza uma transação existente.
     *
     * @param transaction Transação a ser atualizada.
     */
    @Override
    public void update(Transaction transaction) {
        transactionRepository.update(transaction);
    }

    /**
     * Deleta uma transação pelo seu ID.
     *
     * @param id ID da transação a ser deletada.
     */
    @Override
    public void delete(UUID id) {
        transactionRepository.delete(id);
    }

    /**
     * Deleta todas as transações do repositório.
     */
    @Override
    public void deleteAll() {
        transactionRepository.deleteAll();
    }
}
