CREATE TYPE game_type AS ENUM ('ONLINE', 'PHYSICAL');

CREATE TABLE games (
  id SERIAL PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  description TEXT,
  game_type game_type NOT NULL DEFAULT 'ONLINE',
  platform TEXT,
  min_age SMALLINT,
  image_url TEXT,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Exemples de jocs "online" i "physical"
INSERT INTO games (title, description, game_type, platform, min_age, image_url, created_at, updated_at)
VALUES
('League of Legends',
 'Joc multijugador en línia de tipus MOBA desenvolupat per Riot Games.',
 'ONLINE',
 'PC',
 13,
 'https://cdn2.unrealengine.com/lol-logo-rendered-350x100-350x100-a2f0d5492480.png?resize=1&w=480&h=270&quality=medium',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('The Legend of Zelda: Breath of the Wild',
 'Aventura de món obert desenvolupada per Nintendo.',
 'PHYSICAL',
 'Nintendo Switch',
 12,
 'null',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('FIFA 25',
 'Simulador de futbol amb modes en línia i fora de línia, desenvolupat per EA Sports.',
 'PHYSICAL',
 'PlayStation 5',
 3,
 null,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('Valorant',
 'Shooter tàctic 5v5 amb habilitats úniques, desenvolupat per Riot Games.',
 'ONLINE',
 'PC',
 16,
 null,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('Minecraft',
 'Joc de construcció i supervivència en un món obert.',
 'ONLINE',
 'PC / Consoles / Mòbil',
 7,
 null,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('God of War: Ragnarök',
 'Joc d’acció i aventura basat en la mitologia nòrdica.',
 'PHYSICAL',
 'PlayStation 5',
 18,
 null,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('Fortnite',
 'Battle Royale amb construcció i modes creatius.',
 'ONLINE',
 'PC / Consoles / Mòbil',
 12,
 null,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP),

('Mario Kart 8 Deluxe',
 'Joc de curses arcade amb personatges de l´univers Mario.',
 'PHYSICAL',
 'Nintendo Switch',
 3,
 null,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);
