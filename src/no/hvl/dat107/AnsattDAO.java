package no.hvl.dat107;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AnsattDAO {

	private EntityManagerFactory emf;

	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("ansattTest");
	}

	public Ansatt hentAnsattId(int ansattId) {

		EntityManager em = emf.createEntityManager();

		Ansatt a = null;

		try {
			a = em.find(Ansatt.class, ansattId);

		} finally {
			em.close();
		}

		return a;
	}

	public Ansatt hentBrukernavn(String brukernavn) {
		String queryString = "Select a From Ansatt a " + "WHERE a.brukernavn = :brukernavn";
		EntityManager em = emf.createEntityManager();

		Ansatt a = null;

		try {
			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			query.setParameter("brukernavn", brukernavn);
			a = query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();

		} finally {
			em.close();
		}

		return a;
	}

	public void skrivUtAlle() {
		String queryString = "Select a from Ansatt a";
		EntityManager em = emf.createEntityManager();

		List<Ansatt> ansatte = null;
		try {
			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			ansatte = query.getResultList();

			for (Ansatt e : ansatte) {
				System.out.println(e.toString());
			}
		} finally {
			em.close();
		}
	}

	public void oppdaterStilling(String stilling, int ansattId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, ansattId);

			if (a != null) {
				a.setStilling(stilling);

				em.merge(a); // tar objektet å knytter det sammen med databasen
			}


			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();

			tx.rollback();

		} finally {
			em.close();
		}

	}

	public void oppdaterLonn(int maanedslonn, int ansattId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, ansattId);
			if (a != null) {
				a.setMaanedslonn(maanedslonn);
				em.merge(a);
			}
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public void leggTilNyAnsatt(Ansatt a) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(a);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	 

}
