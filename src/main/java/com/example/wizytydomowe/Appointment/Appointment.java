package com.example.wizytydomowe.Appointment;

import com.example.wizytydomowe.Doctor.Doctor;
import com.example.wizytydomowe.Patient.Patient;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Importance importance;
    private Integer price;
    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Appointment(Integer id, LocalDate date, Importance importance, Integer price, Doctor doctor, Patient patient) {
        this.id = id;
        this.date = date;
        this.importance = importance;
        this.price = price;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Appointment() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
