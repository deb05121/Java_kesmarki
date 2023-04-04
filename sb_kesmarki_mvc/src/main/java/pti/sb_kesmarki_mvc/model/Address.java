package pti.sb_kesmarki_mvc.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "userid")
	private int userId;
	
	@Transient
	private ArrayList<Integer> contactIds;
	
	
	
	
	
	public Address() {
		
		this.contactIds = new ArrayList<>();
	}
	
	public void addContactId(int contactId) {
		this.contactIds.add(contactId);
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public ArrayList<Integer> getContactIds() {
		return contactIds;
	}


	public void setContactIds(ArrayList<Integer> contactIds) {
		this.contactIds = contactIds;
	}


	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", userId=" + userId + ", contactIds=" + contactIds + "]";
	}
	
	

}
