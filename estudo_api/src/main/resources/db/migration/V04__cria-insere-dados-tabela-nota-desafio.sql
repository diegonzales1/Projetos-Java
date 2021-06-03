CREATE TABLE nota_de_desafio (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nota_qualidade_codigo INT NOT NULL,
	nota_quantidade_entrega INT NOT NULL,
	codigo_submissao BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_submissao) REFERENCES submissao_de_desafio(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert INTO nota_de_desafio (nota_qualidade_codigo, nota_quantidade_entrega, codigo_submissao) VALUES (3, 3, 1);
insert INTO nota_de_desafio (nota_qualidade_codigo, nota_quantidade_entrega, codigo_submissao) VALUES (3, 3, 2);
 