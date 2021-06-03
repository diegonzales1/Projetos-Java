package exercicio03;

import java.util.List;
import java.util.Random;

public class Mago extends Personagem {

	private List<String> magia;

	public Mago(String nome, float xp, int inteligencia, int forca, int level, int vida, int mana, List<String> magia) {
		super(nome, xp, inteligencia, forca, level, vida, mana);
		this.magia = magia;
	}

	public List<String> getMagia() {
		return magia;
	}

	public void setMagia(List<String> magia) {
		this.magia = magia;
	}

	public void lvlUp() {
		this.setInteligencia(this.getInteligencia() + 10);
		this.setMana(this.getMana() + 10);
	}

	public int Attack() {
		Random aleatorio = new Random();
		return (getInteligencia() * getLevel()) + aleatorio.nextInt(300) + 0;
	}

	public void aprenderMagia(String magia) {
		if (getLevel() >= 20)
			this.magia.add(magia);
		else
			System.out.println("Suba de Level para aprender uma nova Magia");
	}

	public void mostraMago() {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Nome: " + getNome() + " Level: " + getLevel() + " Inteligencia: " + getInteligencia()
				+ " Força: " + getForca() + " Vida: " + getVida() + " Mana: " + getMana() + " Xp: " + getXp()
				+ " Attack: " + Attack() + " Magia: " + getMagia());
		System.out.println();
	}

}
