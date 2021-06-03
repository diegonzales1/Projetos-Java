package exercicio07;

public class Supervisor extends Funcionario {

	public Supervisor() {
	}

	public Supervisor(String nome, int idade, double salario) {
		super(nome, idade, salario);
	}

	public double Bonificacao() {
		return this.getSalario() + 5000.00;
	}
}
