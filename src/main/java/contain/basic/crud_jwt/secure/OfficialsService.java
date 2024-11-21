package contain.basic.crud_jwt.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OfficialsService {

    @Autowired
    OfficialsRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    public Officials signUP(Officials officials){
        repository.save(officials);
        return officials;
    }

    public String verify(Officials officials){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(officials.getUsername(), officials.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(officials.getUsername());
        }
        else{
            return "failed";
        }
    }    
}
