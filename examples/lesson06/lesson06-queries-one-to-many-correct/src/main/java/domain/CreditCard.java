package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue
    private int id;

    private String number;

    private String name;

    private Date expiration;

    public CreditCard() {
    }

    public CreditCard(String number, String name, Date expiration) {
        this.number = number;
        this.name = name;
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", expiration=" + expiration +
                '}';
    }

}
