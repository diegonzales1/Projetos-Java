package exercicio02.model;

import exercicio02.interfaces.Imposto;

public class Livro extends Produto implements Imposto {

	private String autor, tema;
	private int qtdPag;

	public Livro() {
	}

	public Livro(String nome, double preco, int qtd, String autor, String tema, int qtdPag) {
		super(nome, preco, qtd);
		this.autor = autor;
		this.tema = tema;
		this.qtdPag = qtdPag;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getQtdPag() {
		return qtdPag;
	}

	public void setQtdPag(int qtdPag) {
		this.qtdPag = qtdPag;
	}

	@Override
	public double calculaImposto() {
		if (this.tema.toLowerCase().equals("educativo")) {
			System.out.println("Livro educativo não tem imposto: " + this.getNome());
			return 0;
		} else {
			System.out.println("R$ " + this.getPreco() * 0.10 + " de impostos sobre o livro " + this.getNome());
			return this.getPreco() * 0.10;
		}
	}

	@Override
	public String toString() {
		return "Livro [getNome()=" + getNome() + ", getPreco()=" + getPreco() + "]";
	}

}
