-- ============================================================
-- Script do banco de dados do projeto CRUD_PIZZA (PostgreSQL)
-- ============================================================
-- 1) Crie o banco (execute uma unica vez, conectado ao banco "postgres"):
--      CREATE DATABASE crud_pizza;
-- 2) Conecte-se ao banco crud_pizza e execute o restante deste arquivo.
-- ============================================================

-- Cria a tabela pizza, caso ela ainda não exista.
CREATE TABLE IF NOT EXISTS pizza (
    -- Código: SERIAL numera sozinho e PRIMARY KEY identifica cada linha.
    id_pizza        SERIAL PRIMARY KEY,
    -- Nome: texto de até 60 caracteres; NOT NULL torna obrigatório.
    nome_pizza      VARCHAR(60)    NOT NULL,
    -- Descrição: texto de até 200 caracteres, obrigatório.
    descricao_pizza VARCHAR(200)   NOT NULL,
    -- Tamanho: texto de até 20 caracteres, obrigatório.
    tamanho_pizza   VARCHAR(20)    NOT NULL,
    -- Preço: número com até 10 dígitos, sendo 2 decimais, obrigatório.
    preco_pizza     NUMERIC(10, 2) NOT NULL
);

-- Dados de exemplo
-- Insere as linhas abaixo nessas colunas. O código é gerado pelo banco.
INSERT INTO pizza (nome_pizza, descricao_pizza, tamanho_pizza, preco_pizza) VALUES
    -- Pizza Margherita: nome, descrição, tamanho e preço.
    ('Margherita',     'Molho de tomate, mussarela de bufala e manjericao', 'Grande',  54.90),
    -- Pizza Calabresa: nome, descrição, tamanho e preço.
    ('Calabresa',      'Calabresa fatiada, cebola e azeitonas pretas',      'Grande',  49.90),
    -- Pizza Quatro Queijos: nome, descrição, tamanho e preço.
    ('Quatro Queijos', 'Mussarela, provolone, gorgonzola e parmesao',       'Media',   52.00),
    -- Pizza Portuguesa: nome, descrição, tamanho e preço.
    ('Portuguesa',     'Presunto, ovos, cebola, ervilha e azeitonas',       'Pequena', 41.50);
