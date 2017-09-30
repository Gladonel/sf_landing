package com.siftfox.persistence.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.siftfox.model.ContactUser;
import com.siftfox.persistence.ContactUserPersistence;

@Repository
public class ContactUserPersistenceImpl extends GenericDAOImpl<ContactUser, Long> implements ContactUserPersistence {

	public List<ContactUser> getAllUsers() {
	    Query q = getSession().createQuery("from ContactUser");
	    List<ContactUser> allUsers = (List<ContactUser>) q.list();
	    return allUsers;
	}

	@Override
	public ContactUser getUserByEmail(String email) {
		Session sess = getSession();
		ContactUser user = (ContactUser) sess.createQuery(
			    "from ContactUser as user where user.email = ?")
			    .setString(0, email).uniqueResult();
		return user;
	}


}
