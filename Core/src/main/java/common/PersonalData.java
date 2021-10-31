package common;

import java.time.LocalDateTime;

/**
 * A class that returns my personal data. An example of a singleton class
 */
public final class PersonalData {

    // Static variable reference of singleInstance of type PersonalData
    private static PersonalData singleInstance = null;

    // Singleton
    private final String firstName;
    private final String lastName;
    private final String fatherName;
    private final LocalDateTime dateOfBirth;

    // Constructor to set up the properties. Restricted to this class alone
    private PersonalData(){
        this.firstName = "George";
        this.lastName = "Mammos";
        this.fatherName = "Michalis";
        this.dateOfBirth = LocalDateTime.of(1993,12,11,12,0);
    }

    // Static method to get the singleton instance. Either create a new instance or return the existing one (this is not thread safe - consider an enum implementation for singleton)
    public static PersonalData getInstance(){

        if(singleInstance == null){
            singleInstance = new PersonalData();
        }

        return  singleInstance;
    }

    // Getters for the properties
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    // toString method
    @Override
    public String toString() {
        return "PersonalData {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
