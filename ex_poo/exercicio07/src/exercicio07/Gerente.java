package exercicio07;

public class Gerente extends Funcionario {

	public Gerente() {
	}

	public Gerente(String nome, int idade, double salario) {
		super(nome, idade, salario);
	}

	public double Bonificacao() {
		return this.getSalario() + 10000.00;
	}
}
