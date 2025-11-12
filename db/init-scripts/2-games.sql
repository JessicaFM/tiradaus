CREATE TYPE game_type AS ENUM ('ONLINE', 'PHYSICAL');

CREATE TABLE games (
  id SERIAL PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  description TEXT,
  game_type game_type NOT NULL DEFAULT 'ONLINE',
  platform TEXT,
  min_age SMALLINT,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Exemples de jocs "online" i "physical"
INSERT INTO games (title, description, game_type, platform, min_age, created_at, updated_at)
VALUES
('League of Legends',
 'Joc multijugador en línia de tipus MOBA desenvolupat per Riot Games.',
 'ONLINE',
 'PC',
 13,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('The Legend of Zelda: Breath of the Wild',
 'Aventura de món obert desenvolupada per Nintendo.',
 'PHYSICAL',
 'Nintendo Switch',
 12,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('FIFA 25',
 'Simulador de futbol amb modes en línia i fora de línia, desenvolupat per EA Sports.',
 'PHYSICAL',
 'PlayStation 5',
 3,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('Valorant',
 'Shooter tàctic 5v5 amb habilitats úniques, desenvolupat per Riot Games.',
 'ONLINE',
 'PC',
 16,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('Minecraft',
 'Joc de construcció i supervivència en un món obert.',
 'ONLINE',
 'PC / Consoles / Mòbil',
 7,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('God of War: Ragnarök',
 'Joc d’acció i aventura basat en la mitologia nòrdica.',
 'PHYSICAL',
 'PlayStation 5',
 18,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('Fortnite',
 'Battle Royale amb construcció i modes creatius.',
 'ONLINE',
 'PC / Consoles / Mòbil',
 12,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('Mario Kart 8 Deluxe',
 'Joc de curses arcade amb personatges de l’univers Mario.',
 'PHYSICAL',
 'Nintendo Switch',
 3,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

