CREATE DATABASE IF NOT EXISTS dinosaur_db;
USE dinosaur_db;

CREATE TABLE IF NOT EXISTS usuario (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol      VARCHAR(20)  NOT NULL,
    activo   TINYINT(1)   NOT NULL
);

CREATE TABLE IF NOT EXISTS dinosaurio (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    tipo         VARCHAR(20)  NOT NULL,
    epoca        VARCHAR(20)  NOT NULL,
    imagen       VARCHAR(255),
    habitat      VARCHAR(255),
    alimentacion VARCHAR(255),
    tamanio      VARCHAR(100),
    curiosidades TEXT,
    descripcion  TEXT,
    publicado    TINYINT(1)   NOT NULL,
    autor_id     INT,
    FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS favorito (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id    INT NOT NULL,
    dinosaurio_id INT NOT NULL,
    UNIQUE KEY uq_favorito (usuario_id, dinosaurio_id),
    FOREIGN KEY (usuario_id)    REFERENCES usuario(id),
    FOREIGN KEY (dinosaurio_id) REFERENCES dinosaurio(id)
);

CREATE TABLE IF NOT EXISTS contribucion (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    titulo           VARCHAR(255) NOT NULL,
    tipo             VARCHAR(20)  NOT NULL,
    epoca            VARCHAR(20)  NOT NULL,
    contenido_html   TEXT         NOT NULL,
    estado           VARCHAR(20)  NOT NULL,
    fecha_creacion   DATETIME     NOT NULL,
    observacion_admin TEXT,
    usuario_id       INT          NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


INSERT INTO usuario (username, password, rol, activo) VALUES
('admin', '$2a$10$7QJ9zX1234567890abcdefabcdefabcdefabcdefabcdefabcdefab', 'ADMIN', 1);

INSERT INTO dinosaurio (nombre, tipo, epoca, imagen, habitat, alimentacion, tamanio, curiosidades, descripcion, publicado) VALUES
('Tyrannosaurus Rex', 'carnivoro', 'cretacico', 'https://upload.wikimedia.org/wikipedia/commons/9/96/Trex_Smithsonian.jpg', 'Bosques y llanuras de Norteamérica', 'Carnívoro, se alimentaba de herbívoros grandes', '12 metros de largo, 4 metros de alto', 'Tenía brazos muy cortos en proporción a su cuerpo', 'El T-Rex fue uno de los depredadores terrestres más grandes que jamás haya existido.', 1),
('Triceratops', 'herbivoro', 'cretacico', 'https://upload.wikimedia.org/wikipedia/commons/a/a5/Triceratops_liveDB.jpg', 'Llanuras de Norteamérica', 'Herbívoro, se alimentaba de plantas bajas', '9 metros de largo, 3 metros de alto', 'Sus tres cuernos podían usarse para defenderse de depredadores', 'El Triceratops es uno de los dinosaurios con cuernos más reconocibles del período Cretácico.', 1),
('Brachiosaurus', 'herbivoro', 'jurasico', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5b/Brachiosaurus_DB.jpg/800px-Brachiosaurus_DB.jpg', 'Llanuras y bosques de Norteamérica y África', 'Herbívoro, se alimentaba de hojas de árboles altos', '26 metros de largo, 13 metros de alto', 'Era uno de los dinosaurios más altos, podía alcanzar las copas de los árboles', 'El Brachiosaurus tenía el cuello extremadamente largo que le permitía alcanzar vegetación muy alta.', 1),
('Velociraptor', 'carnivoro', 'cretacico', 'https://upload.wikimedia.org/wikipedia/commons/e/e3/Velociraptor_dinoguy2.jpg', 'Desiertos y llanuras de Asia Central', 'Carnívoro, cazaba en manadas', '2 metros de largo, 0.5 metros de alto', 'Era mucho más pequeño de lo que se muestra en las películas', 'El Velociraptor era un cazador ágil e inteligente que probablemente tenía plumas.', 1),
('Stegosaurus', 'herbivoro', 'jurasico', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Stegosaurus_stenops_life_DB.jpg/800px-Stegosaurus_stenops_life_DB.jpg', 'Llanuras de Norteamérica y Europa', 'Herbívoro, se alimentaba de plantas bajas', '9 metros de largo, 4 metros de alto', 'Las placas de su lomo podían servir para regular la temperatura corporal', 'El Stegosaurus es famoso por las grandes placas óseas que recorrían su espalda.', 1);
