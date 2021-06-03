package exercicio03;

import java.util.List;
import java.util.Random;

public class Guerreiro extends Personagem {

	public List<String> habilidade;

	public Guerreiro(String nome, float xp, int inteligencia, int forca, int level, int vida, int mana,
			List<String> habilidade) {
		super(nome, xp, inteligencia, forca, level, vida, mana);
		this.habilidade = habilidade;
	}

	public List<String> getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(List<String> habilidade) {
		this.habilidade = habilidade;
	}

	public int Attack() {
		Random aleatorio = new Random();
		return (this.getForca() * this.getLevel()) + aleatorio.nextInt(300) + 0;
	}

	public void aprenderMagia(String habilidade) {
		if (getLevel() >= 20)
			this.habilidade.add(habilidade);
		else
			System.out.println("Suba de Level para aprender uma nova habilidade");
	}

	public void lvlUp() {
		this.setForca(getForca() + 10);
		this.setVida(getVida() + 10);
	}

	public void mostraGuerreiro() {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Nome: " + getNome() + " Level: " + getLevel() + " Inteligencia: " + getInteligencia()
				+ " Força: " + getForca() + " Vida: " + getVida() + " Mana: " + getMana() + " Xp: " + getXp()
				+ " Attack: " + Attack() + " Habilidade: " + getHabilidade());
		System.out.println();
	}
}
