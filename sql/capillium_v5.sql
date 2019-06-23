DROP DATABASE IF EXISTS `capillium`;
CREATE DATABASE IF NOT EXISTS `capillium` DEFAULT CHARACTER SET utf8 ;

DROP user if EXISTS 'web_app'@'localhost';
CREATE USER 'web_app'@'localhost' identified by '1234';
GRANT ALL PRIVILEGES on capillium.* to 'web_app'@'localhost';

USE `capillium` ;
-- -----------------------------------------------------
-- Table `Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Usuario` ;

CREATE TABLE IF NOT EXISTS `Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `login` CHAR(32) NOT NULL ,
  `senha` CHAR(32) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Cliente` ;

CREATE TABLE IF NOT EXISTS `CLIENTE` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `telefone` VARCHAR(15) NULL ,
  `email` VARCHAR(45) NULL,
  `cpf` VARCHAR(15) NULL ,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  INDEX `fk_Cliente_Usuario1_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_Cliente_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Funcionario` ;

CREATE TABLE IF NOT EXISTS `Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `matricula` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idFuncionario`),
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Atendimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Atendimento` ;

CREATE TABLE IF NOT EXISTS `Atendimento` (
  `idAtendimento` INT NOT NULL AUTO_INCREMENT,
  `horarioInicio` TIME NOT NULL ,
  `horarioFim` TIME NOT NULL ,
  `valorTotal` DECIMAL(10,2) NOT NULL ,
  `dataAtendimento` DATE NOT NULL ,
  `idCliente` INT NOT NULL,
  `idFuncionario` INT NOT NULL,
  PRIMARY KEY (`idAtendimento`),
  INDEX `fk_Atendimento_Cliente1_idx` (`idCliente` ASC),
  INDEX `fk_Atendimento_Funcionario1_idx` (`idFuncionario` ASC),
  CONSTRAINT `fk_Atendimento_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Atendimento_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Servico` ;

CREATE TABLE IF NOT EXISTS `Servico` (
  `idServico` INT NOT NULL AUTO_INCREMENT,
  `descricao` TEXT NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `tempoMedioAtendimento` TIME NOT NULL,
  `se_suspenso` CHAR(1) NULL,
  PRIMARY KEY (`idServico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ausencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Ausencia` ;

CREATE TABLE IF NOT EXISTS `Ausencia` (
  `idAusencia` INT NOT NULL AUTO_INCREMENT,
  `dataAusencia` DATE NOT NULL,
  `horaAusenciaInicio` TIME NOT NULL,
  `horaAusenciaFim` TIME NOT NULL,
  `Motivo` TEXT NULL,
  `idFuncionario` INT NOT NULL,
  PRIMARY KEY (`idAusencia`, `idFuncionario`),
  INDEX `fk_Ausencia_Funcionario1_idx` (`idFuncionario` ASC),
  CONSTRAINT `fk_Ausencia_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Atendimento_Servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Atendimento_Servico` ;

CREATE TABLE IF NOT EXISTS `Atendimento_Servico` (
  `idAtendimento` INT NOT NULL,
  `idServico` INT NOT NULL,
  PRIMARY KEY (`idAtendimento`, `idServico`),
  INDEX `fk_Atendimento_has_Servico_Servico1_idx` (`idServico` ASC),
  INDEX `fk_Atendimento_has_Servico_Atendimento1_idx` (`idAtendimento` ASC),
  CONSTRAINT `fk_Atendimento_has_Servico_Atendimento1`
    FOREIGN KEY (`idAtendimento`)
    REFERENCES `Atendimento` (`idAtendimento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Atendimento_has_Servico_Servico1`
    FOREIGN KEY (`idServico`)
    REFERENCES `Servico` (`idServico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Funcionario_Servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Funcionario_Servico` ;

CREATE TABLE IF NOT EXISTS `Funcionario_Servico` (
  `idServico` INT NOT NULL,
  `idFuncionario` INT NOT NULL,
  PRIMARY KEY (`idServico`, `idFuncionario`),
  INDEX `fk_Funcionario_Servico_Servico1_idx` (`idServico` ASC),
  INDEX `fk_Funcionario_Servico_Funcionario1_idx` (`idFuncionario` ASC),
  CONSTRAINT `fk_Funcionario_Servico_Servico1`
    FOREIGN KEY (`idServico`)
    REFERENCES `Servico` (`idServico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_Servico_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into usuario values (1, MD5('capillium'),md5('admin')); 

delimiter $$
	drop trigger if exists calcula_atendimento_servico $$
    
	create trigger calcula_atendimento_servico after insert 
    on atendimento_servico for each row
    begin
		DECLARE l_valor decimal;
        DECLARE l_tempoMeioAtendimento time;
		
        select valor, tempoMedioAtendimento into l_valor,l_tempoMeioAtendimento 
        from servico where idServico = new.idServico;
        
		update atendimento 
        set horarioFim = horarioFim + l_tempoMeioAtendimento,
			valorTotal = valorTotal + l_valor
        where idAtendimento = new.idAtendimento;
        
    end$$

delimiter ;