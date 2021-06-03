--Categorias
insert into categoria_model (nome_categoria)
select * from (select "Casa") AS tmp where not exists 
( select nome_categoria from categoria_model where nome_categoria = "Casa") limit 1;

insert into categoria_model (nome_categoria)
select * from (select "Prédio") AS tmp where not exists 
( select nome_categoria from categoria_model where nome_categoria = "Prédio") limit 1;

--Negocio 
insert into negocio_model ( nome_negocio) 
select * from (select "Aluguel") AS tmp where not exists
 (select nome_negocio from negocio_model where nome_negocio = "Aluguel") limit 1;

insert into negocio_model ( nome_negocio) 
select * from (select "Venda") AS tmp where not exists
 (select nome_negocio from negocio_model where nome_negocio = "Venda") limit 1;

--Quarto
insert into quarto_model (quantidade_quarto) 
select * from (select "1") AS tmp where not exists
(select quantidade_quarto from quarto_model where quantidade_quarto = "1") limit 1;

insert into quarto_model (quantidade_quarto) 
select * from (select "2") AS tmp where not exists
(select quantidade_quarto from quarto_model where quantidade_quarto = "2") limit 1;

--Estado
insert into estado_model (nome_estado, uf_estado) 
select * from (select "São Paulo", "SP") AS tmp where not exists
(select nome_estado from estado_model where nome_estado = "São Paulo") limit 1;

--Municipio
insert into municipio_model (nome_municipio, estado_codigo) 
select * from (select "Sorocaba", 1) AS tmp where not exists
(select nome_municipio from municipio_model where nome_municipio = "Sorocaba") limit 1;


--Bairro
replace into bairro_model values (1,"Ana Maria", 1, 1);

--Imovel
replace into imovel_model values (1 ,1, 1, 2, 2);
replace into imovel_model values (2,1, 1, 1, "1");

