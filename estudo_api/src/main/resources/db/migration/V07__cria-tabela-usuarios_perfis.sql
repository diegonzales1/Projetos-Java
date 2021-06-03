CREATE TABLE usuario_perfis(
	codigo_perfis BIGINT (50) NOT NULL,
	codigo_usuario BIGINT (50) NOT NULL,
	FOREIGN KEY (codigo_perfis) REFERENCES perfil(codigo),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo)
)ENGINE=INNODB DEFAULT charset=UTF8 ;

INSERT INTO usuario_perfis (codigo_perfis, codigo_usuario) VALUES (1,1);