package exercicio02.model;

import exercicio02.interfaces.Imposto;

public class VideoGame extends Produto implements Imposto {

	private String marca, modelo;
	private boolean isUsado;

	public VideoGame() {
	}

	public VideoGame(String nome, double preco, int qtd, String marca, String modelo, boolean isUsado) {
		super(nome, preco, qtd);
		this.marca = marca;
		this.modelo = modelo;
		this.isUsado = isUsado;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public boolean isUsado() {
		return isUsado;
	}

	public void setUsado(boolean isUsado) {
		this.isUsado = isUsado;
	}

	@Override
	public double calculaImposto() {
		if (this.isUsado() == true) {
			System.out.println("Imposto " + this.getMarca() + " " + this.getModelo() + " usado," + " R$ "
					+ this.getPreco() * 0.25);
			return this.getPreco() * 0.25;
		} else {
			System.out.println("Imposto " + this.getMarca() + " " + this.getModelo() + " R$ " + this.getPreco() * 0.45);
			return this.getPreco() * 0.45;
		}

	}

}
