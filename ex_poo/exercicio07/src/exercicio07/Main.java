package exercicio07;

public class Main {

	public static void main(String[] args) {
		Gerente gerente = new Gerente("Juliette", 30, 3500.00);
		Supervisor supervisor = new Supervisor("Camila", 28, 2500.00);
		Vendedor vendedor = new Vendedor("Vitoria", 21, 1500.00);

		System.out.println("Gerente: " + gerente.getNome() + " \tBonifica��o: R$" + gerente.Bonificacao());
		System.out.println("Supervisora: " + supervisor.getNome() + " \tBonifica��o: R$" + supervisor.Bonificacao());
		System.out.println("Vendedora: " + vendedor.getNome() + " \tBonifica��o: R$" + vendedor.Bonificacao());
	}

}
