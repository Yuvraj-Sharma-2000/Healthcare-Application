package com.example.had.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.had.security.UserRole.*;

@Repository("fake")
public class FakeApplicationUserService implements ApplicationUserDao{
    public final PasswordEncoder passwordEncoder;

    public FakeApplicationUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().
                stream().
                filter(applicationUser -> username.equals(applicationUser.getUsername())).
                findFirst();
    }
    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = new ArrayList<>();
                applicationUsers.add(new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "annasmith",
                        true,
                        true,
                        true,
                        true
                ));
                applicationUsers.add(new ApplicationUser(
                        DOCTOR.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "lynda",
                        true,
                        true,
                        true,
                        true
                ));
                applicationUsers.add(new ApplicationUser(
                        USER.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "tom",
                        true,
                        true,
                        true,
                        true
                ));
        return applicationUsers;
    }
}
