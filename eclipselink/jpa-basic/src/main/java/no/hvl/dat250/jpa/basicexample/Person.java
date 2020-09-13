package no.hvl.dat250.jpa.basicexample;

import no.hvl.dat250.jpa.basicexample.Address;
import no.hvl.dat250.jpa.basicexample.CreditCard;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "creditcard_id")
    private List<CreditCard> creditCard;

    @ManyToMany
    @JoinTable(
            name = "PERSON_ADDRESS",
            joinColumns = @JoinColumn(name = "person_fk"),
            inverseJoinColumns = @JoinColumn(name = "address_fk ")
    )
    private List<Address> address;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<CreditCard> getCreditCard() { return creditCard; }

    public void setCreditCard(List<CreditCard> card) { this.creditCard = card; }

    public List<Address> getAddress() { return address; }

    public void setAddress(List<Address> address) { this.address = address; }

    @Override
    public String toString() {
        return this.name;
    }

}
