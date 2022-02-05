package EX1;

public enum Veiculos {

	FIAT_UNO(1, "Fiat Uno", "Modelo Way", 1.0,"preto" ,130.00),
	VW_GOL(2, "VW Gol", "Modelo G3", 1.0, "branco", 150.00),
	JEEP_RENEGADE(3, "Jeep Renegade", "Modelo Std AT", 1.8,"preto" , 350.00),
	RENAULT_DUSTE(4, "Renault Duste", "Modelo Dynamique", 1.6, "branco", 350.00),
	TOYOTA_HILLUX(5, "Toyota Hillux", "Modelo SW4", 3.0,"preto" , 1500.00),
	GM_TRAILBLAZER(6, "GM TrailBlazer", "Modelo LTZ", 3.0, "branco", 1800.00),
	PORCHE_CAYENNE(7, "Porche Cayenne", "Modelo turbo GT", 4.0,"preto", 3500.00),
	FORD_MUSTANG(8, "Ford Mustang", "Modelo Mach 1", 5.0, "branco", 4000.00);

	int id;
	String nome;
	String modelo;
	String cor;
	double motor;
	double preco;

	Veiculos(int id, String nome, String modelo, double motor,String cor, double preco) {
		this.id = id;
		this.nome = nome;
		this.modelo = modelo;
		this.motor = motor;
		this.cor = cor;
		this.preco = preco;
	}

}
