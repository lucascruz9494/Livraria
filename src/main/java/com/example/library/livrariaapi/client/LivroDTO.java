package com.example.library.livrariaapi.client;


import com.example.library.livrariaapi.dto.Autores;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {

    private String titulo;

    private String id;

    private String isbn;

    private String autorId;

    private AutorDTO autorDTO;

}
