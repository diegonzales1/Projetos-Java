package exercicio02.model;

import java.util.List;

public class Loja {

	private String nome, cnpj;
	private List<Livro> livros;
	private List<VideoGame> videoGames;

	public Loja() {
	}

	public Loja(String nome, String cnpj, List<Livro> livros, List<VideoGame> videoGames) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.livros = livros;
		this.videoGames = videoGames;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public List<VideoGame> getVideoGames() {
		return videoGames;
	}

	public void setVideoGames(List<VideoGame> videoGames) {
		this.videoGames = videoGames;
	}

	public void listaLivros() {

		if (!!livros.isEmpty()) {
			System.out.println("A loja não tem livros no seu estoque");
		} else {
			System.out.println("----------------------------------------------------------------");
			System.out.println("A loja " + this.nome + " possui estes livros para venda:");

			for (Livro livro : livros) {
				System.out.println("Titulo: " + livro.getNome() + ", " + "preço: " + livro.getPreco() + ", "
						+ "quantidade: " + livro.getQtd() + " em estoque.");
			}
		}
	}

	public void listaVideoGames() {

		if (!!videoGames.isEmpty()) {
			System.out.println("A loja não tem video-games no seu estoque");
		} else {
			System.out.println("----------------------------------------------------------------");
			System.out.println("A loja " + this.nome + " possui estes video-games para venda:");

			for (VideoGame videoGame : videoGames) {
				System.out.println("Video-game: " + videoGame.getModelo() + ", " + "preço: " + videoGame.getPreco()
						+ ", " + "quatidade: " + videoGame.getQtd() + " em estoque");
			}
		}
	}

	public double calculaPatrimonio() {
		System.out.println("----------------------------------------------------------------");

		double valorLivros = 0, valorVideoGames = 0, valorTotal = 0;

		for (Livro livro : livros) {
			valorLivros += livro.getPreco() * livro.getQtd();
		}

		for (VideoGame videoGame : videoGames) {
			valorVideoGames += videoGame.getPreco() * videoGame.getQtd();
		}

		valorTotal = valorLivros + valorVideoGames;

		System.out.println("O patrimonio da loja: " + this.nome + " é de R$ " + valorTotal);

		return 0;
	}

}
