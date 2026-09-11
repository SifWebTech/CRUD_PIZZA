-- ============================================================
-- Script do banco de dados do projeto CRUD_PIZZA (PostgreSQL)
-- ============================================================
-- 1) Crie o banco (execute uma unica vez, conectado ao banco "postgres"):
--      CREATE DATABASE crud_pizza;
-- 2) Conecte-se ao banco crud_pizza e execute o restante deste arquivo.
-- ============================================================

CREATE TABLE IF NOT EXISTS pizza (
    id_pizza        SERIAL PRIMARY KEY,
    nome_pizza      VARCHAR(60)    NOT NULL,
    descricao_pizza VARCHAR(200)   NOT NULL,
    tamanho_pizza   VARCHAR(20)    NOT NULL,
    preco_pizza     NUMERIC(10, 2) NOT NULL
);

-- Dados de exemplo
INSERT INTO pizza (nome_pizza, descricao_pizza, tamanho_pizza, preco_pizza) VALUES
    ('Margherita',     'Molho de tomate, mussarela de bufala e manjericao', 'Grande',  54.90),
    ('Calabresa',      'Calabresa fatiada, cebola e azeitonas pretas',      'Grande',  49.90),
    ('Quatro Queijos', 'Mussarela, provolone, gorgonzola e parmesao',       'Media',   52.00),
    ('Portuguesa',     'Presunto, ovos, cebola, ervilha e azeitonas',       'Pequena', 41.50);
