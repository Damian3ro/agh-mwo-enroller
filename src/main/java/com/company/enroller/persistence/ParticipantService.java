package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		String hql = "FROM Participant";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	/*public Participant findByLogin(String login) {
		String hql = "FROM Participant WHERE login=:login"; //wg HQL
		Query query = connector.getSession().createQuery(hql);
		query.setParameter("login", login);
		return (Participant) query.uniqueResult();
	}*/
	public Participant findByLogin(String login) {
		return (Participant) connector.getSession().get(Participant.class, login); //pobieranie z klasy Participant konkretnego loginu
	}

	public Participant add(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();
		return participant;
	}

	public void delete(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().delete(participant);
		transaction.commit();
	}

	public Participant updatePassword(Participant participant, String password) {
		Transaction transaction = connector.getSession().beginTransaction();
		participant.setPassword(password);
		connector.getSession().update(participant);
		transaction.commit();
		return participant;
	}

}
