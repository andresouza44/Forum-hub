package com.andre.forum_hub.service;

import com.andre.forum_hub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }




//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        List<UserDetailsProjection> result = repository.searchUserAndRolesByUserName(username);
//        if (result.isEmpty()){
//            throw new UsernameNotFoundException("Usuário não encontrado");
//        }
//
//        User user = new User();
//        user.setEmail(result.get(0).getUsername());
//        user.setSenha(result.get(0).getPassword());
//        user.getRoles().forEach(projectio -> user.addRole(new Role(projectio.getId(), projectio.getAuthority())));
//
//        return  user;
//    }
}
