package hospital.services.interfaces;

import hospital.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Service
public interface IUserService extends UserDetailsService {

    public void registerUser(User user, boolean isAdmin) throws  Exception;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
