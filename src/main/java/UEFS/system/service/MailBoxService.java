package main.java.UEFS.system.service;

import main.java.UEFS.system.interfaces.IService;
import main.java.UEFS.system.model.MailBox;
import main.java.UEFS.system.repository.MailBoxRepository;

import java.util.List;
import java.util.UUID;

public class MailBoxService implements IService<MailBox> {
    private final MailBoxRepository mailBoxRepository;

    public MailBoxService() {this.mailBoxRepository = new MailBoxRepository();}

    @Override
    public MailBox create(MailBox mailBox) {mailBoxRepository.save(mailBox); return mailBox;}

    @Override
    public List<MailBox> getAll() {return mailBoxRepository.findAll();}

    @Override
    public MailBox getById(UUID id) {return mailBoxRepository.findById(id);}

    @Override
    public void update(MailBox mailBox) {mailBoxRepository.update(mailBox);}

    @Override
    public void delete(UUID id) {mailBoxRepository.delete(id);}

    @Override
    public void deleteAll() {mailBoxRepository.deleteAll();}
}
