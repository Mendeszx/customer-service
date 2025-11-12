CREATE SCHEMA IF NOT EXISTS customer;

SET search_path TO customer;

CREATE TABLE tb_account(
    "account_id" UUID NOT NULL,
    "customer_id" UUID NOT NULL,
    "active" BOOLEAN NOT NULL,
    "account_type" VARCHAR(100) NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NULL
);
ALTER TABLE
    tb_account ADD PRIMARY KEY("account_id");
ALTER TABLE
    tb_account ADD CONSTRAINT "account_customer_id_unique" UNIQUE("customer_id");
CREATE INDEX "account_active_index" ON
    tb_account("active");
CREATE INDEX "account_account_type_index" ON
    tb_account("account_type");
CREATE INDEX "account_created_at_index" ON
    tb_account("created_at");
COMMENT
ON COLUMN
    tb_account."account_id" IS 'Identificador único da conta do cliente.';
COMMENT
ON COLUMN
    tb_account."customer_id" IS 'Identificador único do cliente.';
COMMENT
ON COLUMN
    tb_account."active" IS 'Se a conta está ativa ou inativa.';
COMMENT
ON COLUMN
    tb_account."account_type" IS 'Nivel do perfil de assinatura da conta.';
COMMENT
ON COLUMN
    tb_account."created_at" IS 'Data e hora de criação.';
COMMENT
ON COLUMN
    tb_account."updated_at" IS 'Data e hora de atualização.';

CREATE TABLE "tb_information"(
    "customer_id" UUID NOT NULL,
    "keycloak_id" UUID NULL,
    "email" VARCHAR(255) NOT NULL,
    "name" VARCHAR(80) NOT NULL,
    "phone" VARCHAR(20) NOT NULL,
    "birth_date" DATE NOT NULL,
    "gender" VARCHAR(20) NOT NULL,
    "heard_about" VARCHAR(100) NULL,
    "country" VARCHAR(2) NULL,
    "timezone" VARCHAR(50) NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NULL
);
ALTER TABLE
    "tb_information" ADD PRIMARY KEY("customer_id");
ALTER TABLE
    "tb_information" ADD CONSTRAINT "tb_information_keycloak_id_unique" UNIQUE("keycloak_id");
ALTER TABLE
    "tb_information" ADD CONSTRAINT "tb_information_email_unique" UNIQUE("email");
ALTER TABLE
    "tb_information" ADD CONSTRAINT "tb_information_phone_unique" UNIQUE("phone");
CREATE INDEX "tb_information_birth_date_index" ON
    "tb_information"("birth_date");
CREATE INDEX "tb_information_gender_index" ON
    "tb_information"("gender");
CREATE INDEX "tb_information_heard_about_index" ON
    "tb_information"("heard_about");
CREATE INDEX "tb_information_country_index" ON
    "tb_information"("country");
CREATE INDEX "tb_information_created_at_index" ON
    "tb_information"("created_at");
COMMENT
ON COLUMN
    "tb_information"."customer_id" IS 'Identificador único do cliente.';
COMMENT
ON COLUMN
    "tb_information"."keycloak_id" IS 'Identificador único do keycloak do cliente.';
COMMENT
ON COLUMN
    "tb_information"."email" IS 'Email.';
COMMENT
ON COLUMN
    "tb_information"."name" IS 'Nome.';
COMMENT
ON COLUMN
    "tb_information"."phone" IS 'Número do celular.';
COMMENT
ON COLUMN
    "tb_information"."birth_date" IS 'Data de nascimento.';
COMMENT
ON COLUMN
    "tb_information"."gender" IS 'Gênero.';
COMMENT
ON COLUMN
    "tb_information"."heard_about" IS 'Como ficou sabendo do aplicativo.';
COMMENT
ON COLUMN
    "tb_information"."country" IS 'País.';
COMMENT
ON COLUMN
    "tb_information"."timezone" IS 'Fuso horário.';
COMMENT
ON COLUMN
    "tb_information"."created_at" IS 'Data e hora de criação.';
COMMENT
ON COLUMN
    "tb_information"."updated_at" IS 'Data e hora de atualização.';


CREATE TABLE tb_consent(
    "consent_id" UUID NOT NULL,
    "customer_id" UUID NOT NULL,
    "accept" BOOLEAN NOT NULL,
    "version" VARCHAR(10) NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NULL
);
ALTER TABLE
    tb_consent ADD PRIMARY KEY("consent_id");
ALTER TABLE
    tb_consent ADD CONSTRAINT "consent_customer_id_unique" UNIQUE("customer_id");
CREATE INDEX "consent_accept_index" ON
    tb_consent("accept");
CREATE INDEX "consent_version_index" ON
    tb_consent("version");
COMMENT
ON COLUMN
    tb_consent."consent_id" IS 'Identificador único do termo de consentimento e política de privacidade.';
COMMENT
ON COLUMN
    tb_consent."customer_id" IS 'Identificador único do cliente.';
COMMENT
ON COLUMN
    tb_consent."accept" IS 'Se os termos de consentimento e políticas de privacidade foram aprovados.';
COMMENT
ON COLUMN
    tb_consent."version" IS 'Versão dos termos de consentimento e políticas de privacidade que foi aceita.';
COMMENT
ON COLUMN
    tb_consent."created_at" IS 'Data e hora de criação.';
COMMENT
ON COLUMN
    tb_consent."updated_at" IS 'Data e hora de atualização.';
ALTER TABLE
    tb_consent ADD CONSTRAINT "consent_customer_id_foreign" FOREIGN KEY("customer_id") REFERENCES "tb_information"("customer_id");
ALTER TABLE
    tb_account ADD CONSTRAINT "account_customer_id_foreign" FOREIGN KEY("customer_id") REFERENCES "tb_information"("customer_id");