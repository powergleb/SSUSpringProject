package hospital.services.impl;

import hospital.dao.repositories.RecordRepository;
import hospital.model.entities.Doctor;
import hospital.model.entities.Patient;
import hospital.model.entities.Record;
import hospital.model.entities.help.Specialties;
import hospital.services.interfaces.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordService implements IRecordService {

    @Autowired
    private RecordRepository recordRepository;

    public RecordService() {  }

    @Override
    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Record readRecord(int id) {
        return recordRepository.getOne(id);
    }

    @Override
    public Record updateRecord(String id, String parameter, String newValue) throws Exception {
        Record record = recordRepository.findOne(Integer.parseInt(id));
        record.setValue(Doctor.class, record, parameter, newValue);
        return recordRepository.save(record);
    }
    @Override
    public void deleteRecord(int id) {
        recordRepository.delete(id);
    }

    @Override
    public List<GregorianCalendar> GetFreeGapsByDoctorOnDay(Doctor doctor, GregorianCalendar date) {
        List<GregorianCalendar> allGapsByDay = new ArrayList<GregorianCalendar>();
        for(int currentHour = 9; currentHour<21;currentHour++){
            GregorianCalendar dateToAdd = new GregorianCalendar();
            dateToAdd.set(date.get(GregorianCalendar.YEAR),
                    date.get(GregorianCalendar.MONTH),
                    date.get(GregorianCalendar.DAY_OF_MONTH),currentHour,0);
            allGapsByDay.add(dateToAdd);
        }
        List<Record> recordsByDoctorOnDay = recordRepository.findAll().
                stream().
                filter(record -> record.getDate().get(GregorianCalendar.DATE) == date.get(GregorianCalendar.DATE)
                        && record.getDoctor().getId() ==doctor.getId()).collect(Collectors.toList());
        for (Record record: recordsByDoctorOnDay) {
            allGapsByDay.remove(record.getDate());
        }
        return  allGapsByDay;
    }

    @Override
    public List<GregorianCalendar> GetFreeGapsByDoctorOnWeek(Doctor doctor, GregorianCalendar dateFrom) {
        List<GregorianCalendar> allGapsByDay = new ArrayList<GregorianCalendar>();
        GregorianCalendar maxDate = new GregorianCalendar(dateFrom.get(GregorianCalendar.YEAR),
                dateFrom.get(GregorianCalendar.MONTH),
                dateFrom.get(GregorianCalendar.DAY_OF_MONTH)+7);
        List<Record> recordsByDoctorOnWeek = recordRepository.findAll().
                stream().
                filter(record -> record.getDate().get(GregorianCalendar.DATE) >= dateFrom.get(GregorianCalendar.DATE)
                        && record.getDate().get(GregorianCalendar.DATE) <= maxDate.get(GregorianCalendar.DATE)
                        && record.getDoctor().getId() == doctor.getId()).collect(Collectors.toList());
        for (int i = 0; i < 7; i++) {
            for (int currentHour = 9; currentHour < 21; currentHour++) {
                GregorianCalendar dateToAdd = new GregorianCalendar();
                dateToAdd.set(dateFrom.get(GregorianCalendar.YEAR),
                        dateFrom.get(GregorianCalendar.MONTH),
                        dateFrom.get(GregorianCalendar.DAY_OF_MONTH), currentHour, 0);
                allGapsByDay.add(dateToAdd);
                dateFrom.add(GregorianCalendar.DAY_OF_MONTH,1);
            }
        }
        for (Record record: recordsByDoctorOnWeek) {
            allGapsByDay.remove(record.getDate());
        }
        return  allGapsByDay;
    }

    @Override
    public Map<Doctor, List<GregorianCalendar>> GetFreeGapsByDoctorTypeOnDay(Specialties speciality, GregorianCalendar date) {
        List<Record> allRecords = recordRepository.findAll();
        Map<Doctor,List<GregorianCalendar>> toReturn = new HashMap<Doctor,List<GregorianCalendar>>();
        for (Record record: allRecords) {
            if(record.getDoctor().getSpec() == speciality) {
                toReturn.put(record.getDoctor(), GetFreeGapsByDoctorOnDay(record.getDoctor(),date));
            }
        }
        return toReturn;
    }

    @Override
    public Map<Doctor, List<GregorianCalendar>> GetFreeGapsByDoctorTypeOnWeek(Specialties speciality, GregorianCalendar dateFrom) {
        List<Record> allRecords = recordRepository.findAll();
        Map<Doctor,List<GregorianCalendar>> toReturn = new HashMap<Doctor,List<GregorianCalendar>>();
        for (Record record: allRecords) {
            if(record.getDoctor().getSpec() == speciality) {
                toReturn.put(record.getDoctor(), GetFreeGapsByDoctorOnWeek(record.getDoctor(),dateFrom));
            }
        }
        return toReturn;
    }

    @Override
    public List<Record> GetFutureRecordsByPatient(Patient patient) {
        return recordRepository.findAll().stream().filter(r->r.getDate().getTimeInMillis()>System.currentTimeMillis() &&
                r.getCard().getPatient().getId() == patient.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Record> getRecordsByPatient(Patient patient) {
        return recordRepository.findAll().stream().filter(r->r.getCard().getPatient().getId() == patient.getId()).collect(Collectors.toList());
    }
}
