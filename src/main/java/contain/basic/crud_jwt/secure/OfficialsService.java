package contain.basic.crud_jwt.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OfficialsService implements UserDetailsService {

    @Autowired
    OfficialsRepository repository;

    public Officials signUP(Officials officials){
        repository.save(officials);
        return officials;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        //System.out.println("Received username is "+username);
        Officials officials = repository.findById(username).orElse(null);
        //System.out.println(officials.getFullName());
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
    
}
