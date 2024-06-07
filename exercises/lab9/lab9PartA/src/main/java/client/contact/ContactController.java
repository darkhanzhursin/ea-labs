package client.contact;

import client.CustomErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private Map<String, Contact> contacts = new HashMap<>();

    public ContactController() {
        contacts.put("Frank", new Contact("Frank", "Brown", "fbrown@acme.com", "2341678453"));
        contacts.put("Mary", new Contact("Mary", "Jones", "mjones@acme.com", "2341674376"));
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<?> getContact(@PathVariable String firstName) {
        Contact contact = contacts.get(firstName);
        if (contact == null) {
            return new ResponseEntity<>(new CustomErrorType("Contact with firstName=" + firstName
            + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
        contacts.put(contact.getFirstName(), contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @DeleteMapping("/{firstName}")
    public ResponseEntity<?> deleteContact(@PathVariable String firstName) {
        Contact contact = contacts.get(firstName);
        if (contact == null) {
            return new ResponseEntity<>(new CustomErrorType("Contact with firstname=" + firstName +
                    " is not available"), HttpStatus.NOT_FOUND);
        }
        contacts.remove(firstName);
        return new ResponseEntity<>(contact, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{firstName}")
    public ResponseEntity<?> updateContact(@PathVariable String firstName, @RequestBody Contact contact) {
        contacts.put(firstName, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllContacts() {
        Contacts allContacts = new Contacts(contacts.values());
        return new ResponseEntity<>(allContacts, HttpStatus.OK);
    }
}
