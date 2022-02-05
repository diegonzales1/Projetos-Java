package EX1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int opcao;
		Scanner sc = new Scanner(System.in);
		ClienteRepositorio clienteRepositorio = new ClienteRepositorio();

		do {
			opcao = menu();

			switch (opcao) {
			case 1:
				clienteRepositorio.cadastrarCliente(cadastrarCliente());
				break;

			default:
				break;
			}

		} while (!(opcao == 3));
	}

	static int menu() {

		Scanner sc = new Scanner(System.in);

		System.out.print("---------------------------------------");
		System.out.println("Locadora de Veiculos");
		System.out.println("1 - Cadastrar Cliente");
		System.out.println("2 - Listar Veiculos");
		System.out.println("3 - Sair");
		System.out.print("Digite a opção: ");
		int opcao = sc.nextInt();

		System.out.print("---------------------------------------");
		return opcao;
	}

	static Cliente cadastrarCliente() {
		Cliente cliente = new Cliente();
		int quantidadeDeVeiculos;
		Scanner sc = new Scanner(System.in);

		System.out.print("Digite o ID do cliente: ");
		cliente.setId(sc.nextInt());

		System.out.print("Digite o nome do cliente: ");
		cliente.setNome(sc.nextLine());

		System.out.print("Digite a idade do cliente: ");
		cliente.setIdade(sc.nextInt());

		System.out.print("Digite a quantidade de veiculos: ");
		quantidadeDeVeiculos = sc.nextInt();

		System.out.print("Digite a opção do veiculo: ");

		return cliente;
	}
}
