package com.example.had.request;

import java.util.List;

public class DoctorRegisterRequest {
     String email;
     String password;
     String firstName;
     String lastName;
     String middleName;
     String gender;
     int age;
     String degree;
     String specialisation;
     int experience;
     String address;
     String contact;
     String imageUrl;
     String registrationNumber;
     int PatientLimit;
     int PatientCount;
     List<String> languages;

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getFirstName() {
          return firstName;
     }

     public void setFirstName(String firstName) {
          this.firstName = firstName;
     }

     public String getLastName() {
          return lastName;
     }

     public void setLastName(String lastName) {
          this.lastName = lastName;
     }

     public String getMiddleName() {
          return middleName;
     }

     public void setMiddleName(String middleName) {
          this.middleName = middleName;
     }

     public String getGender() {
          return gender;
     }

     public void setGender(String gender) {
          this.gender = gender;
     }

     public int getAge() {
          return age;
     }

     public void setAge(int age) {
          this.age = age;
     }

     public String getDegree() {
          return degree;
     }

     public void setDegree(String degree) {
          this.degree = degree;
     }

     public String getSpecialisation() {
          return specialisation;
     }

     public void setSpecialisation(String specialisation) {
          this.specialisation = specialisation;
     }

     public int getExperience() {
          return experience;
     }

     public void setExperience(int experience) {
          this.experience = experience;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getContact() {
          return contact;
     }

     public void setContact(String contact) {
          this.contact = contact;
     }

     public String getImageUrl() {
          return imageUrl;
     }

     public void setImageUrl(String imageUrl) {
          this.imageUrl = imageUrl;
     }

     public String getRegistrationNumber() {
          return registrationNumber;
     }

     public void setRegistrationNumber(String registrationNumber) {
          this.registrationNumber = registrationNumber;
     }

     public int getPatientLimit() {
          return PatientLimit;
     }

     public void setPatientLimit(int patientLimit) {
          PatientLimit = patientLimit;
     }

     public int getPatientCount() {
          return PatientCount;
     }

     public void setPatientCount(int patientCount) {
          PatientCount = patientCount;
     }

     public List<String> getLanguages() {
          return languages;
     }

     public void setLanguages(List<String> languages) {
          this.languages = languages;
     }

     @Override
     public String toString() {
          return "DoctorRegisterRequest{" +
                  "email='" + email + '\'' +
                  ", password='" + password + '\'' +
                  ", firstName='" + firstName + '\'' +
                  ", lastName='" + lastName + '\'' +
                  ", middleName='" + middleName + '\'' +
                  ", gender='" + gender + '\'' +
                  ", age=" + age +
                  ", degree='" + degree + '\'' +
                  ", specialisation='" + specialisation + '\'' +
                  ", experience=" + experience +
                  ", address='" + address + '\'' +
                  ", contact='" + contact + '\'' +
                  ", imageUrl='" + imageUrl + '\'' +
                  ", registrationNumber='" + registrationNumber + '\'' +
                  ", PatientLimit=" + PatientLimit +
                  ", PatientCount=" + PatientCount +
                  ", languages=" + languages +
                  '}';
     }
}
