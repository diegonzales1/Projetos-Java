CREATE TABLE desafio (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_desafio VARCHAR(50) NOT NULL 
)ENGINE=INNODB DEFAULT charset=UTF8 ;

insert into desafio (nome_desafio) values ("Estudo MVC");
insert into desafio (nome_desafio) values ("Desafio MVC");