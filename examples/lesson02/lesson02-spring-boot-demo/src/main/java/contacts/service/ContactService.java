package contacts.service;

import contacts.domain.Contact;
import contacts.integration.EmailSender;
import contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    EmailSender emailSender;

    public void add(Contact contact) {
        contactRepository.save(contact);
        emailSender.sendEmail(contact.getEmail(), "Welcome");
    }

    public void update(Contact contact) {
        contactRepository.save(contact);
    }

    public Contact findByFirstName(String firstName){
        return contactRepository.findByFirstName(firstName);
    }

    public void delete(String firstName){
        Contact contact = contactRepository.findByFirstName(firstName);
        emailSender.sendEmail(contact.getEmail(), "Good Bye");
        contactRepository.delete(firstName);
    }

    public Collection<Contact> findAll(){
        return contactRepository.findAll();
    }
}
