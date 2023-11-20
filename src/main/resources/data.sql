INSERT INTO Blogger (name, age) VALUES ('Maykeya', 32);
INSERT INTO Blogger (name, age) VALUES ('Pluto', 36);
INSERT INTO Blogger (name, age) VALUES ('Treesome', 17);
INSERT INTO Blogger (name, age) VALUES ('Monyatta', 42);
INSERT INTO Stories (title, content, posted, blogger_id) VALUES ('Beautiful', 'That was a beautiful day...', '2022-10-14', (SELECT id FROM blogger WHERE name = 'Maykeya') );
INSERT INTO Stories (title, content, posted, blogger_id) VALUES ('Exciting', 'She was a beautiful girl...', '2022-11-17', (SELECT id FROM blogger WHERE name = 'Maykeya') );
INSERT INTO Stories (title, content, posted, blogger_id) VALUES ('Great hiking', 'We climbed up that mountain...', '2022-05-19', 2);
INSERT INTO Stories (title, content, posted, blogger_id) VALUES ('Salsa party', 'My friends and I went to a salsa party with cute girls...', CURRENT_DATE(), 3);