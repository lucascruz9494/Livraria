package com.example.library.livrariaapi.repository;

import com.example.library.livrariaapi.dto.Autores;
import com.example.library.livrariaapi.dto.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores,Long> {


}
