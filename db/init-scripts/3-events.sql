CREATE type event_mode AS ENUM('REAL_LIFE', 'ONLINE');

CREATE TABLE events (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  description TEXT,
  start_date TIMESTAMPTZ NOT NULL,
  end_date TIMESTAMPTZ,
  event_mode event_mode NOT NULL DEFAULT 'ONLINE',
  game_id INT NOT NULL,
  location TEXT,
  players INT,
  FOREIGN KEY (game_id) REFERENCES games ON DELETE RESTRICT
);

INSERT INTO events (name, description, start_date, end_date, event_mode, game_id, location, players)
VALUES (
  'Torneig classificatori de League of Legends',
  'Torneig 5v5 en línia amb fase de grups i eliminatòries. Obert a equips amateurs.',
  '2025-02-01 18:00:00+01',
  '2025-02-01 22:00:00+01',
  'ONLINE',
  1,
  'En línia - servidor EUW',
  40
);

INSERT INTO events (name, description, start_date, end_date, event_mode, game_id, location, players)
VALUES (
  'Marató de The Legend of Zelda: Breath of the Wild',
  'Sessió presencial per compartir partides, trucs i exploració del món obert.',
  '2025-03-15 10:00:00+01',
  '2025-03-15 18:00:00+01',
  'REAL_LIFE',
  2,
  'Botiga Nintendo Fans, Barcelona',
  12
);

INSERT INTO events (name, description, start_date, end_date, event_mode, game_id, location, players)
VALUES (
  'Lliga local de FIFA 25',
  'Competició presencial setmanal amb classificació, inspirada en les lligues professionals.',
  '2025-04-01 19:00:00+02',
  '2025-04-01 22:00:00+02',
  'REAL_LIFE',
  3,
  'Centre Esportiu eSports Arena, Girona',
  24
);

INSERT INTO events (name, description, start_date, end_date, event_mode, game_id, location, players)
VALUES (
  'Torneig ràpid de Valorant 5v5',
  'Bracket d’eliminació directa amb partides al millor de 3. Ideal per equips competitius novells.',
  '2025-02-22 16:00:00+01',
  '2025-02-22 21:00:00+01',
  'ONLINE',
  4,
  'En línia - Discord + servidor EUW',
  32
);

INSERT INTO events (name, description, start_date, end_date, event_mode, game_id, location, players)
VALUES (
  'Nit creativa de Minecraft',
  'Esdeveniment cooperatiu en línia per construir una ciutat conjunta en un servidor privat.',
  '2025-03-29 20:00:00+01',
  '2025-03-29 23:30:00+01',
  'ONLINE',
  5,
  'En línia - servidor dedicat',
  20
);

INSERT INTO events (name, description, start_date, end_date, event_mode, game_id, location, players)
VALUES (
  'Sessió de God of War: Ragnarök per a adults',
  'Trobada presencial per comentar la història, bosses i estratègies en les dificultats més altes.',
  '2025-04-19 17:00:00+02',
  '2025-04-19 21:00:00+02',
  'REAL_LIFE',
  6,
  'Sala de jocs Valhalla, Tarragona',
  10
);

INSERT INTO events (name, description, start_date, end_date, event_mode, game_id, location, players)
VALUES (
  'Battle Royale Night de Fortnite',
  'Vetllada en línia amb partides personalitzades i minijocs creatius amb amics.',
  '2025-05-10 19:30:00+02',
  '2025-05-11 00:00:00+02',
  'ONLINE',
  7,
  'En línia - Epic Friends',
  50
);

INSERT INTO events (name, description, start_date, end_date, event_mode, game_id, location, players)
VALUES (
  'Campionat de Mario Kart 8 Deluxe',
  'Torneig presencial amb premis per als tres primers classificats i curses especials mirall.',
  '2025-06-08 11:00:00+02',
  '2025-06-08 15:00:00+02',
  'REAL_LIFE',
  8,
  'Associació Juvenil Pixelats, Lleida',
  16
);
