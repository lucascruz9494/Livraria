package com.example.library.livrariaapi.service;

import com.example.library.livrariaapi.client.AutorDTO;
import com.example.library.livrariaapi.client.LivroDTO;
import com.example.library.livrariaapi.client.ServicoMalEscritoClient;
import com.example.library.livrariaapi.dto.Livros;
import com.example.library.livrariaapi.repository.LivrosRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivrosService {


    private final LivrosRepository livrosRepository;
    private final ServicoMalEscritoClient servicoMalEscritoClient;
    private final AutoresService autoresService;
    public LivrosService(AutoresService autoresService,LivrosRepository livrosRepository, ServicoMalEscritoClient servicoMalEscritoClient) {
        this.autoresService=autoresService;
        this.livrosRepository = livrosRepository;
        this.servicoMalEscritoClient = servicoMalEscritoClient;
    }

    public List<LivroDTO> obterLivros() {

        List<LivroDTO> livrosEntidadeConvertidos = converteEntidadeParaDto(livrosRepository.findAll());
        return unificaLivros(livrosEntidadeConvertidos);

    }

    public List<LivroDTO> converteEntidadeParaDto(List<Livros> livrosEntidade) {

        return livrosEntidade.stream()
                .map(livrosConvertidos ->
                        new LivroDTO(livrosConvertidos.getTitulo(),
                                livrosConvertidos.getId().toString(),
                                livrosConvertidos.getIsbn(),
                                livrosConvertidos.getAutores().getAutoresId().toString(),
                                new AutorDTO(livrosConvertidos.getAutores().getNome(),
                                        livrosConvertidos.getAutores().getAutoresId().toString())))
                .collect(Collectors.toList());
    }

    public List<LivroDTO> unificaLivros(List<LivroDTO> livrosEntidadeConvertidos) {
        List<LivroDTO> livros = new ArrayList<>();
        List<LivroDTO> livrosMalEscritos = obtemLivrosMalEscritos();
        livros.addAll(livrosEntidadeConvertidos);
        livros.addAll(livrosMalEscritos);
        return livros;
    }

    public void salvaLivros(Livros Livros) {
        livrosRepository.save(Livros);
    }

    public Livros obterLivro(String isbn) {
        return livrosRepository.findByIsbn(isbn);

    }

    public String obtemAutorPorId(String id){
        List<AutorDTO> autores = autoresService.obtemAutoresalEscritos();
        return autores.stream().filter(c-> id.equals(c.getAutoresId())).collect(Collectors.toList()).get(0).getNome();
    }


    public List<LivroDTO> obtemLivrosMalEscritos() {

        return servicoMalEscritoClient.obterLivros()
                .stream()
                .map(livro -> {
                    var livroDividido = livro.split(";");
                    return new LivroDTO(livroDividido[0], livroDividido[1], livroDividido[2], livroDividido[3],
                            new AutorDTO(obtemAutorPorId(livroDividido[3]),livroDividido[3])
                            );
                })
                .collect(Collectors.toList());

    }
}
