package hospital.model.entities;


import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "patients")
public class Patient extends User {


    @OneToOne(cascade = CascadeType.ALL)
    public Card card;

    public Patient() {
    }

    public Patient(String name, String secondName, String patronymic, String pass, String login, String phone, String address, String mail) {
        super(name, secondName, patronymic, pass, login, phone, address, mail);

    }



    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setValue(Class<?> type,
                         Patient patient,
                         String paramName,
                         String value) throws Exception {

        Field field;
        field = Patient.class.getField(paramName);

        if (field == null)
            throw new IllegalArgumentException(
                    "can't find the field with name: " + paramName);

        field.setAccessible(true);
        field.set(patient, value);
    }
}
