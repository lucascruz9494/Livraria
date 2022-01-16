package com.example.library.livrariaapi.service;

import com.example.library.livrariaapi.dto.Autores;
import com.example.library.livrariaapi.dto.Livros;
import com.example.library.livrariaapi.repository.AutoresRepository;
import com.example.library.livrariaapi.repository.LivrosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
public class AutoresServiceTest {

    @Mock
    private AutoresRepository autoresRepository;

    @InjectMocks
    private AutoresService autoresService;

    @Test
    void listarLivrosDoAutorComSucesso(){
        List<Livros> livros = new ArrayList<>();
        Autores autores = new Autores(1l,"lucas",livros);
        Autores autores2 = new Autores(2l,"cruz",livros);
        livros.add(new Livros(1l,"1234","qualquer um",autores));
        livros.add(new Livros(2l,"12345","qualquer dois",autores2));
        Mockito.when(autoresRepository.findById(1l)).thenReturn(Optional.of(autores));
        List<Livros> livroAutor = autoresService.obterLivros(1l);
        Assertions.assertEquals("1234", livroAutor.get(0).getIsbn());
    }
}
