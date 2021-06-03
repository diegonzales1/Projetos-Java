CREATE TABLE usuario(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(150) NOT NULL, 
	senha VARCHAR(150) NOT NULL,
	codigo_perfis BIGINT (50) NOT NULL,
	FOREIGN KEY (codigo_perfis) REFERENCES perfil(codigo)
)ENGINE=INNODB DEFAULT charset=UTF8 ;

INSERT INTO usuario (nome, email, senha, codigo_perfis) VALUES 
("Fulano", "fulano@gft.com", "$2a$10$yNRR9XjRFlb4N.e1qWvStu78NKI7xgvETgTzcvlVjthMaBUch0bqq", 1);

INSERT INTO usuario (nome, email, senha, codigo_perfis) VALUES 
("admin", "admin@gft.com", "$2a$10$yNRR9XjRFlb4N.e1qWvStu78NKI7xgvETgTzcvlVjthMaBUch0bqq", 2);