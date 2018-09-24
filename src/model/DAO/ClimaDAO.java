package model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.DTO.Ciudad;
import model.DTO.Clima;

public class ClimaDAO {
	private static final String persistenceUnitName = "clima";
	private static EntityManager em;
	private static EntityManagerFactory emf;

	public ClimaDAO() {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}

	public Boolean create(Clima clima) {
		try {
			em.getTransaction().begin();
			em.persist(clima);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			// 
			System.err.println("Excepcion \n" + e);
			em.getTransaction().rollback();
			em.close();
			return false;
		}
	}
	
	
	public Clima select(int id) {
		Clima clima = em.find(Clima.class, id);
		em.close();
		return clima;
	}
	public int searchId() {
		int maximo = 0;
		TypedQuery<Clima> query = em.createQuery("select t from Clima t",Clima.class);
		List<Clima> clima = query.getResultList();
		for(Clima c:clima) {
			maximo = c.getIdclima();
		}
		maximo++;
		return maximo;
	}
	public List<Clima> selectall() {
		@SuppressWarnings("unchecked")
		List<Clima> climas = em.createQuery("from Clima a").getResultList();
		em.close();
		return climas;
	}

	public boolean status(int id, boolean estado) {
		try {
			em.getTransaction().begin();
			Clima clima = em.find(Clima.class, id);
			clima.setEstado(estado);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			
			System.err.println(e);
			em.getTransaction().rollback();
			em.close();
			return false;
		}
	}

	public boolean updatecity(int id, Ciudad ciudad) {
		try {
			em.getTransaction().begin();
			Clima clima = em.find(Clima.class, id);
			clima.setCiudad(ciudad);
			em.getTransaction().begin();
			em.close();
			return true;
		} catch (Exception e) {
			// 
			System.err.println(e);
			em.getTransaction().rollback();
			em.close();
			return false;
		}
	}
}
