-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.23 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.2.0.6213
-- --------------------------------------------------------

SET FOREIGN_KEY_CHECKS=0;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando dados para a tabela loja_dogs.cliente: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`codigo`, `cpf`, `email`, `bairro`, `cep`, `cidade`, `complemento`, `estado`, `logradouro`, `numero`, `nome`, `telefone`) VALUES
	(1, '111.111.111-11', 'lupe@loja.com', 'Vila Amarela', '55.555-444', 'Santos', 'Casa Verde', 'São Paulo', 'Rua Das Mentiras', '222', 'Lucimar Pereira', '000000000'),
	(2, '222.333.444-45', 'maria_falsa@loja.com', 'Jardim Planalto', '56.665-656', 'Sao Paulo', 'Casa de Esquina', 'São Paulo', 'Rua Das Maritacas', '222', 'Maria Falsa', '12345678910');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.compra: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` (`codigo`, `codigo_fornecedor`) VALUES
	(1, 1),
	(2, 2),
	(3, 2);
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.compra_itens_compra: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `compra_itens_compra` DISABLE KEYS */;
INSERT INTO `compra_itens_compra` (`compra_codigo`, `itens_compra_codigo`) VALUES
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `compra_itens_compra` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.estoque: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` (`codigo`, `quantidade`, `valor_de_venda`, `produto_codigo`) VALUES
	(1, 3, 2, 1),
	(2, 8, 2.9, 3),
	(3,10,0,2);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.fornecedor: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` (`codigo`, `cnpj`, `email`, `bairro`, `cep`, `cidade`, `complemento`, `estado`, `logradouro`, `numero`, `nome`, `telefone`) VALUES
	(1, '123456789/0001', 'consult@pepsi.com', 'Das Aguias', '18005', 'Sorocaba', 'Portaria 20C', 'São Paulo', 'Rua Das Aves', '333', 'Pepsi', '1598135-4751'),
	(2, '123456745/0001', 'ju@dogs.com', 'São Guilherme', '17701', 'Votorantim', NULL, 'São Paulo', 'Rua Das Maritacas', '4', 'Juliano Ukman', '1532457826'),
	(3, '123456789/0001', 'sadia@sadia.com', 'Das Aguias', '18001455', 'Sorocaba', '01B', 'São Paulo', 'Rua Das Linguiças', '123', 'Sadia', '1598135-4444');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.itens_compra: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `itens_compra` DISABLE KEYS */;
INSERT INTO `itens_compra` (`codigo`, `quantidade`, `valor`, `produto_codigo`) VALUES
	(1, 3, 3, 1),
	(2, 5, 2, 1),
	(3, 10, 2.5, 3);
/*!40000 ALTER TABLE `itens_compra` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.itens_venda: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `itens_venda` DISABLE KEYS */;
INSERT INTO `itens_venda` (`codigo`, `quantidade`, `valor`, `produto_codigo`) VALUES
	(1, 2, 4.5, 1),
	(2, 2, 5.625, 3);
/*!40000 ALTER TABLE `itens_venda` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.produto: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` (`codigo`, `descricao`, `nome`, `unidade`) VALUES
	(1, 'Oral-b', 'Escova de Dentes', 'un.'),
	(2, 'Phebo', 'Sabonete', 'un.'),
	(3, 'Sadia', 'Linguiça', 'kg.');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.venda: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` (`codigo`, `status`, `codigo_cliente`) VALUES
	(1, 'RECEBIDO', 1),
	(2, 'PENDENTE', 1);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;

-- Copiando dados para a tabela loja_dogs.venda_itens_venda: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `venda_itens_venda` DISABLE KEYS */;
INSERT INTO `venda_itens_venda` (`venda_codigo`, `itens_venda_codigo`) VALUES
	(1, 1),
	(2, 2);
/*!40000 ALTER TABLE `venda_itens_venda` ENABLE KEYS */;

----Usuario
INSERT INTO usuario (codigo, nome, email, senha) VALUES (1,"Loja", "loja@loja.com", "$2a$10$yNRR9XjRFlb4N.e1qWvStu78NKI7xgvETgTzcvlVjthMaBUch0bqq");
INSERT INTO usuario (codigo, nome, email, senha) VALUES (2,"Cliente", "cliente@cliente.com", "$2a$10$yNRR9XjRFlb4N.e1qWvStu78NKI7xgvETgTzcvlVjthMaBUch0bqq");

----Perfil
INSERT INTO perfil (codigo, nome) VALUES (1, "Loja");
INSERT INTO perfil (codigo, nome) VALUES (2, "Cliente");

----Usuario_perfis
INSERT INTO usuario_perfis (codigo_usuario, codigo_perfis) VALUES (1, 1);
INSERT INTO usuario_perfis (codigo_usuario, codigo_perfis) VALUES (2, 2);

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

SET FOREIGN_KEY_CHECKS=1;
