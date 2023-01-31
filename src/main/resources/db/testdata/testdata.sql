INSERT INTO
    address (city, zipCode, street, houseNumber, flatNumber)
VALUES
    ('Jasło', '38-200', 'Dobrzańskiego', 35, 2),
    ('Kraków', '30-342', 'Mickiewicza', 99, 12),
    ('Rzeszów', '38-200', 'Leibniza', 75, 52),
    ('Krosno', '38-200', 'Słomnicka', 135, 23),
    ('Lipinki Łużyckie', '38-200', 'Słowackiego', 88, 7);
INSERT INTO
    doctor(name, surname, phoneNumber, specialization, email)
VALUES
    ('Paweł', 'Hałucha', '506193161', 'Medycyna Pracy', 'pawhal@tlen.pl'),
    ('Edyta', 'Hałucha', '888668259', 'Diabetologia', 'edyta_med@wp.pl');
INSERT INTO
    patient(name, surname, pesel, dateOfBirth, address_id, phoneNumber, email)
VALUES
    ('Aleksander', 'Turocha', '00233100346', '31-03-2000', '5', '112345029', 'olek_turocha@gmail.com'),
    ('Jakub', 'Hałucha', '00242200996', '22-04-2000', '1', '535110460', 'kubaszekh@gmail.com');
INSERT INTO
    appointment(date, importance, price, doctor_id, patient_id)
VALUES
    ('31-01-2023', 'MEDIUM', 300, 2, 1),
    ('22-03-2023', 'URGENT', 250, 1, 2);