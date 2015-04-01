CREATE TABLE funcionario (
nome VARCHAR(50) NOT NULL,
dataNascimento DATE,
cpf INT NOT NULL,
endereco VARCHAR(50) NOT NULL,
sexo VARCHAR(50) NOT NULL,
salario INT NOT NULL,
datainicio DATE NOT NULL,
funcionario_cpf INT,
departamento_numero INT,
PRIMARY KEY (`cpf`)
);

CREATE TABLE departamento (
nome VARCHAR(50) NOT NULL,
numero INT AUTO_INCREMENT NOT NULL,
funcionario_cpf INT,
PRIMARY KEY (`numero`)
);

CREATE TABLE projeto (
nome VARCHAR(50) NOT NULL,
numero INT AUTO_INCREMENT NOT NULL,
localizacao VARCHAR(50),
PRIMARY KEY (`numero`)
);

CREATE TABLE dependente (
nome VARCHAR(50) NOT NULL,
sexo VARCHAR(50) NOT NULL,
parentecos VARCHAR(50) NOT NULL,
datanasc DATE,
funcionario_cpf INT,
PRIMARY KEY (`funcionario_cpf`)
);

CREATE TABLE trabalhaem (
funcionario_cpf INT,
projeto_numero INT,
horas FLOAT NOT NULL,
PRIMARY KEY (`funcionario_cpf`,`projeto_numero`)
);

CREATE TABLE departamento_localizacoes (
localizacoes VARCHAR(50),
departamento_numero INT,
PRIMARY KEY (`departamento_numero`)
);


ALTER TABLE `funcionario` ADD CONSTRAINT `fk0_funcionario` FOREIGN KEY ( `funcionario_cpf` ) REFERENCES `funcionario` ( `cpf` );
ALTER TABLE `funcionario` ADD CONSTRAINT `fk1_departamento` FOREIGN KEY ( `departamento_numero` ) REFERENCES `departamento` ( `numero` );
ALTER TABLE `departamento` ADD CONSTRAINT `fk2_funcionario` FOREIGN KEY ( `funcionario_cpf` ) REFERENCES `funcionario` ( `cpf` );
ALTER TABLE `dependente` ADD CONSTRAINT `fk3_funcionario` FOREIGN KEY ( `funcionario_cpf` ) REFERENCES `funcionario` ( `cpf` );
ALTER TABLE `trabalhaem` ADD CONSTRAINT `fk4_funcionario` FOREIGN KEY ( `funcionario_cpf` ) REFERENCES `funcionario` ( `cpf` );
ALTER TABLE `trabalhaem` ADD CONSTRAINT `fk5_projeto` FOREIGN KEY ( `projeto_numero` ) REFERENCES `projeto` ( `numero` );
ALTER TABLE `departamento_localizacoes` ADD CONSTRAINT `fk6_departamento` FOREIGN KEY ( `departamento_numero` ) REFERENCES `departamento` ( `numero` );