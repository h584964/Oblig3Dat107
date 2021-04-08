package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProsjektDeltagelseDAO {
	private EntityManagerFactory emf;

	public ProsjektDeltagelseDAO() {
		emf = Persistence.createEntityManagerFactory("ansattTest");
	}

	public void registrerProsjektdeltagelse(Ansatt a, Prosjekt p, int timer, String rolle) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			a = em.merge(a);
			p = em.merge(p);

			ProsjektDeltagelse pd = new ProsjektDeltagelse(a, p, timer, rolle);

			a.leggTilProsjektdeltagelse(pd);
			p.leggTilProsjektdeltagelse(pd);

			em.persist(pd);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}

	}

	public void timerRegrister(ProsjektDeltagelse pd, int timer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			ProsjektDeltagelse id = em.merge(pd);
			id.setTimer(timer);

			tx.commit();
			System.out.println("Ansatte sine timer er blitt oppdatert");
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public void r(ProsjektDeltagelse deltagelse) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			deltagelse = em.merge(deltagelse);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
//			if (tx.isActive()) {
			tx.rollback();
//			}
		} finally {
			em.close();
		}

	}

	public ProsjektDeltagelse finnProsjekt(int id) {
		EntityManager em = emf.createEntityManager();
		ProsjektDeltagelse pd = null;
		try {
			em.find(ProsjektDeltagelse.class, id);
		} finally {
			em.close();
		}
		return pd;
	}

	public ProsjektDeltagelse finnProsjekt_A(int ansattId, int prosjektId) {
		String queryStr = "Select a from ProsjektDeltagelse a " + "Where a.prosjekt = :prosjektId"
				+ " AND a.ansatt = :ansattId";
		EntityManager em = emf.createEntityManager();
		ProsjektDeltagelse pd = null;

		try {
			Prosjekt p = em.find(Prosjekt.class, prosjektId);
			Ansatt a = em.find(Ansatt.class, ansattId);
			TypedQuery<ProsjektDeltagelse> query = em.createQuery(queryStr, ProsjektDeltagelse.class);
			query.setParameter("prosjektId", p);
			query.setParameter("ansattId", a);
			pd = query.getSingleResult();

		} catch (Throwable e) {
			System.out.println("Prosjektet " + prosjektId + " med ansattId " + ansattId + " eksisterer ikke");

		} finally {
			em.close();
		}

		return pd;
	}

	public void registrerTimer(int timer, ProsjektDeltagelse p) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			p.leggTilTimer(timer);
			em.merge(p);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("Timer ikke registrert");
			tx.rollback();
		} finally {
			em.close();
		}

	}

	public void skrivUtProsjekt(int prosjektId) {
		String queryString = "Select a from ProsjektDeltagelse a " + "Where a.prosjekt = :prosjektId";
		EntityManager em = emf.createEntityManager();

		Prosjekt p = em.find(Prosjekt.class, prosjektId);
		List<ProsjektDeltagelse> pd = null;

		try {
			TypedQuery<ProsjektDeltagelse> query = em.createQuery(queryString, ProsjektDeltagelse.class);
			query.setParameter("prosjektId", p);
			pd = query.getResultList();

			int sum = 0;
			for (ProsjektDeltagelse e : pd) {
				System.out.println(e.toString());
				sum += e.getTimer();
			}
			System.out.println("Total antall timer på prosjektet: " + sum);
		} finally {
			em.close();
		}
	}

}
