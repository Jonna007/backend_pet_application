CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       registration_date TIMESTAMP NOT NULL
);

CREATE TABLE pets (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      species VARCHAR(255) NOT NULL,
                      breed VARCHAR(255) NOT NULL,
                      age INTEGER NOT NULL,
                      ownerd_name VARCHAR(255) NOT NULL,
                      user_id INTEGER NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE vital_signs (
                             id SERIAL PRIMARY KEY,
                             temperature DOUBLE PRECISION NOT NULL,
                             heart_rate INTEGER NOT NULL,
                             date DATE NOT NULL,
                             time TIME NOT NULL,
                             device VARCHAR(255) NOT NULL,
                             pet_id INTEGER NOT NULL,
                             FOREIGN KEY (pet_id) REFERENCES pets(id)
);