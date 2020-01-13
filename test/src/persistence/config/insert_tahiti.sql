--Coordinates Insertion
--coordinates of hotel
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (1,-17.5241581, -149.537856);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (2,-17.524874, -149.5454424);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (3,-17.5283088,-149.5626756);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (4,-17.5306755,-149.5691355);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (5,-17.533445,-149.5652248);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (6,-17.5411618,-149.5691798);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (7,-17.5429846,-149.5737088);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (8,-17.5434419,-149.5763124);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (9,-17.5448638,-149.5779101);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (10,-17.7437277,-149.3221794);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (11,-17.741306,-149.3280841);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (12,-17.8449623,-149.2688906);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (13,-17.8449623,-149.2688906);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (14,-17.7521178,-149.5213179);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (15,-17.7461328,-149.5481204);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (16,-17.5384702,-149.3636201);

--coordinates of site
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (17,-17.5402758,-149.5669825);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (18,-17.4942887,-149.4943525);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (19,-17.5448616,-149.5743084);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (20,-17.5214627,-149.5262248);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (21,-17.5375238,-149.5691559);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (22,-17.6970417,-149.5789915);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (23,-17.7451405,-149.5668903);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (24,-17.760616,-149.3904279);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (25,-17.5344679,-149.3994284);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (26,-17.5243789,-149.3904695);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (27,-17.515051,-149.5038297);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (28,-17.570925,-149.618736);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (29,-17.543147,-149.5738693);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (30,-17.6323661,-149.6138763);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (31,-17.5354486,-149.565312);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (32,-17.5571733,-149.5308777);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (33,-17.5222916,-149.5404661);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (34,-17.5860141,-149.6146481);
INSERT INTO coordinates(id_coordinates, latitude, longitude) VALUES (35,-17.730888,-149.330967);



--Transport Insertion
INSERT INTO transport(type, price) VALUES ();

--Site Insertion
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Notre Dame Cathedral', 'Historic', 0, 17);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Pointe Venus', 'Activity', 0, 18);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Temple Protestant de Paofai', 'Historic', 0, 19);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Tombe du roi Pomare V', 'Historic', 0, 20);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Safari Jeep', 'Activity', 81, 21);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Marae Arahurahu', 'Activity', 0, 22);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Grotte Maraa', 'Activity', 0, 23);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('jardin Vaipahi', 'Activity', 0, 24);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Cascade de Faarumai', 'Activity', 0, 25);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Arahoho', 'Activity', 0, 26);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Belvedere Tahara\'a', 'Activity', 0, 27);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Location Scooter', 'Activity', 39, 28);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Musee de la perle', 'Historic', 5, 29);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Musee de Tahiti et des Iles', 'Historic', 6, 30);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Tagaloa Tattoo', 'Historic', 0, 31);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Rainbow Park', 'Activity', 30, 32);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Api Dive', 'Activity', 50, 33);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Eleuthera', 'Activity', 150, 34);
INSERT INTO site(name, type, price, id_coordinates) VALUES ('Nui Diving', 'Activity', 49, 35);

--Hotel Insertion
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Residence Arahiri', 49, 'Plage Papeete', 1);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Royal Tahitien', 135, 'Plage Papeete', 2);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Manapiti Sea View', 106, 'Plage Papeete', 3);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Hotel Sarah Nui', 105, 'Plage Papeete', 4);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('5 Stars Apt', 106, 'Plage Papeete', 5);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Mahana Lodge', 28, 'Plage Papeete', 6);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Studio Te Tiare', 99, 'Nanu\'u', 7);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Le Maori', 169, 'Nanu\'u', 8);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Alizé', 118, 'Nanu\'u', 9);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Mitirapa', 186, 'Phaëton', 10);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('The Farm 689', 59, 'Phaëton', 11);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Vanira Lodge', 125, 'Teahupoo', 12);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Villa BO', 109, 'Teahupoo', 13);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Hitimoana', 92, 'Moana', 14);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Manomano', 29, 'Tiamao', 15);
INSERT INTO hotel(name, price, beach_name, id_coordinates) VALUES ('Le rocher', 273, 'Plage du Rocher', 16);





--Ride Insertion
INSERT INTO ride(departure_site, arrival_site, id_transport) VALUES ();