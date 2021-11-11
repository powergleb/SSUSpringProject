package hospital.services.impl;


import hospital.dao.repositories.CardRepository;
import hospital.model.entities.Card;
import hospital.model.entities.Doctor;
import hospital.services.interfaces.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardService implements ICardService {

    @Autowired
    public CardRepository cardRepository;

    public CardService() { }

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card readCard(int id) {
        return cardRepository.getOne(id);
    }

    @Override
    public Card updateCard(String id, String parameter, String newValue) throws Exception {
        Card card = cardRepository.findOne(Integer.parseInt(id));
        card.setValue(Doctor.class, card, parameter, newValue);
        return cardRepository.save(card);
    }

    @Override
    public void deleteCard(int id) {
        cardRepository. delete(id);
    }

    public List<Card> findCardByPatient(int id) {

        return cardRepository.findAll().stream().filter(i -> i.getPatient().id == id).collect(Collectors.toList());
    }
}
