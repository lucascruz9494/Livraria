package com.example.library.livrariaapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "servicoMalEscrito", url = "${service.servico-mal-escrito.url}")
public interface ServicoMalEscritoClient {

    @GetMapping("/li")
    List<String> obterLivros();

    @GetMapping("/au")
    List<String> obterAutores();

}
