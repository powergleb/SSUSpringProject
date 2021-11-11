package hospital.services.interfaces;

import hospital.model.entities.Doctor;
import hospital.model.entities.Patient;
import hospital.model.entities.Record;
import hospital.model.entities.help.Specialties;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@Service
public interface IRecordService {
    Record createRecord(Record record);
    Record readRecord(int id);
    Record updateRecord(String id, String parameter, String newValue) throws Exception;
    void deleteRecord(int id);

    List<GregorianCalendar> GetFreeGapsByDoctorOnDay(Doctor doctor, GregorianCalendar date);

    List<GregorianCalendar> GetFreeGapsByDoctorOnWeek(Doctor doctor, GregorianCalendar dateFrom);

    Map<Doctor,List<GregorianCalendar>> GetFreeGapsByDoctorTypeOnDay(Specialties speciality, GregorianCalendar date);

    Map<Doctor,List<GregorianCalendar>> GetFreeGapsByDoctorTypeOnWeek(Specialties speciality, GregorianCalendar dateFrom);

    List<Record> GetFutureRecordsByPatient(Patient patient);

    List<Record> getRecordsByPatient(Patient patient);

}
