package no.hvl.dat250.jpa.basicexample;

import javax.persistence.*;
import java.util.List;

@Entity
public class AddressSimple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "address")
    List<PersonSimple> personSet;

    private String street;
    private int number;

    public String getStreet() { return street; }

    public void setStreet(String street) {this.street = street;}

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public List<PersonSimple> getPersonSet() { return personSet; }

    public void setPersonList(List<PersonSimple> personSet) { this.personSet = personSet; }

    public String toString() {
        return "Street = " + street + ", number: " + number;
    }
}
