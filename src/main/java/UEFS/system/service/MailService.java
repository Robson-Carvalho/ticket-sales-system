package main.java.UEFS.system.service;

import main.java.UEFS.system.interfaces.IService;
import main.java.UEFS.system.model.Mail;
import main.java.UEFS.system.repository.MailRepository;

import java.util.List;
import java.util.UUID;

public class MailService implements IService<Mail> {
    private final MailRepository mailRepository;

    public MailService() {this.mailRepository = new MailRepository();}

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
