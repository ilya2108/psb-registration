CREATE ROLE super WITH LOGIN PASSWORD 'super';

CREATE ROLE superman WITH LOGIN PASSWORD 'superman' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE money_transaction_database;
GRANT ALL PRIVILEGES ON DATABASE money_transaction_database TO superman ;
GRANT ALL PRIVILEGES ON DATABASE money_transaction_database TO super;

CREATE ROLE super_registration WITH LOGIN PASSWORD 'superman' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE registration_database;
GRANT ALL PRIVILEGES ON DATABASE registration_database TO super_registration ;
GRANT ALL PRIVILEGES ON DATABASE registration_database TO super;
