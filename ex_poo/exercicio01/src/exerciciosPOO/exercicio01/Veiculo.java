package exerciciosPOO.exercicio01;

public class Veiculo {

	protected String Marca;
	protected String Modelo;
	protected String Placa;
	protected String Cor;
	protected float Km;
	protected boolean isLigado;
	protected int litrosCombustivel;
	protected int Velocidade;
	protected double Preco;

	public Veiculo() {
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getPlaca() {
		return Placa;
	}

	public void setPlaca(String placa) {
		Placa = placa;
	}

	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public float getKm() {
		return Km;
	}

	public void setKm(float km) {
		Km = km;
	}

	public boolean isLigado() {
		return isLigado;
	}

	public void setLigado(boolean isLigado) {
		this.isLigado = isLigado;
	}

	public int getLitrosCombustivel() {
		return litrosCombustivel;
	}

	public void setLitrosCombustivel(int litrosCombustivel) {
		this.litrosCombustivel = litrosCombustivel;
	}

	public int getVelocidade() {
		return Velocidade;
	}

	public void setVelocidade(int velocidade) {
		Velocidade = velocidade;
	}

	public double getPreco() {
		return Preco;
	}

	public void setPreco(double preco) {
		Preco = preco;
	}

	public void acelerar() {
		this.Velocidade += 20;
		System.out.println("Aumentando velocidade");
		System.out.println("Velocidade atual: " + getVelocidade());
	}

	public void abastecer(int combustivel) {

		if ((this.litrosCombustivel + combustivel) > 60) {
			System.out.println("Tanque de combustível suporta somente até 60 litros");
			System.out.println("Nível de combustivel: " + getLitrosCombustivel());
		} else {
			this.litrosCombustivel += combustivel;
			System.out.println("Abatecendo " + combustivel + " litros");
			System.out.println("Nível de combustivel: " + getLitrosCombustivel());
		}

	}

	public void frear() {
		if (this.Velocidade == 0)
			System.out.println("Veiculo parado!");
		else {
			this.Velocidade -= 20;
			System.out.println("Diminuindo velocidade");
			System.out.println("Velocidade atual: " + getVelocidade());
		}

	}

	public void pintar(String cor) {
		this.Cor = cor;
	}

	public void ligar() {
		if (this.isLigado == false) {
			this.isLigado = true;
			System.out.println(" ");
			System.out.println("========================");
			System.out.println("Cor : " + getCor());
			System.out.println("Veiculo ligado");
			System.out.println("Velocidade atual: " + getVelocidade());
			System.out.println("Nível de combustivel: " + getLitrosCombustivel());

		}

	}

	public void desligar() {
		if (this.Velocidade > 0)
			System.out.println("Diminua a velocidade primeiro!");
		else {
			System.out.println("Veiculo desligado");
			System.out.println("========================");
			this.isLigado = false;
		}

	}

}
