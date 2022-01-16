package com.example.library.livrariaapi.service;

import com.example.library.livrariaapi.client.LivroDTO;
import com.example.library.livrariaapi.dto.Autores;
import com.example.library.livrariaapi.dto.Livros;
import com.example.library.livrariaapi.repository.LivrosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class LivrosServiceTest {

    @Mock
    LivrosRepository livrosRepository;

    @InjectMocks
    LivrosService livrosService;
    @Test
    void listarLivrosComSucesso(){
        List<Livros> livros = new ArrayList<>();
        livros.add(new Livros(1L,"1234","qualquer um",new Autores(1l,"lucas",livros)));
        when(livrosRepository.findAll()).thenReturn(livros);
        List<LivroDTO> livrosBanco = livrosService.obterLivros();
        assertEquals(1L,livrosBanco.get(0).getId());
    }
    @Test
    void listarLivroIsbnComSucesso(){
        List<Livros> livros = new ArrayList<>();
        livros.add(new Livros(1l,"1234","qualquer um",new Autores(1l,"lucas",livros)));
        livros.add(new Livros(2l,"1111","qualquer dois",new Autores(2l,"cruz",livros)));
        when(livrosRepository.findByIsbn("1111")).thenReturn(livros.get(1));
        Livros livroBanco = livrosService.obterLivro("1111");
        assertEquals("1111",livroBanco.getIsbn());
        verify(livrosRepository).findByIsbn("1111");
    }

}
