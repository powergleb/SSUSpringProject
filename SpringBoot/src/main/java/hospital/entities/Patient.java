package hospital.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Set;


@Entity
@Table(name = "patients")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Patient extends User {


    @OneToMany(mappedBy="patient")
    private Set<Record> records;

    public Patient() {
    }

    public Patient(String name, String secondName, String patronymic, String pass, String login, String phone, String address, String mail, Set<Record> records) {
        super(name, secondName, patronymic, pass, login, phone, address, mail);
        this.records = records;
    }

    public Patient(String name, String secondName, String patronymic, String pass, String login, String phone, String address, String mail) {
        super(name, secondName, patronymic, pass, login, phone, address, mail);
    }


    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
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
