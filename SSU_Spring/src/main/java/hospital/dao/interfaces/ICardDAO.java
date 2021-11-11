package hospital.dao.interfaces;

import hospital.model.entities.Card;
import hospital.model.entities.Patient;

public interface ICardDAO {
    boolean createCard(Card card);

    Card readCard(int id);

    boolean updateCard(Card card);

    boolean deleteCard(int id);

    Card findCardByPatient(Patient patient);
}
