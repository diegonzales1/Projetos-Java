CREATE TABLE perfil(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)ENGINE=INNODB DEFAULT charset=UTF8 ;

INSERT INTO perfil (nome) VALUES ("Fulano");
INSERT INTO perfil (nome) VALUES ("admin");