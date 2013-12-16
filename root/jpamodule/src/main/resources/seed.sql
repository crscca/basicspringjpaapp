insert into Address (city, country, line1, line2, line3, state, zipOrPin) values ('citya','countrya' , 'line1a', 'line2a', 'line3a', 'statea', 'zipa');
insert into UserSimplified (address_id, birthday, emailId, firstName, lastName, loginId, password, phone, title) values (1, "2006-02-02 15:35:00", 'anemaila@email.com', 'Fn', 'Ln', 'login1', 'password', 'phone1', 'zip1');
insert into Address (city, country, line1, line2, line3, state, zipOrPin) values ('cityb','countryb' , 'line1b', 'line2b', 'line3b', 'stateb', 'zipb');
--insert into Contact (address_id, emailId, firstName, lastName, phone, title) values (2, 'email2@email.com', 'Fna',  'Lna', 'phone2', 'Mr');

--insert into User_Contact (User_id, contacts_id) values (1, 1);
insert into Additional (description, name) values ('NAMEX', 'namex');
insert into Additional (description, name) values ('NAMEY', 'namey');

insert into UserSimplified (address_id, birthday, emailId, firstName,  lastName, loginId, password, phone, title) values (NULL, "2006-02-02 15:35:00", 'anemaila@email.com', 'Fn',  'Ln', 'loginx', 'password', 'phone1', 'zip1');
insert into UserSimplified (address_id, birthday, emailId, firstName,  lastName, loginId, password, phone, title) values (NULL, "2006-02-02 15:35:00", 'anemaila@email.com', 'Fn',  'Ln', 'loginy', 'password', 'phone1', 'zip1');
insert into UserSimplified_Additional (UserSimplified_id, additionals_id) values (2, 1);
insert into UserSimplified_Additional (UserSimplified_id, additionals_id) values (3, 2);