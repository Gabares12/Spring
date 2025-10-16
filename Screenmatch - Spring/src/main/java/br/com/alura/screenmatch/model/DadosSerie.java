package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// JsonAlias serve para selecionar oq é para aparecer
// JsonIgnoreproperties serve para igonorar oq não é relevante no link (de minha escolha)

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie (@JsonAlias("title")String titulo,

                         @JsonAlias("Episode")Integer numero,

                         @JsonAlias("imdbRating")String avaliacao,

                         @JsonAlias("Released")String dataLancamento) {
}
