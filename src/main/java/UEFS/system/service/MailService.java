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

public class MailService implements IService<Mail> {
    private final MailRepository mailRepository;

    public MailService() {
        this.mailRepository = new MailRepository();
    }

    @Override
    public Mail create(Mail mailBox) {
        mailRepository.save(mailBox); return mailBox;}

    @Override
    public List<Mail> getAll() {return mailRepository.findAll();}

    @Override
    public Mail getById(UUID id) {return mailRepository.findById(id);}

    @Override
    public void update(Mail mailBox) {
        mailRepository.update(mailBox);}

    @Override
    public void delete(UUID id) {
        mailRepository.delete(id);}

    @Override
    public void deleteAll() {
        mailRepository.deleteAll();}
}
