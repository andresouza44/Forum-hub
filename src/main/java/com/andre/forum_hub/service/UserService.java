package com.andre.forum_hub.service;

import com.andre.forum_hub.entity.User;
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
        User user = repository.findByEmail(username);
        System.out.println(user);
        System.out.println(user.getPassword());

        return user;
    }


//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        List<UserDetailsProjection> result = repository.searchUserAndRolesByUserName(username);
//        if (result.isEmpty()){
//            throw new UsernameNotFoundException("Usuário não encontrado");
//        }
//
//        System.out.println(result.get(0).getUsername());
//        System.out.println(result.get(0).getPassword());
//        System.out.println(result.get(0).getRoleId());
//        System.out.println(result.get(0).getAuthority());
//
//
//
//
//        User user = new User();
//        user.setEmail(result.get(0).getUsername());
//        user.setSenha(result.get(0).getPassword());
//        result.forEach(projeto -> user.addRole(new Role(projeto.getRoleId(), projeto.getAuthority())));
//        System.out.println(user);
//        return  user;
//    }
}
