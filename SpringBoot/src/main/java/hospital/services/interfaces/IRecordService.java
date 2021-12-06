package hospital.services.interfaces;

import hospital.entities.Doctor;
import hospital.entities.Patient;
import hospital.entities.Record;
import hospital.entities.help.Specialties;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@Service
public interface IRecordService {
    Record createRecord(Record record);
    Record readRecord(int id);
    void deleteRecord(int id);

    List<GregorianCalendar> GetFreeGapsByDoctorOnDay(Doctor doctor, GregorianCalendar date);

    List<GregorianCalendar> GetFreeGapsByDoctorOnWeek(Doctor doctor, GregorianCalendar dateFrom);

    Map<Doctor,List<GregorianCalendar>> GetFreeGapsByDoctorTypeOnDay(Specialties speciality, GregorianCalendar date);

    Map<Doctor,List<GregorianCalendar>> GetFreeGapsByDoctorTypeOnWeek(Specialties speciality, GregorianCalendar dateFrom);

    List<Record> GetFutureRecordsByPatient(Patient patient);

    List<Record> getRecordsByPatient(Patient patient);

    List<Record> getRecordsByDoctor(Doctor doctor);

    boolean isRecordExists(GregorianCalendar date);

    boolean isValidTimeForRecord(GregorianCalendar time);

}
