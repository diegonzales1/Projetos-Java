package exercicio02.main;

import java.util.ArrayList;
import java.util.List;

import exercicio02.model.Livro;
import exercicio02.model.Loja;
import exercicio02.model.VideoGame;

public class Main {

	public static void main(String[] args) {

		Livro l1 = new Livro("Harry Potter", 40, 50, "J. k. Rowling", "fantasia", 3900);
		Livro l2 = new Livro("Senhor dos Anéis", 60, 30, "J. R. R. Tolkien", "fantasia", 500);
		Livro l3 = new Livro("Java POO", 20, 50, "GFT", "educativo", 500);

		VideoGame ps4 = new VideoGame("PS4", 1800, 100, "Sony", "Slim", false);
		VideoGame ps4Usado = new VideoGame("PS4", 1000, 7, "Sony", "Slim", true);
		VideoGame xbox = new VideoGame("XBOX", 1500, 500, "Microsoft", "One", false);

		List<Livro> livros = new ArrayList<>();
		livros.add(l1);
		livros.add(l2);
		livros.add(l3);

		List<VideoGame> games = new ArrayList<>();
		games.add(ps4);
		games.add(ps4Usado);
		games.add(xbox);

		Loja americanas = new Loja("Americana", "12345678", livros, games);

		l2.calculaImposto();
		l3.calculaImposto();

		ps4Usado.calculaImposto();
		ps4.calculaImposto();

		americanas.listaLivros();
		americanas.listaVideoGames();
		americanas.calculaPatrimonio();

	}

}
