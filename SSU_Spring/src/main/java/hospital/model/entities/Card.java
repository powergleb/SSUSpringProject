package hospital.model.entities;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Record> records;

    public Card(int id, List<Record> records) {
        this.id = id;
        this.records = records;
    }
    public Card() {
    }
    @OneToOne(mappedBy = "card")
    private Patient patient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Card(int id, List<Record> records, Patient patient) {
        this.id = id;
        this.records = records;
        this.patient = patient;
    }

    public void setValue(Class<?> type,
                         Card card,
                         String paramName,
                         String value) throws Exception {

        Field field;
        field = Card.class.getField(paramName);

        if (field == null)
            throw new IllegalArgumentException(
                    "can't find the field with name: " + paramName);

        field.setAccessible(true);
        field.set(card, value);
    }
}
