package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AvdelingDAO {
	private EntityManagerFactory emf;

	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("ansattTest");
	}

	public Avdeling finnAvdelingMedId(int id) {
		EntityManager em = emf.createEntityManager();

		Avdeling a = null;

		try {
			a = em.find(Avdeling.class, id);

		} finally {
			em.close();
		}

		return a;
	}

	public void skrivUtAlle(int id) {
		EntityManager em = emf.createEntityManager();

		Avdeling a = em.find(Avdeling.class, id);
		List<Ansatt> ansatte = null;

		try {
			TypedQuery<Ansatt> query = em.createQuery("Select a from Ansatt a " + "WHERE a.avdeling = :avdeling",
					Ansatt.class);
			query.setParameter("avdeling", a);

			ansatte = query.getResultList();

			for (Ansatt e : ansatte) {
				if (e == a.getSjef()) {
					System.out.println("Sjef " + e.toString());
				} else {
					System.out.println(e.toString());
				}
			}
		} finally {
			em.close();
		}

	}

	public void oppdaterAvdeling(Avdeling avdeling, int ansattId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, ansattId);

			if (a != null && a.getAvdeling().getSjef() != a) {
				a.setAvdeling(avdeling);
				em.merge(a); // tar objektet å knytter det sammen med databasen
			} else {
				System.out.println(a.getNavn() + " " + "kan ikke bytte avdeling siden er sjef i en annen");
			}

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();

			tx.rollback();

		} finally {
			em.close();
		}

	}
	
	public void leggTilNyAvdeling(Avdeling a, Ansatt sjef) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(a);
			sjef.setAvdeling(a);
			em.merge(sjef);
			tx.commit();
		} catch(Throwable e) {
			e.printStackTrace();

			tx.rollback();
		} finally {
			em.close();
		}
	}

//	public boolean leggTilNyAvdeling(Avdeling a) {
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		boolean lagtTil = false;
//
//		try {
//			tx.begin();
//			em.persist(a);
//			tx.commit();
//
//			lagtTil = true;
//			
//		} catch (Throwable e) {
//			e.printStackTrace();
//			tx.rollback();
//		} finally {
//			em.close();
//		}
//		return lagtTil;
//	}
//	
//	private boolean erSjef(int ansattId) {
//		EntityManager em = emf.createEntityManager();
//		boolean erSjef = true;
//		
//		String queryString = "SELECT a FROM Avdeling a WHERE a.sjef = :ansattId";
//		Avdeling a = null;
//		try {
//			TypedQuery<Avdeling> query = em.createQuery(queryString, Avdeling.class);
//			query.setParameter("ansattId", ansattId);
//			a = query.getSingleResult();
//			
//		}
//		catch(NoResultException e) {
//			erSjef = false;
//		}
//		
//		finally {
//			em.close();
//		}
//		if(erSjef) 
//			System.out.println("Den ansatte er allerde sjef og kan ikke flyttes");
//		return erSjef;
//	}
}

//	public void skrivUtAlle(int id){
//		EntityManager em = emf.createEntityManager();
//		Avdeling avd = null;
//		List<Ansatt> liste = null;
//		
//		avd = finnAvdelingMedId(id);
//		Ansatt sjefid = avd.getSjef();
//		Ansatt sjef = em.find(Ansatt.class, id);
//		
//		liste = avd.getAnsatte();
//		System.out.println("Oversikt over ansatte i " + avd.getNavn() + ", avdelingsnr" + id);
//		System.out.println("Sjef: " + sjef.toString());
//		
//		for(Ansatt a : liste) {
//			if(a.getAnsattId() != sjefid) {
//				System.out.println("Ans: " + a.toString());
//			}
//		}
//	}
//	}
//		try {
//			
//			//TypedQuery<Ansatt> query = em.createQuery("Select a from Ansatt a " + "WHERE a.avdeling = :avdeling", Ansatt.class);
//			query.setParameter("avdeling", a);
//			ansatte = query.getResultList();
//			
//			for(Ansatt e : ansatte) {
//				if(e == a.getSjef()){
//					System.out.println("Sjef " + e.toString());
//				}else {
//					System.out.println(e.toString());
//				}
//			}
//		}finally {
//			em.close();
//		}
//		return ansatte;
//	}
