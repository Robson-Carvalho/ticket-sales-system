package main.java.UEFS.system.service;

import main.java.UEFS.system.interfaces.IService;
import main.java.UEFS.system.model.CreditCard;
import main.java.UEFS.system.repository.CreditCardRepository;

import java.util.List;
import java.util.UUID;

public class CreditCardService implements IService<CreditCard> {
    private final CreditCardRepository creditCardRepository;

    public CreditCardService() {this.creditCardRepository = new CreditCardRepository();}

    @Override
    public CreditCard create(CreditCard creditCard) {creditCardRepository.save(creditCard); return creditCard;}

    @Override
    public List<CreditCard> getAll() {return creditCardRepository.findAll();}

    @Override
    public CreditCard getById(UUID id) {return creditCardRepository.findById(id);}

    @Override
    public void update(CreditCard creditCard) {creditCardRepository.update(creditCard);}

    @Override
    public void delete(UUID id) {creditCardRepository.delete(id);}

    @Override
    public void deleteAll() {creditCardRepository.deleteAll();}
}
