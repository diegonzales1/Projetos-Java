package exercicio07;

public class Vendedor extends Funcionario {

	public Vendedor() {
	}

	public Vendedor(String nome, int idade, double salario) {
		super(nome, idade, salario);
	}

	public double Bonificacao() {
		return this.getSalario() + 3000.00;
	}
}
