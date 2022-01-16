package com.example.library.livrariaapi.controller;

import com.example.library.livrariaapi.client.AutorDTO;
import com.example.library.livrariaapi.client.LivroDTO;
import com.example.library.livrariaapi.client.ServicoMalEscritoClient;
import com.example.library.livrariaapi.dto.Livros;
import com.example.library.livrariaapi.dto.Autores;
import com.example.library.livrariaapi.service.LivrosService;
import com.example.library.livrariaapi.service.AutoresService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class LivrariaController {


    private final ServicoMalEscritoClient servicoMalEscritoClient;


    private final AutoresService autoresService;


    private final LivrosService livrosService;

    public LivrariaController(ServicoMalEscritoClient servicoMalEscritoClient, AutoresService autoresService, LivrosService livrosService) {
        this.servicoMalEscritoClient = servicoMalEscritoClient;
        this.autoresService = autoresService;
        this.livrosService = livrosService;
    }

    @GetMapping("/livros")
    public List<LivroDTO> getLivros()  {

        return livrosService.obterLivros();
    }
    @GetMapping("/livros/{isbn}")
    public Livros getLivro(@PathVariable String isbn)  {

        return livrosService.obterLivro(isbn);
    }
    @GetMapping("/autores/{autoresId}")
    public List<Livros> getLivrosDoAutor(@PathVariable Long autoresId)  {

        return autoresService.obterLivros(autoresId);
    }
   @PostMapping("/autores")
    public void postAutores(@RequestBody Autores autores)  {

        autoresService.salvaAutores(autores);
    }
    @GetMapping("/autores")
    public List<AutorDTO> getAutores()  {

        return autoresService.obterAutores();
    }

    @PostMapping("/livros")
    public void postLivros(@RequestBody Livros Livros)  {

        livrosService.salvaLivros(Livros);
    }

    @GetMapping("/li")
    public List<LivroDTO> getLivrosMalEscritos(){
        return livrosService.obtemLivrosMalEscritos();
    }

    @GetMapping("/au")
    public List<AutorDTO> getAutoresMalEscritos(){
        return autoresService.obtemAutoresalEscritos();
    }
}
