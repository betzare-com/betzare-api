CREATE TABLE users (id UUID NOT NULL PRIMARY KEY, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL,
    gender VARCHAR(6) NOT NULL CHECK (gender='MALE' OR gender='male' OR gender='FEMALE' OR  gender='female'), is_confirmed BOOL DEFAULT 'f' NOT NULL);

