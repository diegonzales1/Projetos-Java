package exercicio03;

public class Personagem {

	private String nome;
	private float xp;
	private int inteligencia, forca, level, vida, mana;
	static int contador = 0;

	public Personagem() {
	}

	public Personagem(String nome, float xp, int inteligencia, int forca, int level, int vida, int mana) {
		this.nome = nome;
		this.xp = xp;
		this.inteligencia = inteligencia;
		this.forca = forca;
		this.level = level;
		this.vida = vida;
		this.mana = mana;
		this.contador++;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getXp() {
		return xp;
	}

	public void setXp(float xp) {
		this.xp = xp;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void lvlUp() {
		this.inteligencia += 5;
		this.forca += 5;
		this.level += 1;
		this.vida += 5;
		this.mana += 5;
		this.xp += 10.5;
	}
}
