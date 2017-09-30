package com.siftfox.persistence.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.siftfox.model.TrialUser;
import com.siftfox.persistence.TrialUserPersistence;

@Repository
public class TrialUserPersistenceImpl extends GenericDAOImpl<TrialUser, Long> implements TrialUserPersistence {

	public List<TrialUser> getAllUsers() {
	    Query q = getSession().createQuery("from TrialUser");
	    List<TrialUser> allUsers = (List<TrialUser>) q.list();
	    return allUsers;
	}

	@Override
	public TrialUser getUserByEmail(String email) {
		Session sess = getSession();
		TrialUser user = (TrialUser) sess.createQuery(
			    "from TrialUser as user where user.email = ?")
			    .setString(0, email).uniqueResult();
		return user;
	}


}
