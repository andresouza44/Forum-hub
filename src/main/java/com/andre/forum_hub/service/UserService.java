package com.andre.forum_hub.service;

import com.andre.forum_hub.config.TokenService;
import com.andre.forum_hub.dto.AuthenticationDTO;
import com.andre.forum_hub.dto.LoginResponseDTO;
import com.andre.forum_hub.dto.RegisterDTO;
import com.andre.forum_hub.dto.UserDto;
import com.andre.forum_hub.entity.Role;
import com.andre.forum_hub.entity.User;
import com.andre.forum_hub.repository.RoleRepository;
import com.andre.forum_hub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

   @Transactional
    public LoginResponseDTO login(AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }

    public UserDto registerNewUser (RegisterDTO dto) {

        if (repository.findByEmail(dto.email()) != null) {
            throw new UsernameNotFoundException("Usuário Não Encontrado");
        }

        String encryptedPassword = passwordEncoder.encode(dto.password());
        User newUser = new User(dto.name(), dto.email(), encryptedPassword);
        dto.roles().forEach(roleDto ->{
            Role role = roleRepository.getReferenceById(roleDto.getId());
            newUser.addRole(role);
        });

        repository.save(newUser);

        return new UserDto(newUser);
    }

}
