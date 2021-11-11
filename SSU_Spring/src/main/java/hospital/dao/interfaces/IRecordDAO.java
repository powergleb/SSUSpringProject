package hospital.dao.interfaces;


import hospital.model.entities.Doctor;
import hospital.model.entities.Record;

import java.util.List;

public interface IRecordDAO {
    boolean createDoctor(Record record);

    Record readDoctor(int id);

    boolean updateDoctor(Record record);

    boolean deleteDoctor(int id);

    Record findRecordById(int id);

    List<Record> findRecordByDoctorDay(Doctor doctor);

    List<Record> findRecordByDoctorWeek(Doctor doctor);

    boolean addRecord(Record record);

    List<Record> getAllRecordsByUser(Record Record);

}
