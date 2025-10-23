set echo on
SPOOL D:\Documentos\Faculdade\2024.1\projeto02bancodedados\projeto02.txt
prompt ' Projeto02 '
prompt ' Francisco Losada Totaro - 10364673 '

--Apagar tabelas
Drop table Projetos cascade constraint;
Drop table Clientes cascade constraint;
Drop table Arquitetos_Associados cascade constraint;
Drop table Arquitetos cascade constraint;
Drop table Admistrativo cascade constraint;
Drop table Tercerizados cascade constraint;
Drop table Construtora cascade constraint;
Drop table Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados cascade constraint;
Drop table Relacionamento_1 cascade constraint;
Drop table Relacionamento_4 cascade constraint;
Drop table Relacionamento_2 cascade constraint;

--criar Tabelas

CREATE TABLE Projetos (
codigo_projeto INTEGER PRIMARY KEY,
nome_projeto VARCHAR (20),
valor_projeto INTEGER,
duracao_projeto INTEGER
);
CREATE TABLE Clientes (
cod_cliente INTEGER PRIMARY KEY,
nome_cliente VARCHAR (20),
academia_cliente VARCHAR (20)
);
CREATE TABLE Arquitetos_Associados (
cod_socio INTEGER PRIMARY KEY,
nome_socio VARCHAR (20)
);
CREATE TABLE Arquitetos (
cod_arq INTEGER PRIMARY KEY,
nome_arq VARCHAR (20),
salario_arq INTEGER
);
CREATE TABLE Admistrativo (
cod_adm INTEGER PRIMARY KEY,
nome_adm VARCHAR (20),
salario_adm INTEGER,
funcao_adm VARCHAR (20)
);
CREATE TABLE Tercerizados (
cod_terceirizados INTEGER PRIMARY KEY,
empresa_terceirizados VARCHAR (20),
funcao_terceirizados VARCHAR (20),
valor_terceirizados INTEGER
);
CREATE TABLE Construtora (
cod_contrutora INTEGER PRIMARY KEY,
nome_construtora VARCHAR (20),
valor_construtora INTEGER
);
CREATE TABLE Relacionamento_2 (
cod_socio INTEGER,
cod_projeto INTEGER
);
CREATE TABLE
Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados (
cod_socio INTEGER,
cod_arq INTEGER,
cod_contrutora INTEGER,
cod_terceirizados INTEGER
);
CREATE TABLE Relacionamento_1 (
cod_cliente INTEGER,
codigo_projeto INTEGER
);
CREATE TABLE Relacionamento_4 (
cod_adm INTEGER,
cod_cliente INTEGER
);

-- Dados

insert into Projetos values (1, 'AAA', 2400, 8);
insert into Projetos values (2, 'BBB', 4500, 13);
insert into Projetos values (3, 'CCC', 1500, 4);
insert into Projetos values (4, 'DDD', 3600, 10);
insert into Projetos values (5, 'EEE', 5760, 20);

INSERT INTO Clientes VALUES (11, 'Antonio', 'Afit');
INSERT INTO Clientes VALUES (22, 'Bernardo', 'Bfit');
INSERT INTO Clientes VALUES (33, 'Claudia', 'Cfit');
INSERT INTO Clientes VALUES (44, 'Dario', 'Dfit');
INSERT INTO Clientes VALUES (55, 'Enzo', 'Efit');

INSERT INTO Arquitetos_Associados VALUES (111, 'Socio1');
INSERT INTO Arquitetos_Associados VALUES (222, 'Socio2');
INSERT INTO Arquitetos_Associados VALUES (333, 'Socio3');
INSERT INTO Arquitetos_Associados VALUES (444, 'Socio4');
INSERT INTO Arquitetos_Associados VALUES (555, 'Socio5');

INSERT INTO Arquitetos VALUES (1111, 'Arq1', 10000);
INSERT INTO Arquitetos VALUES (2222, 'Arq2', 15000);
INSERT INTO Arquitetos VALUES (3333, 'Arq3', 8000);
INSERT INTO Arquitetos VALUES (4444, 'Arq4', 7600);
INSERT INTO Arquitetos VALUES (5555, 'Arq5', 9990);

INSERT INTO Admistrativo VALUES (11111, 'adm1', 3000, 'Vendas');
INSERT INTO Admistrativo VALUES (22222, 'adm2', 5000, 'Financeiro');
INSERT INTO Admistrativo VALUES (33333, 'adm3', 1500, 'Marketing');
INSERT INTO Admistrativo VALUES (44444, 'adm4', 3000, 'Vendas');

INSERT INTO Tercerizados VALUES (111111, 'comp1', 'Desenho', 3350);
INSERT INTO Tercerizados VALUES (222222, 'comp2', 'Maquete', 10000);

INSERT INTO Construtora VALUES (1111111, 'cons1', 15000);
INSERT INTO Construtora VALUES (2222222, 'cons2', 16700);

INSERT INTO Relacionamento_1 VALUES (11, 2);
INSERT INTO Relacionamento_1 VALUES (22, 1);
INSERT INTO Relacionamento_1 VALUES (33, 3);
INSERT INTO Relacionamento_1 VALUES (44, 5);
INSERT INTO Relacionamento_1 VALUES (55, 4);

INSERT INTO Relacionamento_2 VALUES (111, 4);
INSERT INTO Relacionamento_2 VALUES (222, 3);
INSERT INTO Relacionamento_2 VALUES (333, 1);
INSERT INTO Relacionamento_2 VALUES (444, 2);
INSERT INTO Relacionamento_2 VALUES (555, 5);

INSERT INTO Relacionamento_4 VALUES (11111, 11);
INSERT INTO Relacionamento_4 VALUES (11111, 22);
INSERT INTO Relacionamento_4 VALUES (11111, 55);
INSERT INTO Relacionamento_4 VALUES (44444, 33);
INSERT INTO Relacionamento_4 VALUES (44444, 44);

INSERT INTO Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados VALUES (111, 2222, 1111111, 111111);
INSERT INTO Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados VALUES (222, 1111, 1111111, 222222);
INSERT INTO Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados VALUES (333, 5555, 2222222, 222222);
INSERT INTO Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados VALUES (444, 3333, 1111111, 111111);
INSERT INTO Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados VALUES (555, 4444, 2222222, 111111);
COMMIT;


-- Tabelas com Dados


SELECT * FROM Projetos;
SELECT * FROM Clientes;
SELECT * FROM Arquitetos_Associados;
SELECT * FROM Arquitetos;
SELECT * FROM Admistrativo;
SELECT * FROM Tercerizados;
SELECT * FROM Construtora;
SELECT * FROM Relacionamento_1;
SELECT * FROM Relacionamento_2;
SELECT * FROM Relacionamento_4;
SELECT * FROM Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados;


--Consultas


SELECT Clientes.nome_cliente, Projetos.nome_projeto
FROM Clientes
INNER JOIN Relacionamento_1 ON Clientes.cod_cliente = Relacionamento_1.cod_cliente
INNER JOIN Projetos ON Relacionamento_1.codigo_projeto = Projetos.codigo_projeto;

SELECT Clientes.academia_cliente, SUM(Projetos.valor_projeto) AS valor_total_projetos
FROM Clientes
INNER JOIN Relacionamento_1 ON Clientes.cod_cliente = Relacionamento_1.cod_cliente
INNER JOIN Projetos ON Relacionamento_1.codigo_projeto = Projetos.codigo_projeto
GROUP BY Clientes.academia_cliente;

SELECT Projetos.nome_projeto, Relacionamento_2.cod_socio
FROM Projetos
LEFT JOIN Relacionamento_2 ON Projetos.codigo_projeto = Relacionamento_2.cod_projeto;

SELECT Clientes.nome_cliente, Projetos.nome_projeto, Arquitetos_Associados.nome_socio, Construtora.nome_construtora
FROM Clientes
INNER JOIN Relacionamento_1 ON Clientes.cod_cliente = Relacionamento_1.cod_cliente
INNER JOIN Projetos ON Relacionamento_1.codigo_projeto = Projetos.codigo_projeto
INNER JOIN Relacionamento_2 ON Projetos.codigo_projeto = Relacionamento_2.cod_projeto
INNER JOIN Arquitetos_Associados ON Relacionamento_2.cod_socio = Arquitetos_Associados.cod_socio
INNER JOIN Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados ON Arquitetos_Associados.cod_socio = Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados.cod_socio
INNER JOIN Construtora ON Relacionamento_3_Arquitetos_Associados_Arquitetos_Contrutora_Tercerizados.cod_contrutora = Construtora.cod_contrutora;
set echo off
SPOOL OFF;

