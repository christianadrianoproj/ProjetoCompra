create database IF NOT EXISTS db_compra;
use db_compra;

CREATE TABLE IF NOT EXISTS tb_pessoa (
  id_pessoa int(11) NOT NULL AUTO_INCREMENT,
  data_nacimento date DEFAULT NULL,
  idade int default null,
  nome varchar(300) DEFAULT NULL,
  cpf_cnpj varchar(14) not null,
  identidade integer default null,
  inscricao_estadual integer default null,
  PRIMARY KEY (id_pessoa)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS tb_produto (
  id_produto int(11) NOT NULL AUTO_INCREMENT,
  descricao varchar(300) not NULL,
  valor float not null,
  PRIMARY KEY (id_produto)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2;

CREATE TABLE IF NOT EXISTS tb_compra (
  id_compra int(11) NOT NULL AUTO_INCREMENT,
  data_compra timestamp DEFAULT NULL,
  id_pessoa int not NULL, 
  foreign key FK_pessoa (id_pessoa) REFERENCES tb_pessoa (id_pessoa) ON DELETE RESTRICT,
  PRIMARY KEY (id_compra)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS tb_compra_item (
  id_compra_item int(11) NOT NULL AUTO_INCREMENT,
  descricao varchar(300) default null,
  quantidade integer not null,
  valor_unitario float not null,
  id_compra int not NULL, 
  id_produto int not NULL,
  foreign key FK_compra (id_compra) REFERENCES tb_compra (id_compra) ON DELETE RESTRICT,
  foreign key FK_produto (id_produto) REFERENCES tb_produto (id_produto) ON DELETE RESTRICT,
  PRIMARY KEY (id_compra_item)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;
