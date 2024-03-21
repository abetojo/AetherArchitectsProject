package ca.sheridancollege.sprint1.security;

import ca.sheridancollege.sprint1.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
            @Lazy
    DatabaseAccess da;
    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException{
        System.out.println(email);
        ca.sheridancollege.sprint1.beans.User user = da.findUserAccount(email);
        if(user == null)
        {
            System.out.println("User not found: " + email);
            throw new UsernameNotFoundException("User " + email + " was not found");
        }

        List<String> rolesNames = da.getRolesById(user.getUserId());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if(rolesNames != null) {
            for(String role : rolesNames){
                grantList.add(new SimpleGrantedAuthority(role));
            }
        }

        UserDetails userDetails = new User(user.getEmail(),
                user.getEncryptedPassword(),
                grantList);
        return userDetails;
    }
}
