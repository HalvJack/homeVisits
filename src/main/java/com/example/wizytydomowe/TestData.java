package com.example.wizytydomowe;

import com.example.wizytydomowe.Address.AdddressRepository;
import com.example.wizytydomowe.Address.Address;
import com.example.wizytydomowe.Appointment.Appointment;
import com.example.wizytydomowe.Appointment.AppointmentRepository;
import com.example.wizytydomowe.Appointment.Importance;
import com.example.wizytydomowe.Doctor.Doctor;
import com.example.wizytydomowe.Doctor.DoctorRepository;
import com.example.wizytydomowe.Patient.Patient;
import com.example.wizytydomowe.Patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestData {
    private final AdddressRepository adddressRepository;
    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    public void createTestData(){
        createTestAddress();
        createTestDoctors();
        createTestPatient();
        createTestAppointment();
    }

    private void createTestDoctors() {
        doctorRepository.save((new Doctor(1,"Paweł","Hałucha","506193161","Medycyna Pracy","pawhal@tlen.pl")));
        doctorRepository.save((new Doctor(2,"Edyta","Hałucha","888668259","Diabetologia","Edyta_med@wp.pl")));
        doctorRepository.save((new Doctor(3,"Patryk","Janas","663123053","Kardiolog","janas@wp.pl")));
    }

    private void createTestAddress() {
       adddressRepository.save(new Address(1,"Jasło", "38-200", "Dobrzańskiego", 35, 2));
       adddressRepository.save(new Address(2,"Rzeszów", "38-200", "Leibniza", 75, 52));
       adddressRepository.save(new Address(3,"Krosno", "38-200", "Słomnicka", 135, 23));
       adddressRepository.save(new Address(4,"Lipinki Łużyckie", "38-200", "Słowackiego", 88, 7));
    }
    private void createTestPatient(){
        patientRepository.save(new Patient(1, "Jakub", "Hałucha", "001351453", null,
                new Address(1,"Jasło", "38-200", "Mickiewczia", 31, 12),
                "888113361", "kubah200@wp.pl"));
    }
    private void createTestAppointment(){
        appointmentRepository.save(new Appointment(1, null, Importance.MEDIUM, 200, new Doctor(1,
                null, null, null, null, null), new Patient(1,
                null,null,null,null,null,null,null)));
    }

}
