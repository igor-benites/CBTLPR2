/*
 * TP04 - Script do banco aulajava (SQL Server)
 * Nome: Igor Flores
 */

CREATE DATABASE aulajava;
GO

USE aulajava;
GO

CREATE TABLE tbcargos (
    cd_cargo smallint NOT NULL PRIMARY KEY,
    ds_cargo char(20) NULL
);
GO

CREATE TABLE tbfuncs (
    cod_func decimal(9,0) NOT NULL PRIMARY KEY,
    nome_func char(30) NULL,
    sal_func money NULL,
    cod_cargo smallint NULL,
    CONSTRAINT FK_tbfuncs_tbcargos FOREIGN KEY (cod_cargo) REFERENCES tbcargos (cd_cargo)
);
GO

INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (1, 'Administrativo');
INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (2, 'Analista');
INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (3, 'Gerente');
INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (4, 'Programador');
GO

INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1, 'Marcelo', 2000.00, 1);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (2, 'Marcelo Santos', 3500.00, 2);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (3, 'Marcelo Oliveira', 8000.00, 3);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (4, 'Maria Souza', 2800.50, 1);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (5, 'Carlos Lima', 4200.00, 4);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (6, 'Ana Paula', 5100.00, 2);
GO
