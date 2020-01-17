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
VALUES (1, 'bus', 2, 1),
(2, 'boat', 15, 0);

-- Site Insertion
INSERT INTO site(id_site, name, type, price, id_coordinates)
VALUES (1,'Notre Dame Cathedral', 'historic', 0, 17),
(2,'Pointe Venus', 'activity', 0, 18),
(3,'Temple Protestant de Paofai', 'historic', 0, 19),
(4,'Tombe du roi Pomare V', 'historic', 0, 20),
(5,'Safari Jeep', 'activity', 81, 21),
(6,'Marae Arahurahu', 'activity', 0, 22),
(7,'Grotte Maraa', 'activity', 0, 23),
(8,'jardin Vaipahi', 'activity', 0, 24),
(9,'Cascade de Faarumai', 'activity', 0, 25),
(10,'Arahoho', 'activity', 0, 26),
(11,'Belvedere Tahara\'a', 'activity', 0, 27),
 (12,'Location Scooter', 'activity', 39, 28),
 (13,'Musee de la perle', 'historic', 5, 29),
 (14,'Musee de Tahiti et des Iles', 'historic', 6, 30),
 (15,'Tagaloa Tattoo', 'historic', 0, 31),
 (16,'Rainbow Park', 'activity', 30, 32),
 (17,'Api Dive', 'activity', 50, 33),
 (18,'Eleuthera', 'activity', 150, 34),
 (19,'Nui Diving', 'activity', 49, 35);

-- Hotel Insertion
INSERT INTO hotel(name, price, beach_name,lien_image, id_coordinates)
 VALUES ('Residence Arahiri', 49, 'Plage Papeete','https://r-cf.bstatic.com/images/hotel/max1280x900/205/205239933.jpg', 1),
 ('Royal Tahitien', 135, 'Plage Papeete','https://r-cf.bstatic.com/images/hotel/max1280x900/109/10953874.jpg', 2),
 ('Manapiti Sea View', 106, 'Plage Papeete','https://r-cf.bstatic.com/images/hotel/max1280x900/204/204361837.jpg', 3),
 ('Hotel Sarah Nui', 105, 'Plage Papeete','https://r-cf.bstatic.com/images/hotel/max1280x900/208/20834814.jpg', 4),
 ('5 Stars Apt', 106, 'Plage Papeete','https://q-cf.bstatic.com/images/hotel/max1280x900/223/223088100.jpg', 5),
 ('Mahana Lodge', 28, 'Plage Papeete','https://q-cf.bstatic.com/images/hotel/max1280x900/974/97407797.jpg', 6),
 ('Studio Te Tiare', 99, 'Nanu\'u','https://r-cf.bstatic.com/images/hotel/max1280x900/192/192169130.jpg', 7),
 ('Le Maori', 169, 'Nanu\'u','https://q-cf.bstatic.com/images/hotel/max1280x900/115/115219622.jpg', 8),
 ('Aliz�', 118, 'Nanu\'u','https://r-cf.bstatic.com/images/hotel/max1280x900/202/202045692.jpg', 9),
 ('Mitirapa', 186, 'Pha�ton','https://q-cf.bstatic.com/images/hotel/max1280x900/156/15670548.jpg', 10),
 ('The Farm 689', 59, 'Pha�ton','https://r-cf.bstatic.com/images/hotel/max1280x900/216/216287105.jpg', 11),
 ('Vanira Lodge', 125, 'Teahupoo','https://r-cf.bstatic.com/images/hotel/max1280x900/217/217386427.jpg', 12),
 ('Villa BO', 109, 'Teahupoo','https://r-cf.bstatic.com/images/hotel/max1280x900/129/129399410.jpg', 13),
 ('Hitimoana', 92, 'Moana','https://r-cf.bstatic.com/images/hotel/max1280x900/331/33188566.jpg', 14),
 ('Manomano', 29, 'Tiamao','https://r-cf.bstatic.com/images/hotel/max1280x900/203/203842514.jpg', 15),
 ('Le rocher', 273, 'Plage du Rocher','https://r-cf.bstatic.com/images/hotel/max1280x900/238/238074925.jpg', 16);