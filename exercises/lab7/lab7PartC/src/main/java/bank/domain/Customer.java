package bank.domain;

import jakarta.persistence.*;

@Entity
public class Customer {

	@Id
	private long id;

	private String name;
	
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

	protected Customer() {
	}

	public Customer(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}


