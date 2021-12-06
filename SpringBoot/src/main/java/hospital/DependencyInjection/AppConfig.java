package hospital.DependencyInjection;



import hospital.services.impl.AppointmentService;
import hospital.services.impl.DoctorService;
import hospital.services.impl.PatientService;
import hospital.services.impl.RecordService;
import hospital.services.interfaces.IAppointmentService;
import hospital.services.interfaces.IDoctorService;
import hospital.services.interfaces.IPatientService;
import hospital.services.interfaces.IRecordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class AppConfig {

    @Bean
    public IPatientService patientService(){
        return new PatientService();
    }

    @Bean
    public IDoctorService doctorService(){
        return new DoctorService();
    }

    @Bean
    public IRecordService recordService(){
        return new RecordService();
    }

    @Bean
    public IAppointmentService appointmentService(){ return new AppointmentService();}

}
