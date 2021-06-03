CREATE TABLE submissao_de_desafio (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_starter BIGINT(20) NOT NULL,
	codigo_desafio BIGINT(20) NOT NULL,
	endereco_repositorio VARCHAR(150) NOT NULL,
	FOREIGN KEY (codigo_starter) REFERENCES starter(codigo),
	FOREIGN KEY (codigo_desafio) REFERENCES desafio(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert INTO submissao_de_desafio (endereco_repositorio, codigo_desafio, codigo_starter) VALUES ( "www.github.com/fulaninho1", 2, 1);

insert INTO submissao_de_desafio (endereco_repositorio, codigo_desafio, codigo_starter) VALUES ( "www.github.com/ciclaninho", 1, 2);