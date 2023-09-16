package com.example.Rinha.repositories;
import com.example.Rinha.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;
@Repository
public interface PessoaRepository extends  JpaRepository<Pessoa , UUID>{
    @Query("SELECT p FROM Pessoa p WHERE p.Nome LIKE %:termo% OR p.Apelido LIKE %:termo%")
    List<Pessoa> findByNomeOrApelidoContaining (@Param("termo") String termo);
}
