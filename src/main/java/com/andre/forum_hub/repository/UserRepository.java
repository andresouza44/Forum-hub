package com.andre.forum_hub.repository;

import com.andre.forum_hub.entity.User;
import com.andre.forum_hub.projection.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_user.email AS username,tb_user.senha as password, tb_role.id AS roleId, tb_role.authority 
            FROM tb_user
            INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
            INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
            WHERE tb_user.email = :email 
            """)
    List<UserDetailsProjection> searchUserAndRolesByUserName(String email);

    UserDetails findByEmail(String email);
}
