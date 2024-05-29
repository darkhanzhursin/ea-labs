package contacts.repository;

import contacts.domain.Contact;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ContactRepository {

    private Map<String, Contact> contacts = new HashMap<String, Contact>();

    public void save(Contact contact){
        contacts.put(contact.getFirstName(),contact);
    }

    public Contact findByFirstName(String firstName){
        return contacts.get(firstName);
    }

    public void delete(String firstName){
        contacts.remove(firstName);
    }

    public Collection<Contact> findAll(){
        return contacts.values();
    }
}
