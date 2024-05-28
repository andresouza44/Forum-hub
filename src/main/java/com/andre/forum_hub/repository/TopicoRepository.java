package com.andre.forum_hub.repository;

import com.andre.forum_hub.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
            SELECT t FROM Topico  t
            WHERE UPPER (t.curso) LIKE UPPER (CONCAT ('%',:curso,'%'))
            AND YEAR(t.dataCriacao) = :ano
            """)


    Page<Topico> searchByCurso(Pageable pageable, String curso, Integer ano);
}
