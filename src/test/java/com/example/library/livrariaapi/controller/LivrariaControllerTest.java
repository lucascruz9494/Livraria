package com.example.library.livrariaapi.controller;

import com.example.library.livrariaapi.client.AutorDTO;
import com.example.library.livrariaapi.client.LivroDTO;
import com.example.library.livrariaapi.service.AutoresService;
import com.example.library.livrariaapi.service.LivrosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LivrariaController.class)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class LivrariaControllerTest {
    @MockBean
    private LivrosService livrosService;

    @Autowired
    private MockMvc mvc;

    @Test
    void obterTimesStatus200SemTimes() throws Exception {
        AutorDTO autorDTO = new AutorDTO("lucas cruz", "1");
        AutorDTO autorDTO1 = new AutorDTO("cruz", "2");

        List<LivroDTO> livroDTOS = List.of(new LivroDTO("titulo-1", "123", "12314", "1", autorDTO),
                new LivroDTO("titulo-2", "1234", "12341213", "1", autorDTO),
                new LivroDTO("titulo-3", "12345", "123123", "2", autorDTO1));

        when(livrosService.obterLivros()).thenReturn(livroDTOS);

        mvc.perform(get("/livros").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("[0]titulo",equalTo("titulo-1")))
                .andExpect(jsonPath("[0]id",equalTo("123")))
                .andExpect(jsonPath("[0]isbn",equalTo("12314")))
                .andExpect(jsonPath("[0]autorId",equalTo("1")))
                .andExpect(jsonPath("[0]autorDTO.nome",equalTo("lucas cruz")))
                .andExpect(jsonPath("[0]autorDTO.autoresId",equalTo("1")))
                        .andExpect(jsonPath("[1]titulo",equalTo("titulo-2")))
                .andExpect(jsonPath("[1]id",equalTo("1234")))
                .andExpect(jsonPath("[1]isbn",equalTo("12341213")))
                .andExpect(jsonPath("[1]autorId",equalTo("1")))
                .andExpect(jsonPath("[1]autorDTO.nome",equalTo("lucas cruz")))
                .andExpect(jsonPath("[1]autorDTO.autoresId",equalTo("1")))
                .andExpect(jsonPath("[2]titulo",equalTo("titulo-3")))
                .andExpect(jsonPath("[2]id",equalTo("12345")))
                .andExpect(jsonPath("[2]isbn",equalTo("123123")))
                .andExpect(jsonPath("[2]autorId",equalTo("2")))
                .andExpect(jsonPath("[2]autorDTO.nome",equalTo("cruz")))
                .andExpect(jsonPath("[2]autorDTO.autoresId",equalTo("2")));
    }

}
