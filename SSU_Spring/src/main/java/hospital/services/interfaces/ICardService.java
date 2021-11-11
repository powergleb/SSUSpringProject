package hospital.services.interfaces;

import hospital.model.entities.Card;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ICardService {

    Card createCard(Card card);

    Card readCard(int id);

    Card updateCard(String id, String parameter, String newValue) throws Exception;

    void deleteCard(int id);

    List<Card> findCardByPatient(int id);

}
