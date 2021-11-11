package hospital.model.entities;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.GregorianCalendar;

@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name="card_id", nullable=false)
    private Card card;

    @Column(name = "date")
    private GregorianCalendar date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public Record(int id, Doctor doctor, Card card, GregorianCalendar date, Appointment appointment) {
        this.id = id;
        this.doctor = doctor;
        this.card = card;
        this.date = date;
        this.appointment = appointment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setValue(Class<?> type,
                         Record record,
                         String paramName,
                         String value) throws Exception {

        Field field;
        field = Record.class.getField(paramName);

        if (field == null)
            throw new IllegalArgumentException(
                    "can't find the field with name: " + paramName);

        field.setAccessible(true);
        field.set(record, value);
    }
}
