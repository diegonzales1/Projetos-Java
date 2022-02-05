package EX1;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio implements Repositorio{

	private List<Cliente> clientes = new ArrayList<>();
	
	
	@Override
	public void cadastrarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

}
