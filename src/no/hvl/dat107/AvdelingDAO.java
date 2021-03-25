package no.hvl.dat107;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

	

}
