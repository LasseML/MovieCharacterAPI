DELETE FROM movie_moviecharacter;
DELETE FROM movie_character;
DELETE FROM movie;
DELETE FROM franchise;


INSERT INTO franchise (id, deleted, description, name) VALUES
(1, 0, 
'a film series of three epic fantasy adventure films directed by Peter Jackson, based on the novel written by J. R. R. Tolkien.',
'The Lord of the Rings');

INSERT INTO franchise (id, deleted, description, name) VALUES
(2, 0,
'an American epic space opera media franchise created by George Lucas',
'Star Wars');

SELECT pg_catalog.setval('public.franchise_id_seq', 2, true);

INSERT INTO movie (id, deleted, franchise_id, director, genre, picture, release, title, trailer) VALUES 
(1, 0, 1, 
'Peter Jackson', 
'Action, Adventure, Drama', 
'https://upload.wikimedia.org/wikipedia/en/8/8a/The_Lord_of_the_Rings_The_Fellowship_of_the_Ring_%282001%29.jpg',
'2001-01-01',
'The Lord of the Rings: The Fellowship of the Ring',
'https://www.youtube.com/watch?v=V75dMMIW2B4');

INSERT INTO movie (id, deleted, franchise_id, director, genre, picture, release, title, trailer) VALUES 
(2, 0, 1, 
'Peter Jackson', 
'Action, Adventure, Drama', 
'https://upload.wikimedia.org/wikipedia/en/d/d0/Lord_of_the_Rings_-_The_Two_Towers_%282002%29.jpg',
'2002-01-01',
'The Lord of the Rings: The Two Towers',
'https://www.youtube.com/watch?v=LbfMDwc4azU');

INSERT INTO movie (id, deleted, franchise_id, director, genre, picture, release, title, trailer) VALUES 
(3, 0, 1, 
'Peter Jackson', 
'Action, Adventure, Drama', 
'https://upload.wikimedia.org/wikipedia/en/b/be/The_Lord_of_the_Rings_-_The_Return_of_the_King_%282003%29.jpg',
'2003-01-01',
'The Lord of the Rings: The Return of the King',
'https://www.youtube.com/watch?v=r5X-hFf6Bwo');

INSERT INTO movie (id, deleted, franchise_id, director, genre, picture, release, title, trailer) VALUES 
(4, 0, 2, 
'George Lucas', 
'Action, Adventure, Fantasy', 
'https://static.wikia.nocookie.net/starwars/images/0/06/Star_Wars_Style_A_poster_1977.jpg/revision/latest?cb=20100708051712',
'1977-01-01',
'Star Wars: Episode IV - A New Hope',
'https://www.youtube.com/watch?v=vZ734NWnAHA');

INSERT INTO movie (id, deleted, franchise_id, director, genre, picture, release, title, trailer) VALUES 
(5, 0, 2, 
'Irvin Kershner', 
'Action, Adventure, Fantasy', 
'https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg',
'1980-01-01',
'Star Wars: Episode V - The Empire Strikes Back',
'https://www.youtube.com/watch?v=JNwNXF9Y6kY');

INSERT INTO movie (id, deleted, franchise_id, director, genre, picture, release, title, trailer) VALUES 
(6, 0, 2, 
'George Lucas', 
'Action, Adventure, Fantasy', 
'https://m.media-amazon.com/images/M/MV5BOWZlMjFiYzgtMTUzNC00Y2IzLTk1NTMtZmNhMTczNTk0ODk1XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg',
'1983-01-01',
'Star Wars: Return of the Jedi',
'https://www.youtube.com/watch?v=7L8p7_SLzvU');


SELECT pg_catalog.setval('public.movie_id_seq', 6, true);

INSERT INTO movie_character (id, deleted, alias, first_name, gender, last_name, picture) VALUES
(1, 0, '', 'Frodo', 'MALE', 'Baggins', 'https://static.wikia.nocookie.net/lotr/images/5/54/Untitledjk.png/revision/latest?cb=20130313174543');

INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (1, 1);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (1, 2);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (1, 3);

INSERT INTO movie_character (id, deleted, alias, first_name, gender, last_name, picture) VALUES
(2, 0, '', 'Sam', 'MALE', 'Gamgee', 'https://static.wikia.nocookie.net/lotr/images/2/20/Sam.jpg/revision/latest/top-crop/width/360/height/450?cb=20070623123241');

INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (2, 1);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (2, 2);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (2, 3);

INSERT INTO movie_character (id, deleted, alias, first_name, gender, last_name, picture) VALUES
(3, 0, '', 'Legolas', 'MALE', '', 'https://static.wikia.nocookie.net/lotr/images/3/33/Legolas_-_in_Two_Towers.PNG/revision/latest/top-crop/width/360/height/360?cb=20120916035151');

INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (3, 1);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (3, 2);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (3, 3);

INSERT INTO movie_character (id, deleted, alias, first_name, gender, last_name, picture) VALUES
(4, 0, '', 'Luke', 'MALE', 'Skywalker', 'https://upload.wikimedia.org/wikipedia/en/9/9b/Luke_Skywalker.png');

INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (4, 4);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (4, 5);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (4, 6);

INSERT INTO movie_character (id, deleted, alias, first_name, gender, last_name, picture) VALUES
(5, 0, '', 'Han', 'MALE', 'Solo', 'https://upload.wikimedia.org/wikipedia/en/b/be/Han_Solo_depicted_in_promotional_image_for_Star_Wars_%281977%29.jpg');

INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (5, 4);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (5, 5);
INSERT INTO public.movie_moviecharacter (movie_character_id, movie_id) VALUES (5, 6);

SELECT pg_catalog.setval('public.movie_character_id_seq', 5, true);
