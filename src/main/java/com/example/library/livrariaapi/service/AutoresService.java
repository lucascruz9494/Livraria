package com.example.library.livrariaapi.service;

import com.example.library.livrariaapi.client.AutorDTO;
import com.example.library.livrariaapi.client.LivroDTO;
import com.example.library.livrariaapi.client.ServicoMalEscritoClient;
import com.example.library.livrariaapi.dto.Autores;
import com.example.library.livrariaapi.dto.Livros;
import com.example.library.livrariaapi.repository.AutoresRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoresService {

    private final AutoresRepository autoresRepository;
    private final ServicoMalEscritoClient servicoMalEscritoClient;

    public AutoresService(AutoresRepository autoresRepository, ServicoMalEscritoClient servicoMalEscritoClient) {
        this.autoresRepository = autoresRepository;
        this.servicoMalEscritoClient = servicoMalEscritoClient;
    }

    public List<AutorDTO> obterAutores() {

        List<AutorDTO> autoresEntidadeConvertidos = converteEntidadeParaDto(autoresRepository.findAll());
        return unificaAutores(autoresEntidadeConvertidos);

    }

    public List<AutorDTO> converteEntidadeParaDto(List<Autores> autoresEntidade) {
        return autoresEntidade.stream()
                .map(autoresConvertidos -> new AutorDTO(autoresConvertidos.getNome(), autoresConvertidos.getAutoresId().toString()))
                .collect(Collectors.toList());
    }

    public List<AutorDTO> unificaAutores(List<AutorDTO> autoresEntidadeConvertidos) {
        List<AutorDTO> autores = new ArrayList<>();
        List<AutorDTO> autoresMalEscritos = obtemAutoresalEscritos();
        autores.addAll(autoresEntidadeConvertidos);
        autores.addAll(autoresMalEscritos);
        return autores;
    }

    public void salvaAutores(Autores autores) {
        autoresRepository.save(autores);
    }

    public List<Livros> obterLivros(Long autoresId) {

        return autoresRepository.findById(autoresId).map(Autores::getLivros).orElse(List.of());

    }

    public List<AutorDTO> obtemAutoresalEscritos() {

        return servicoMalEscritoClient.obterAutores()
                .stream()
                .map(autor -> {
                    var autorDividido = autor.split(";");
                    return new AutorDTO(autorDividido[0], autorDividido[1]);
                })
                .collect(Collectors.toList());

    }

}
