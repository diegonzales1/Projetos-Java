package EX1;

import java.util.Map;

public class Cliente {

	private Integer id;
	private Integer idade;
	private String nome;
	private Map<Integer, Veiculos> veiculos;
	private Double valorAPagar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<Integer, Veiculos> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(Map<Integer, Veiculos> veiculos) {
		this.veiculos = veiculos;
	}

	public Double getValorAPagar() {
		return valorAPagar;
	}

	public void setValorAPagar(Double valorAPagar) {
		this.valorAPagar = valorAPagar;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", idade=" + idade + ", nome=" + nome + "]";
	}

}
