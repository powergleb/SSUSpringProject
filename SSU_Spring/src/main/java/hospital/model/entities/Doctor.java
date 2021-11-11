package hospital.model.entities;

import hospital.model.entities.help.Specialties;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "doctors")
public class Doctor extends User
{

    @Enumerated(EnumType.STRING)
    private Specialties spec;

    public Specialties getSpec() {
        return spec;
    }

    public Doctor(String name, String secondName, String patronymic, String pass, String login, String phone, String address, String mail, Specialties spec) {
        super(name, secondName, patronymic, pass, login, phone, address, mail);
        this.spec = spec;
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
