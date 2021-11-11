package hospital.model.entities;

import hospital.model.entities.help.StatusAppointment;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "appointments")
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

    @Column(name = "endFlag")
    private boolean endFlag;

    @OneToOne(mappedBy = "appointment")
    private Record record;

    public Appointment(int id, StatusAppointment status, String description, boolean endFlag, Record record) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.endFlag = endFlag;
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

    public boolean isEndFlag() {
        return endFlag;
    }

    public void setEndFlag(boolean endFlag) {
        this.endFlag = endFlag;
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