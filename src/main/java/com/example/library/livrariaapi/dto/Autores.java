package com.example.library.livrariaapi.dto;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTORES")
public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTORES_ID")

    private Long autoresId;
    @Column
    private String nome;

    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL)
    private List<Livros> livros;



}
