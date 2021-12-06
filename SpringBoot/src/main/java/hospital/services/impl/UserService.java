package hospital.services.impl;

import hospital.repositories.DoctorRepository;
import hospital.repositories.PatientRepository;
import hospital.repositories.RoleRepository;
import hospital.entities.Doctor;
import hospital.entities.Patient;
import hospital.entities.Role;
import hospital.entities.User;
import hospital.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user, boolean isAdmin) throws Exception {
        user.setPass(passwordEncoder.encode(user.getPass()));
        if(user instanceof Doctor && !isAdmin) {
            user.setRoles(Collections.singletonList(getDoctorsRole()));
            doctorRepository.save((Doctor)user);
        }else if(user instanceof Patient && isAdmin){
            user.setRoles(Collections.singletonList(getAdminRole()));
            patientRepository.save((Patient) user);
        }else if(user instanceof Patient){
            user.setRoles(Collections.singletonList(getClientRole()));
            patientRepository.save((Patient) user);
        }else{
            throw new Exception("Doctor can't be admin");
        }

    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User client = patientRepository.findAll().stream().
                filter(c ->c.getLogin().equals(username)).findFirst().orElse(null);
        User doctor = doctorRepository.findAll().stream().
                filter(c ->c.getLogin().equals(username)).findFirst().orElse(null);
        if (client!=null){
            return toSpringUser(client);
        }else if (doctor!=null){
            return toSpringUser(doctor);
        }else{
            throw new UsernameNotFoundException(username);
        }
    }

    private org.springframework.security.core.userdetails.User toSpringUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPass(),
                user.getRoles()
        );
    }

    private Role getClientRole() {
        return getRole("ROLE_CLIENT");
    }

    private Role getAdminRole() {
        return getRole("ROLE_ADMIN");
    }

    private Role getDoctorsRole(){
        return  getRole("ROLE_DOCTOR");
    }

    private Role getRole(String roleName) {
        Optional<Role> roleUser = roleRepository.findByName(roleName);
        if (!roleUser.isPresent()) {
            Role role = new Role(roleName);
            roleRepository.save(role);

            return role;
        }

        return roleUser.get();
    }

}
