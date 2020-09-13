package no.hvl.dat250.jpa.basicexample;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonSimple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany
    @JoinTable(
            name = "PERSONSIMPLE_ADDRESSSIMPLE",
            joinColumns = @JoinColumn(name = "personsimple_fk"),
            inverseJoinColumns = @JoinColumn(name = "addresssimple_fk")
    )
    private List<AddressSimple> address;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<AddressSimple> getAddress() { return address; }

    public void setAddress(List<AddressSimple> address) { this.address = address; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" with addresses: \n");
        for (AddressSimple a : address) {
            sb.append(a + "\n");
        }
        return sb.toString();
    }

}
