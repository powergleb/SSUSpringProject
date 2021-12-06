package hospital.services.impl;

import hospital.repositories.RecordRepository;
import hospital.entities.Doctor;
import hospital.entities.Patient;
import hospital.entities.Record;
import hospital.entities.help.Specialties;
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

    @Override
    public List<GregorianCalendar> GetFreeGapsByDoctorOnDay(Doctor doctor, GregorianCalendar date) {
        List<Record> doctorRecords = recordRepository.findAll().stream()
                .filter(r->isOneDate(date,r.getDate())
                        && r.getDoctor().getId() == doctor.getId())
                .collect(Collectors.toList());
        List<GregorianCalendar> gaps = new ArrayList<>();
        for(int i = 9; i<21;i++){
            GregorianCalendar timeByGap = new GregorianCalendar(
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH),
                    i,
                    0,
                    0);
            if(doctorRecords.stream().noneMatch(r->isOneHour(r.getDate(),timeByGap)))
                gaps.add(timeByGap);
        }
        return gaps;
    }

    @Override
    public List<GregorianCalendar> GetFreeGapsByDoctorOnWeek(Doctor doctor, GregorianCalendar date) {
        GregorianCalendar maxDate = new GregorianCalendar(date.get(GregorianCalendar.YEAR),
                date.get(GregorianCalendar.MONTH),
                date.get(GregorianCalendar.DAY_OF_MONTH)+7);
        List<Record> recordsByDoctorOnWeek = recordRepository.findAll().
                stream().
                filter(record -> record.getDate().get(GregorianCalendar.DATE) >= date.get(GregorianCalendar.DATE)
                        && record.getDate().get(GregorianCalendar.DATE) <= maxDate.get(GregorianCalendar.DATE)
                        && record.getDoctor().getId() == doctor.getId()).collect(Collectors.toList());
        List<GregorianCalendar> gaps = new ArrayList<>();
        for(int dayOfWeek =0; dayOfWeek<7;dayOfWeek++) {
            for (int i = 9; i < 21; i++) {
                GregorianCalendar timeByGap = new GregorianCalendar(
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH),
                        i,
                        0,
                        0);
                timeByGap.add(Calendar.DAY_OF_MONTH,dayOfWeek);
                if (recordsByDoctorOnWeek.stream().noneMatch(r -> isOneHour(r.getDate(), timeByGap)))
                    gaps.add(timeByGap);
            }
        }
        return gaps;
    }

    @Override
    public Map<Doctor,List<GregorianCalendar>> GetFreeGapsByDoctorTypeOnDay(Specialties speciality, GregorianCalendar date) {
        List<Record> allRecords = recordRepository.findAll();
        Map<Doctor,List<GregorianCalendar>> toReturn = new HashMap<>();
        for (Record record: allRecords) {
            if(record.getDoctor().getSpec() == speciality) {
                toReturn.put(record.getDoctor(), GetFreeGapsByDoctorOnDay(record.getDoctor(),date));
            }
        }
        return toReturn;
    }

    @Override
    public Map<Doctor,List<GregorianCalendar>> GetFreeGapsByDoctorTypeOnWeek(Specialties speciality, GregorianCalendar dateFrom) {
        List<Record> allRecords = recordRepository.findAll();
        Map<Doctor,List<GregorianCalendar>> toReturn = new HashMap<>();
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
                r.getPatient().getId() == patient.getId()).collect(Collectors.toList());
    }

    @Override
    public Record createRecord(Record record){
        return recordRepository.save(record);
    }

    @Override
    public void deleteRecord(int id) {
        recordRepository.deleteById(id);
    }

    @Override
    public Record readRecord(int id) {return recordRepository.getOne(id);}
    @Override
    public boolean isRecordExists(GregorianCalendar date) {
        List<Record> allRecords = recordRepository.findAll();
        for(Record record: allRecords){
            if(record.getDate().getTimeInMillis() == date.getTimeInMillis())
                return true;
        }
        return false;
    }

    public boolean isValidTimeForRecord(GregorianCalendar time){
        if(time.get(Calendar.MINUTE)!=0 || time.get(Calendar.SECOND)!=0){
            return false;
        }
        GregorianCalendar currentTime = new GregorianCalendar();
        if(time.getTimeInMillis()<currentTime.getTimeInMillis()){
            return false;
        }
        return true;
    }

    @Override
    public List<Record> getRecordsByPatient(Patient patient) {
        return recordRepository.findAll().stream().filter(r->r.getPatient().getId() == patient.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Record> getRecordsByDoctor(Doctor doctor) {
        return recordRepository.findAll().stream().filter(r->r.getDoctor().getId() == doctor.getId()).collect(Collectors.toList());
    }

    private boolean isOneDate(GregorianCalendar date1, GregorianCalendar date2){
        return date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR);
    }

    private boolean isOneHour(GregorianCalendar time1, GregorianCalendar time2){
        return time1.get(Calendar.HOUR_OF_DAY) == time2.get(Calendar.HOUR_OF_DAY);
    }

}
