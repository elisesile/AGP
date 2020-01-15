-- Coordinates Insertion
-- coordinates of hotel
INSERT INTO coordinates(id_coordinates, latitude, longitude) 
VALUES (1,-17.5241581, -149.537856),
(2,-17.524874, -149.5454424),
(3,-17.5283088,-149.5626756),
(4,-17.5306755,-149.5691355),
(5,-17.533445,-149.5652248),
(6,-17.5411618,-149.5691798),
(7,-17.5429846,-149.5737088),
(8,-17.5434419,-149.5763124),
(9,-17.5448638,-149.5779101),
(10,-17.7437277,-149.3221794),
(11,-17.741306,-149.3280841),
(12,-17.8449623,-149.2688906),
(13,-17.8449623,-149.2688906),
(14,-17.7521178,-149.5213179),
(15,-17.7461328,-149.5481204),
(16,-17.5384702,-149.3636201);

-- coordinates of site
INSERT INTO coordinates(id_coordinates, latitude, longitude)
VALUES (17,-17.5402758,-149.5669825),
(18,-17.4942887,-149.4943525),
(19,-17.5448616,-149.5743084),
(20,-17.5214627,-149.5262248),
(21,-17.5375238,-149.5691559),
(22,-17.6970417,-149.5789915),
(23,-17.7451405,-149.5668903),
(24,-17.760616,-149.3904279),
(25,-17.5344679,-149.3994284),
(26,-17.5243789,-149.3904695),
(27,-17.515051,-149.5038297),
(28,-17.570925,-149.618736),
(29,-17.543147,-149.5738693),
(30,-17.6323661,-149.6138763),
(31,-17.5354486,-149.565312),
(32,-17.5571733,-149.5308777),
(33,-17.5222916,-149.5404661),
(34,-17.5860141,-149.6146481),
(35,-17.730888,-149.330967);



-- Transport Insertion
INSERT INTO transport(id_transport, type, price, is_per_km)
VALUES (1, 'Bus', 2, 1),
(2, 'Boat', 15, 0);

-- Site Insertion
INSERT INTO site(id_site, name, type, price, id_coordinates)
VALUES (1,'Notre Dame Cathedral', 'Historic', 0, 17),
(2,'Pointe Venus', 'Activity', 0, 18),
(3,'Temple Protestant de Paofai', 'Historic', 0, 19),
(4,'Tombe du roi Pomare V', 'Historic', 0, 20),
(5,'Safari Jeep', 'Activity', 81, 21),
(6,'Marae Arahurahu', 'Activity', 0, 22),
(7,'Grotte Maraa', 'Activity', 0, 23),
(8,'jardin Vaipahi', 'Activity', 0, 24),
(9,'Cascade de Faarumai', 'Activity', 0, 25),
(10,'Arahoho', 'Activity', 0, 26),
(11,'Belvedere Tahara\'a', 'Activity', 0, 27),
 (12,'Location Scooter', 'Activity', 39, 28),
 (13,'Musee de la perle', 'Historic', 5, 29),
 (14,'Musee de Tahiti et des Iles', 'Historic', 6, 30),
 (15,'Tagaloa Tattoo', 'Historic', 0, 31),
 (16,'Rainbow Park', 'Activity', 30, 32),
 (17,'Api Dive', 'Activity', 50, 33),
 (18,'Eleuthera', 'Activity', 150, 34),
 (19,'Nui Diving', 'Activity', 49, 35);

-- Hotel Insertion
INSERT INTO hotel(name, price, beach_name, id_coordinates)
 VALUES ('Residence Arahiri', 49, 'Plage Papeete', 1),
 ('Royal Tahitien', 135, 'Plage Papeete', 2),
 ('Manapiti Sea View', 106, 'Plage Papeete', 3),
 ('Hotel Sarah Nui', 105, 'Plage Papeete', 4),
 ('5 Stars Apt', 106, 'Plage Papeete', 5),
 ('Mahana Lodge', 28, 'Plage Papeete', 6),
 ('Studio Te Tiare', 99, 'Nanu\'u', 7),
 ('Le Maori', 169, 'Nanu\'u', 8),
 ('Alizé', 118, 'Nanu\'u', 9),
 ('Mitirapa', 186, 'Phaëton', 10),
 ('The Farm 689', 59, 'Phaëton', 11),
 ('Vanira Lodge', 125, 'Teahupoo', 12),
 ('Villa BO', 109, 'Teahupoo', 13),
 ('Hitimoana', 92, 'Moana', 14),
 ('Manomano', 29, 'Tiamao', 15),
 ('Le rocher', 273, 'Plage du Rocher', 16);


-- Ride Insertion
INSERT INTO ride(departure_site, arrival_site, id_transport)
VALUES (1,3,1),
(3,4,1),
(4,10,1),
(2,6,1),
(6,7,1),
(7,8,1),
(13,14,1),
(14,15,1),
(16,17,1),
(12,18,1),
(19,2,2),
(5,9,1),
(9,10,1),
(10,11,1),
(4,19,2);