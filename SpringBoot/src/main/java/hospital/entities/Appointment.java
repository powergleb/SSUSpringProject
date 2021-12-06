package hospital.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import hospital.entities.help.StatusAppointment;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "appointments")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAppointment status;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "appointment", cascade=CascadeType.ALL)
    private Record record;

    public Appointment() {
    }

    public Appointment(int id, StatusAppointment status, String description, Record record) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.record = record;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public StatusAppointment getStatus() {
        return status;
    }

    public void setStatus(StatusAppointment status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(Class<?> type,
                         Appointment appointment,
                         String paramName,
                         String value) throws Exception {

        Field field;
        field = Appointment.class.getField(paramName);

        if (field == null)
            throw new IllegalArgumentException(
                    "can't find the field with name: " + paramName);

        field.setAccessible(true);
        field.set(appointment, value);
    }
}