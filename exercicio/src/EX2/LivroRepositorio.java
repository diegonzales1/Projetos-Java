package EX2;

import java.util.ArrayList;
import java.util.List;

public class LivroRepositorio implements Repositorio {

	List<Livro> livros = new ArrayList<>();

	@Override
	public void adicionaLivro(Livro livro) {
		livros.add(livro);
	}

	@Override
	public void buscarLivro(String titulo) {

		boolean temLivro = false;

		for (Livro item : livros) {
			if (item.getTitulo().equalsIgnoreCase(titulo))
				temLivro = true;
		}

		if (temLivro == true) {
			for (Livro item : livros) {
				if (item.getTitulo().equalsIgnoreCase(titulo)) {
					System.out.println(item.toString());
				}
			}
		} else {
			System.out.println("\nTitulo não encontrado!!");
		}
	}

}
