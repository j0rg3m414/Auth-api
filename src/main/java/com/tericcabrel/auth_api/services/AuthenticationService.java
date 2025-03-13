package com.tericcabrel.auth_api.services;

import com.tericcabrel.auth_api.dtos.LoginUserDto;
import com.tericcabrel.auth_api.dtos.RegistreUserDto;
import com.tericcabrel.auth_api.entities.Role;
import com.tericcabrel.auth_api.entities.RoleEnum;
import com.tericcabrel.auth_api.entities.User;
import com.tericcabrel.auth_api.repository.RoleRepository;
import com.tericcabrel.auth_api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    public User signup(RegistreUserDto input){
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if(optionalRole.isEmpty())
            return null;

        var user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        /*User user = new User(
                input.getFullName(),
                input.getEmail(),
                passwordEncoder.encode(input.getPassword()));
        */

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
