package EX2;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		LivroRepositorio livro = new LivroRepositorio();

		int opcao;
		try {
			do {
				opcao = menu();

				switch (opcao) {
				case 1:
					livro.adicionaLivro(recebeLivro());
					break;
				case 2:
					livro.buscarLivro(buscaLivro());
					break;
				case 3:
					System.out.println("Você saiu do sistema");
					break;
				default:
					System.out.println("Opção inválida!!!");
					break;
				}
			} while (!(opcao == 3));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static String buscaLivro() {
		String opcao;
		Scanner sc = new Scanner(System.in);

		System.out.print("Digite o titulo do livro: ");
		opcao = sc.next();
		return opcao;
	}

	static int menu() {
		int opcao;
		Scanner sc = new Scanner(System.in);

		System.out.println("---------------------Livros FOLIA---------------------");
		System.out.println("1 - Cadastrar Livros");
		System.out.println("2 - Pesquisar Livro");
		System.out.println("3 - Sair");
		System.out.print("Digite a opção: ");
		opcao = sc.nextInt();
		System.out.println("-------------------------------------------------------\n");
		return opcao;
	}

	static Livro recebeLivro() {
		Livro livro = new Livro();
		Scanner sc = new Scanner(System.in);

		try {
			System.out.print("Digite o titulo: ");
			livro.setTitulo(sc.nextLine());

			System.out.print("Digite o autor: ");
			livro.setAutor(sc.nextLine());

			System.out.print("Digite a editora: ");
			livro.setEditora(sc.nextLine());

			System.out.print("Digite o número de páginas: ");
			livro.setNumeroDePaginas(sc.nextInt());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nLivro cadastrado com sucesso!!!\n\n");
		return livro;

	}
}
