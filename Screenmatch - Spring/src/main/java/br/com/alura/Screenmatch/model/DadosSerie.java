package br.com.alura.Screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // serve para ignorar tudo q n foi escolhido... ou seja ele só vai puxar title , total seassons  e imdbRating
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeassons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {

}
// não entendi oque é integer e @JsonAlias ou não me lembro
//eu do futuro lembre se procurar oque é isso