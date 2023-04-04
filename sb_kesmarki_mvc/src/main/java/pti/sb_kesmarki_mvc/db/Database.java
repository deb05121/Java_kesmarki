package pti.sb_kesmarki_mvc.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import pti.sb_kesmarki_mvc.model.Address;
import pti.sb_kesmarki_mvc.model.Contact;
import pti.sb_kesmarki_mvc.model.User;

public class Database {
	
	private SessionFactory sessionFactory;
	
	
	public Database() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	
	public User getUserById(int uid) {
		
		User user = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
				
		user = session.get(User.class, uid);
		Query query = session.createQuery("SELECT a FROM Address a WHERE a.userId = ?1");
		query.setParameter(1, uid);
		List<Address> addresses = query.getResultList();
		
		for (Address address : addresses) {
			int addressId = address.getId();
			user.addAddressId(addressId);
		}
				
		tx.commit();
		session.close();
		
		return user;		
	}	
	
	public void updateUser(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.update(user);
		
		tx.commit();
		session.close();
	}
	
	
	public void addUser(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		
		tx.commit();
		session.close();
	}
	
	
	public void deleteUser(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.delete(user);
		
		tx.commit();
		session.close();
	}
	
	public Address getAddressById(int aId) {
		Address address = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		address = session.get(Address.class, aId);
		Query q = session.createQuery("SELECT c FROM Contact c WHERE c.addressId = ?1");
		q.setParameter(1, aId);
		
		List<Contact> contacts = q.getResultList();
		
		for (Contact contact : contacts) {
			int contactId = contact.getId();
			address.addContactId(contactId);			
		}		
		tx.commit();
		session.close();
		
		return address;
	}
	
	public void updateAddress(Address address) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.update(address);
		
		tx.commit();
		session.close();
	}
	
	
	public void addAddress(Address address) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(address);
		
		tx.commit();
		session.close();
	}
	
	
	public void deleteAddress(Address address) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.delete(address);
		
		tx.commit();
		session.close();
	}
	
	
	
	
	
	
	
	
	
	
	public void closeDb() {
		
		sessionFactory.close();
	}


	

}
