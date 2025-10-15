CREATE TABLE "informacao_pessoal"(
                                     "cliente_id" UUID NOT NULL,
                                     "cpf" VARCHAR(11) NOT NULL,
                                     "email" VARCHAR(255) NOT NULL,
                                     "nome" VARCHAR(80) NOT NULL,
                                     "telefone" VARCHAR(20) NOT NULL,
                                     "data_nascimento" DATE NOT NULL,
                                     "genero" VARCHAR(100) NOT NULL,
                                     "data_criacao" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
                                     "data_alteracao" TIMESTAMP(0) WITHOUT TIME ZONE NULL
);
CREATE INDEX "informacao_pessoal_nome_index" ON
    "informacao_pessoal"("nome");
CREATE INDEX "informacao_pessoal_genero_index" ON
    "informacao_pessoal"("genero");
CREATE INDEX "informacao_pessoal_data_nascimento_index" ON
    "informacao_pessoal"("data_nascimento");
ALTER TABLE
    "informacao_pessoal" ADD PRIMARY KEY("cliente_id");
ALTER TABLE
    "informacao_pessoal" ADD CONSTRAINT "informacao_pessoal_cpf_unique" UNIQUE("cpf");
ALTER TABLE
    "informacao_pessoal" ADD CONSTRAINT "informacao_pessoal_email_unique" UNIQUE("email");

CREATE TABLE "login"(
                        "login_id" UUID NOT NULL,
                        "fk_cliente_id" UUID NOT NULL,
                        "senha_hash" VARCHAR(255) NOT NULL,
                        "data_criacao" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
                        "data_alteracao" TIMESTAMP(0) WITHOUT TIME ZONE NULL
);
ALTER TABLE
    "login" ADD CONSTRAINT "login_fk_cliente_id_unique" UNIQUE("fk_cliente_id");
ALTER TABLE
    "login" ADD PRIMARY KEY("login_id");

CREATE TABLE "conta"(
                        "conta_id" UUID NOT NULL,
                        "fk_cliente_id" UUID NOT NULL,
                        "ativo" BOOLEAN NOT NULL,
                        "permissao" VARCHAR(100) NOT NULL,
                        "data_criacao" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
                        "data_alteracao" TIMESTAMP(0) WITHOUT TIME ZONE NULL
);
CREATE INDEX "conta_ativo_index" ON
    "conta"("ativo");
CREATE INDEX "conta_permissao_index" ON
    "conta"("permissao");
ALTER TABLE
    "conta" ADD CONSTRAINT "conta_fk_cliente_id_unique" UNIQUE("fk_cliente_id");
ALTER TABLE
    "conta" ADD PRIMARY KEY("conta_id");
ALTER TABLE
    "login" ADD CONSTRAINT "login_fk_cliente_id_foreign" FOREIGN KEY("fk_cliente_id") REFERENCES "informacao_pessoal"("cliente_id");
ALTER TABLE
    "conta" ADD CONSTRAINT "conta_fk_cliente_id_foreign" FOREIGN KEY("fk_cliente_id") REFERENCES "informacao_pessoal"("cliente_id");