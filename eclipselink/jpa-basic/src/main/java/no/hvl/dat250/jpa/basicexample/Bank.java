package no.hvl.dat250.jpa.basicexample;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bank")
    private List<CreditCard> creditCard = new ArrayList<>();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<CreditCard> getCreditCard() { return creditCard; }
    public void setCreditCard(List<CreditCard> creditCard) { this.creditCard = creditCard; }

    public String toString() {
        return name;
    }
}
