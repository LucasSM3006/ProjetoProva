create table camiseta(
	codigo serial primary key,
	modelo varchar(50),
	cor varchar(50),
	descricao varchar(100),
	custo float,
	categoria varchar(50),
	disponibilidade boolean
);