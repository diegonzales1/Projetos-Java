package EX1;

public enum Categorias {

	BASICO(1, "Básico"), INTERMEDIARIO(2, "Intermediário"), EXECUTIVO(3, "Executivo"), PREMIUM(4, "Premium");

	int id;
	String categoria;

	Categorias(int id, String categoria) {
		this.id = id;
		this.categoria = categoria;
	}
}
