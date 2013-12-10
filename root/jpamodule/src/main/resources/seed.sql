insert into Address (city, country, line1, line2, line3, state, zipOrPin) values ('citya','countrya' , 'line1a', 'line2a', 'line3a', 'statea', 'zipa');
insert into User (address_id, birthday, emailId, firstName, gender, lastName, loginId, password, phone, title) values (1, "2006-02-02 15:35:00", 'anemaila@email.com', 'Fn', 'M', 'Ln', 'login1', 'password', 'phone1', 'zip1');
insert into Address (city, country, line1, line2, line3, state, zipOrPin) values ('cityb','countryb' , 'line1b', 'line2b', 'line3b', 'stateb', 'zipb');
insert into Contact (address_id, emailId, firstName, gender, lastName, phone, title) values (2, 'email2@email.com', 'Fna', 'M', 'Lna', 'phone2', 'Mr');
insert into User_Contact (User_id, contacts_id) values (1, 1);