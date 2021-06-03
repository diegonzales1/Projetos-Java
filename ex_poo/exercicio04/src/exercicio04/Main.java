package exercicio04;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		Pessoas p1 = new Pessoas("João", 15);
		Pessoas p2 = new Pessoas("Leandro", 21);
		Pessoas p3 = new Pessoas("Paulo", 17);
		Pessoas p4 = new Pessoas("Jessica", 18);

		List<Pessoas> pessoa = new ArrayList<>();
		pessoa.add(p4);
		pessoa.add(p3);
		pessoa.add(p2);
		pessoa.add(p1);

		// Exercicio 04
		System.out.println("------------------------------------------");
		System.out.println("               Exercicio 04");
		System.out.println("------------------------------------------");
		pessoa.sort((x, y) -> x.getIdade() > y.getIdade() ? 1 : -1);

		System.out.println("Pessoa mais velha " + pessoa.get(pessoa.size() - 1).getNome() + " Idade: "
				+ pessoa.get(pessoa.size() - 1).getIdade() + " anos.");

		// Exercicio 05
		System.out.println("------------------------------------------");
		System.out.println("               Exercicio 05");
		System.out.println("------------------------------------------");

		System.out.println("Todas as Pessoas:");

		for (Pessoas p : pessoa) {
			System.out.println("Nome: " + p.getNome() + " \tIdade: " + p.getIdade() + " anos.");
		}

		pessoa.removeIf(a -> (a.getIdade() < 18));

		System.out.println("\nPessoas maiores de 18 anos:");
		for (Pessoas p : pessoa) {
			System.out.println("Nome: " + p.getNome() + " \tIdade: " + p.getIdade() + " anos.");
		}

		// Exercicio 06
		System.out.println("------------------------------------------");
		System.out.println("               Exercicio 06");
		System.out.println("------------------------------------------");

		for (Pessoas p : pessoa) {
			if (p.getNome().equals("Jessica"))
				System.out.println("Jessica tem " + p.getIdade() + " anos.");
		}
	}
}
