package com.andre.forum_hub.service;

import com.andre.forum_hub.config.TokenService;
import com.andre.forum_hub.dto.AuthenticationDto;
import com.andre.forum_hub.dto.LoginResponseDto;
import com.andre.forum_hub.dto.RegisterDto;
import com.andre.forum_hub.dto.UserDto;
import com.andre.forum_hub.entity.Role;
import com.andre.forum_hub.entity.User;
import com.andre.forum_hub.projection.UserDetailsProjection;
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

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;


    @Autowired
    TokenService tokenService;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       
        System.out.println("findby Email " + repository.findByEmail(username));

        List<UserDetailsProjection> result = repository.searchUserAndRolesByUserName(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        System.out.println("Service DTO:" + result.get(0).getUsername());
        System.out.println("Service DTO:" + result.get(0).getPassword());
        System.out.println("Service DTO:" + result.get(0).getAuthority());
        System.out.println("Service DTO:" + result.get(0));
        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setSenha(result.get(0).getPassword());
        user.getRoles().forEach(projection -> user.addRole(new Role(projection.getId(), projection.getAuthority())));
        System.out.println("USER: " + user);
        return user;
    }

    @Transactional
    public LoginResponseDto login(AuthenticationDto dto) {
        System.out.println("Service DTO login :" + dto);

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDto(token);
    }

    public UserDto registerNewUser(RegisterDto dto) {

        if (repository.findByEmail(dto.email()) != null) {
            throw new UsernameNotFoundException("Usuário Não Encontrado");
        }

        String encryptedPassword = passwordEncoder.encode(dto.password());
        User newUser = new User(dto.name(), dto.email(), encryptedPassword);
        dto.roles().forEach(roleDto -> {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            newUser.addRole(role);
        });

        repository.save(newUser);

        return new UserDto(newUser);
    }

}
