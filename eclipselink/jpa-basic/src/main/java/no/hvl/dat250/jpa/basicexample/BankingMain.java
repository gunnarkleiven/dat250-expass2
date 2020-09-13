package no.hvl.dat250.jpa.basicexample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class BankingMain {
    private static final String PERSISTENCE_UNIT_NAME = "banking";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        //testWithSimplePersonAndAddress();
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select t from Person t");
        List<Person> personList = q.getResultList();
        for (Person person : personList) {
            System.out.println(person);
        }
        System.out.println("Size: " + personList.size());

        em.getTransaction().begin();

        // Create a new person
        Person newPerson = new Person();
        newPerson.setName("Gunnar");

        // We need to make him a credit card
        CreditCard card = new CreditCard();
        List<CreditCard> cardList = new ArrayList<>();
        cardList.add(card);
        // The card needs cardnumber and a pincode
        card.setCardnumber(12345678);
        Pincode pincode = new Pincode();
        pincode.setPincode("1234");
        card.setPincode(pincode);
        // The card also needs a bank
        Bank bank = new Bank();
        bank.setName("Sparebanken Sogn og Fjordane");
        // Also set the card coming from this bank
        bank.setCreditCard(cardList);

        card.setBank(bank);
        // Now we set the limit and the balance
        card.setLimit(1000000);
        card.setBalance(150000);
        // Now we give the card to the person, after we put it in a list
        newPerson.setCreditCard(cardList);

        // Then, we give the person an address (and also add that person to the address' list
        Address address = new Address();
        address.setNumber(8);
        address.setStreet("Hulda Garborgs Gate");
        List<Person> personOnAddress = new ArrayList<>();
        personOnAddress.add(newPerson);
        address.setPersonList(personOnAddress);
        // Finished with the address, add it to the person
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        newPerson.setAddress(addressList);

        em.persist(newPerson);
        em.persist(bank);
        em.persist(card);
        em.persist(pincode);
        em.persist(address);

        em.getTransaction().commit();

        em.close();
    }

    private static void testWithSimplePersonAndAddress() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // read the existing entries in PERSON and write to console
        Query q = em.createQuery("SELECT t FROM PersonSimple t");
        List<PersonSimple> people = q.getResultList();
        System.out.println("These are the persons, with their corresponding addresses");
        for (PersonSimple p : people) {
            System.out.println(p);
        }
        System.out.println("Size: " + q.getResultList().size());

        em.getTransaction().begin();

        PersonSimple newPerson = new PersonSimple();
        newPerson.setName("Kleiven");
        List<AddressSimple> newAddresses = new ArrayList<>();
        List<PersonSimple> personList = new ArrayList<>();
        personList.add(newPerson);
        AddressSimple heim = new AddressSimple();
        heim.setNumber(4);
        heim.setStreet("Stolen");
        heim.setPersonList(personList);
        newAddresses.add(heim);
        AddressSimple bergen = new AddressSimple();
        bergen.setNumber(8);
        bergen.setStreet("Hulda Garborgs Gate");
        bergen.setPersonList(personList);
        newAddresses.add(bergen);
        // Add set of addresses to person
        newPerson.setAddress(newAddresses);

        em.persist(newPerson);
        em.persist(heim);
        em.persist(bergen);

        // Add England to the mix
        PersonSimple anotherNewPerson = new PersonSimple();
        anotherNewPerson.setName("England");
        newAddresses = new ArrayList<>();
        AddressSimple englandHeim = new AddressSimple();
        englandHeim.setNumber(1);
        englandHeim.setStreet("Kvam");
        newAddresses.add(englandHeim);
        personList = new ArrayList<>();
        personList.add(anotherNewPerson);
        englandHeim.setPersonList(personList);
        anotherNewPerson.setAddress(newAddresses);

        em.persist(anotherNewPerson);
        em.persist(englandHeim);
        em.getTransaction().commit();

        em.close();
    }
}
