package com.example.plateforme.Security;

import com.example.plateforme.Entities.App_User;
import com.example.plateforme.Repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class userDetailImp implements UserDetailsService {
    @Autowired
    RepoUser clientRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        App_User c = clientRepo.findByEmail(email).get();
        if(c != null) {
            String roles = c.getRole().getName();
            return User.withUsername(c.getEmail())
                    .password(c.getPassword())
                    .authorities(roles)
                    .build();
        }

        throw new UsernameNotFoundException(email+" Not found");
    }
}
