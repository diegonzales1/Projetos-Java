CREATE TABLE starter(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(255) NOT NULL,
	quatro_letras VARCHAR(4) NOT NULL,
	telefone VARCHAR(12) NOT NULL,
	endereco VARCHAR(50) NOT NULL, 
	linguagem VARCHAR(15) NOT NULL
)ENGINE=INNODB DEFAULT charset=UTF8 ;

insert into starter (nome, email, quatro_letras, telefone, endereco, linguagem ) values ("Ciclaninho Fulanes", "cofs@gft.com", "cofs", "15123456789", "Rua Das Inventadas", "Java");
insert into starter (nome, email, quatro_letras, telefone, endereco, linguagem ) values ("Fulaninho Ciclanes", "focs@gft.com", "focs", "15123456789", "Rua Inventada da Silva", ".NET");
