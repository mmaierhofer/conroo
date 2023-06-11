CREATE TABLE conroo_challenge_db.time_slot (
    id bigint NOT NULL,
    slot_begin TIMESTAMP,
    slot_end TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE conroo_challenge_db.container (
    id bigint NOT NULL,
    container_number VARCHAR(255),
    container_weight MEDIUMINT,
    length SMALLINT,
    type VARCHAR(255),
    available_from TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE conroo_challenge_db.booking (
    id bigint NOT NULL AUTO_INCREMENT,
    created_date TIMESTAMP,
    container_id BIGINT,
    timeslot_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (container_id) REFERENCES container(id),
    FOREIGN KEY (timeslot_id) REFERENCES time_slot(id)
);



INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (1, 'TCLU4741524', 6534, 40, 'BOX', '2022-09-14 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (2, 'TLLU4546320', 5634, 40, 'HC', '2022-09-15 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (3, 'APHU7194748', 34566, 20, 'BOX', '2022-09-14 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (4, 'BEAU4089138', 12432, 40, 'BOX', '2022-09-14 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (5, 'TCNU3602270', 8834, 40, 'BOX', '2022-09-14 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (6, 'MRKU8591623', 5434, 40, 'BOX', '2022-09-15 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (7, 'APHU6939651', 1234, 40, 'HC', '2022-09-15 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (8, 'APZU3995438', 4234, 40, 'BOX', '2022-09-14 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (9, 'BMOU2361262', 5212, 20, 'BOX', '2022-09-16 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (10, 'BMOU2906887', 15342, 40, 'BOX', '2022-09-16 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (11, 'BMOU5074081', 25234, 20, 'OTOP', '2022-09-16 04:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (12, 'BSIU2854496', 1643, 20, 'OTOP', '2022-09-15 16:00:00');
INSERT INTO conroo_challenge_db.container (id, container_number, container_weight, length, type, available_from) VALUES (13, 'BSIU8041317', 1533, 20, 'BOX', '2022-09-15 16:00:00');

INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (1, '2022-09-15 00:00:00', '2022-09-15 02:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (2, '2022-09-15 02:00:00', '2022-09-15 04:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (3, '2022-09-15 04:00:00', '2022-09-15 06:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (4, '2022-09-15 06:00:00', '2022-09-15 08:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (5, '2022-09-15 08:00:00', '2022-09-15 10:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (6, '2022-09-15 10:00:00', '2022-09-15 12:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (7, '2022-09-15 12:00:00', '2022-09-15 14:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (8, '2022-09-15 14:00:00', '2022-09-15 16:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (9, '2022-09-15 16:00:00', '2022-09-15 18:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (10, '2022-09-15 18:00:00', '2022-09-15 20:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (11, '2022-09-15 20:00:00', '2022-09-15 22:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (12, '2022-09-15 22:00:00', '2022-09-16 00:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (13, '2022-09-16 00:00:00', '2022-09-16 02:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (14, '2022-09-16 02:00:00', '2022-09-16 04:00:00');
INSERT INTO conroo_challenge_db.time_slot (id, slot_begin, slot_end) VALUES (15, '2022-09-16 04:00:00', '2022-09-16 06:00:00');

INSERT INTO conroo_challenge_db.booking (id, created_date, container_id, timeslot_id) VALUES (1, '2022-09-14 17:54:23', 4, 2);