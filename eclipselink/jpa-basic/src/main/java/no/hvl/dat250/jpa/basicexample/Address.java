package no.hvl.dat250.jpa.basicexample;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int number;

    @ManyToMany(mappedBy = "address")
    List<Person> personList;

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public int getNumber() {return number; }
    public void setNumber(int number) { this.number = number; }

    public List<Person> getPersonList() { return personList; }
    public void setPersonList(List<Person> personList) { this.personList = personList; }

    @Override
    public String toString() {
        return "Street = " + street + ", number: " + number;
    }


}
