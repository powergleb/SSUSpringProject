package hospital.dao.impl;

import hospital.dao.interfaces.IPatientDAO;
import hospital.model.entities.Patient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class FakeDAOImpl implements IPatientDAO {

    public List<Patient> fakeRepo;

    public FakeDAOImpl(){
        fakeRepo = new ArrayList<>();
        Patient patient = new Patient(
                "Евстафий",
                "Беляков",
                "Евстафович",
                "qwerty",
                "evstaf228",
                "89682333795",
                "162374, г. Александров, ул. Поэтический б-р, " +
                "дом 53, квартира 504",
                "evstaf@gmail.com"
                );
        fakeRepo.add(patient);
        patient = new Patient(
                "Афанасий",
                "Пугачев",
                "Дмитриевич",
                "1234567",
                "afanasya002",
                "89755358777",
                "140703, г. Томск, ул. Зеленхозовский 1-й пер, " +
                        "дом 62, квартира 958",
                "afanya@mail.ru"
                );
        fakeRepo.add(patient);

        patient = new Patient(
                "Сильва",
                "Травникова",
                "Кирилловна",
                "afagfjsliekhfsielrfj3",
                "sylvanaWindrunner",
                "89440044705",
                "347629, г. Нерчинск, ул. Перевозная, дом 110, квартира 387",
                "s.travnikova@gmail.com"
                );
        fakeRepo.add(patient);
    }

    @Override
    public boolean createPatient(Patient patient) {
        if (fakeRepo.add(patient))
            return true;
        else return false;
    }

    @Override
    public Patient readPatient(int id) {
        Optional<Patient> patients = fakeRepo.stream().filter(el -> el.getId() == id).findFirst();
        return patients.orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        Patient workPatient = readPatient(patient.getId());
        if (workPatient == null)
            return false;
        else {
            deletePatient(patient.getId());
            createPatient(patient);
            return true;
        }
    }

    @Override
    public boolean deletePatient(int id) {
        Iterator<Patient> iterator = fakeRepo.iterator();

        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            if (id == patient.getId()) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Patient> readAllPatient() {
        return fakeRepo;
    }
}
