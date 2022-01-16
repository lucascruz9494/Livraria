package com.example.library.livrariaapi.repository;

import com.example.library.livrariaapi.dto.Autores;
import com.example.library.livrariaapi.dto.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivrosRepository extends JpaRepository<Livros,Long> {
    Livros findByIsbn(String isbn);
}
