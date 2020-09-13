package no.hvl.dat250.jpa.basicexample;

import javax.persistence.*;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cardnumber;
    private int balance;
    private int limit;

    @OneToOne
    private Pincode pincode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bank bank;

    public int getCardnumber() { return cardnumber; }
    public void setCardnumber(int cardnumber) { this.cardnumber = cardnumber; }

    public int getLimit() {return limit; }
    public void setLimit(int limit) { this.limit = limit; }

    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }

    public Bank getBank() { return bank; }
    public void setBank(Bank bank) { this.bank = bank; }

    public Pincode getPincode() { return pincode; }
    public void setPincode(Pincode pincode) { this.pincode = pincode; }
}
