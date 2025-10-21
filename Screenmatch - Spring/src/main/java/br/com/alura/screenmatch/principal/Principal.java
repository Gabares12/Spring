package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    //private significa que só a própria classe Principal consegue acessar esse Scanner.
//Ninguém de fora (outras classes) pode usar direto essa variável.
//Isso é boa prática de programação: a gente esconde detalhes internos da classe e controla o acesso.
    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();

    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void exibirMenu() {
        System.out.println("Digite o nome da Série para busca: ");
        String nomeSerie = leitura.nextLine();
        String json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);


//        Aqui eu tô criando uma lista vazia pra guardar todas as temporadas da série.
//        Essa lista vai guardar vários objetos do tipo DadosTemporada
        List<DadosTemporada> temporadas = new ArrayList<>();
//        Esse for é um loop que vai rodar várias vezes — uma vez pra cada temporada.
//                Eu começo no 1 (porque a 1ª temporada é número 1, não zero), e o i <= dados.totalTemporadas() garante que vai até o número total de temporadas da série.
//                Ou seja, se a série tiver 7 temporadas, esse loop roda 7 vezes.
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
//            Dentro do loop, eu tô montando o link da API com o número da temporada (i).
//                    Cada vez que o loop roda, o número muda — então, na primeira vez ele pega a temporada 1, depois a 2, e assim por diante.
//                    Depois ele usa a classe ConsumoApi pra buscar os dados da temporada no site da OMDB (essa é a API que fornece as informações das séries).
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
//        temporadas.forEach(System.out::println);/Aqui eu só tô mandando imprimir todas as temporadas da lista.
//                O forEach vai percorrer cada item da lista e jogar no System.out.println, mostrando o conteúdo de cada DadosTemporada na tela.

        for(int i = 0; i < dados.totalTemporadas(); i++){
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for (int j = 0; j< episodiosTemporada.size() ; j++) {
                System.out.println(episodiosTemporada.get(j).titulo());

            }
        }
//        lambda ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t ->t.episodios().stream())
                .toList();

        dadosEpisodios.add(new DadosEpisodio("teste",3,"10","2020-01-01"));
        dadosEpisodios.forEach(System.out::println);



    }

}


//.SORTED() - serve para ordenar em ordem alfabetica
//.limit(3) - serve para limitar a lista a 3 itens ou quantos eu quiser (tiver)
//.filter - (n -> n.startsWith("N) nesse exemplo , este comando é usado para
// "Filtrar" os nomes que começam com "N" exibir apenas eles. mas podem ser usadas outras letras.
//.map - serve para deixar em letras MAIUSCULAS
//.ForEach - permite executar uma ação em cada elemento da stream. por exemplo,
//imprimir cada elemento da lista