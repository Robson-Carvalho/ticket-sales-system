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
import main.java.UEFS.system.model.Mail;
import main.java.UEFS.system.repository.MailRepository;

import java.util.List;
import java.util.UUID;

/**
 * Classe de serviço para gerenciar operações relacionadas a correios.
 * Implementa a interface IService para a entidade Mail.
 */
public class MailService implements IService<Mail> {
    private final MailRepository mailRepository;

    /**
     * Construtor da classe que inicializa o repositório de correios.
     */
    public MailService() {
        this.mailRepository = new MailRepository();
    }

    /**
     * Cria um novo correio e o salva no repositório.
     *
     * @param mailBox Correio a ser criado.
     * @return O correio criado.
     */
    @Override
    public Mail create(Mail mailBox) {
        mailRepository.save(mailBox);
        return mailBox;
    }

    /**
     * Retorna todos os correios salvos no repositório.
     *
     * @return Lista de todos os correios.
     */
    @Override
    public List<Mail> getAll() {
        return mailRepository.findAll();
    }

    /**
     * Busca um correio pelo seu ID.
     *
     * @param id ID do correio a ser buscado.
     * @return Correio encontrado ou null se não existir.
     */
    @Override
    public Mail getById(UUID id) {
        return mailRepository.findById(id);
    }

    /**
     * Atualiza um correio existente.
     *
     * @param mailBox Correio a ser atualizado.
     */
    @Override
    public void update(Mail mailBox) {
        mailRepository.update(mailBox);
    }

    /**
     * Deleta um correio pelo seu ID.
     *
     * @param id ID do correio a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        mailRepository.delete(id);
    }

    /**
     * Deleta todos os correios do repositório.
     */
    @Override
    public void deleteAll() {
        mailRepository.deleteAll();
    }
}
