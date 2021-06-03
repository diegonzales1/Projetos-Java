package exercicio03;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                         Magos: ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("");

		// Magos
		List<String> Magia = new ArrayList<>();
		Magia.add("Lumus");

		Mago mag1 = new Mago("Harry Potter", 15, 100, 200, 15, 100, 50, Magia);

		// Aprender Magias
		mag1.aprenderMagia("Expectro Patronum");
		mag1.lvlUp();

		mag1.mostraMago();

		Mago mag2 = new Mago("Hemione Granger", 20, 150, 230, 31, 100, 55, Magia);
		mag2.aprenderMagia("Cruccios");
		mag2.mostraMago();

		System.out.println("");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                       Guerreiros");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("");

		// Guerreiros
		List<String> habilidades = new ArrayList<>();
		habilidades.add("Luta");

		Guerreiro gue1 = new Guerreiro("Hercules", 21, 100, 200, 5, 99, 45, habilidades);

		// Sobe de lvl
		gue1.lvlUp();

		// Mostra Guerreiro
		gue1.mostraGuerreiro();

		Guerreiro gue2 = new Guerreiro("Uriah", 25, 100, 199, 20, 98, 30, habilidades);
		gue2.aprenderMagia("Espadachim");
		gue2.mostraGuerreiro();

		// Quantidade de Personagens
		System.out.println("");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"                                                  Quantidade de personagens: " + Personagem.contador);
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("");

	}

}
