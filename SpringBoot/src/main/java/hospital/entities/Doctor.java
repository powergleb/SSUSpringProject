package hospital.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import hospital.entities.help.Specialties;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Set;

@Entity
@Table(name = "doctors")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Doctor extends User
{

    @Enumerated(EnumType.STRING)
    private Specialties spec;

    @OneToMany(mappedBy="doctor", fetch = FetchType.EAGER)

    private Set<Record> records;

    public Specialties getSpec() {
        return spec;
    }

    public Doctor(String name, String secondName, String patronymic, String pass, String login, String phone, String address, String mail, Specialties spec) {
        super(name, secondName, patronymic, pass, login, phone, address, mail);
        this.spec = spec;
    }
    public Doctor(String name, String secondName, String patronymic, String pass, String login, String phone, String address, String mail) {
        super(name, secondName, patronymic, pass, login, phone, address, mail);

    }
    public Doctor(){

    }

    public void setSpec(Specialties spec) {
        this.spec = spec;
    }
    public void setValue(Class<?> type,
                         Doctor doctor,
                         String paramName,
                         String value) throws Exception {

        Field field;
        field = Doctor.class.getField(paramName);

        if (field == null)
            throw new IllegalArgumentException(
                    "can't find the field with name: " + paramName);

        field.setAccessible(true);
        field.set(doctor, value);
    }
}
