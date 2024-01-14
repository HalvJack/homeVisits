INSERT INTO
    address (city, zip_Code, street, house_Number, flat_Number)
VALUES
    ('Jasło', '38-200', 'Dobrzańskiego', 35, 2),
    ('Kraków', '30-342', 'Mickiewicza', 99, 12),
    ('Rzeszów', '38-200', 'Leibniza', 75, 52),
    ('Krosno', '38-200', 'Słomnicka', 135, 23),
    ('Lipinki Łużyckie', '38-200', 'Słowackiego', 88, 7);
INSERT INTO
    doctor(name, surname, phone_Number, specialization, email, latitude, longitude)
VALUES
    ('Paweł', 'Hałucha', '506193161', 'Medycyna Pracy', 'pawhal@tlen.pl', '50.051838', '50.051838'),
    ('Edyta', 'Hałucha', '888668259', 'Diabetologia', 'edyta_med@wp.pl', '50.051838', '15.885622');
INSERT INTO
    patient(name, surname, pesel, date_Of_Birth, address_id, phone_Number, email)
VALUES
    ('Aleksander', 'Turocha', '00233100346', '2000-03-31', '4', '112345029', 'olek_turocha@gmail.com'),
    ('Jakub', 'Hałucha', '00242200996', '2000-04-22', '1', '535110460', 'kubaszekh@gmail.com');
INSERT INTO
    appointment(date, importance, comments, specialization, price, doctor_id, patient_id)
VALUES
    ('2023-03-24', 1, 'Mam bol glowy', 'internista',300, 2, 1),
    ('2023-04-14', 2, 'zapalenie pecherza', 'diabetolog',250, 1, 2);