package model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.DTO.Ciudad;

public class CiudadDAO {
	private static final String persistenceUnitName = "clima";
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public CiudadDAO() {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}

	public Boolean create(Ciudad ciudad) {
		try {
			em.getTransaction().begin();
			em.persist(ciudad);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {

			System.err.println("Excepcion \n" + e);
			em.getTransaction().rollback();
			em.close();
			return false;
		}
	}

	public int searchId() {
		int maximo = 0;
		TypedQuery<Ciudad> query = em.createQuery("select t from Ciudad t", Ciudad.class);
		List<Ciudad> ciudad = query.getResultList();
		for (Ciudad c : ciudad) {
			maximo = c.getIdciudad();
		}
		maximo++;
		return maximo;
	}

	public Ciudad select(int id) {
		Ciudad ciudad = em.find(Ciudad.class, id);
		em.close();
		return ciudad;
	}

	public Ciudad selectbyname(String nombre) {
		Ciudad ciudad = em.find(Ciudad.class, nombre);
		return ciudad;
	}

	public List<Ciudad> selectall() {
		List<Ciudad> ciudades = em.createQuery("SELECT c FROM Ciudad c ORDER BY c.nombre", Ciudad.class)
				.getResultList();
		em.close();
		return ciudades;
	}

	public boolean status(int id, boolean estado) {
		try {
			em.getTransaction().begin();
			Ciudad city = em.find(Ciudad.class, id);
			city.setEstado(estado);
			em.merge(city);
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

	public boolean update(int id, String nombre) {
		try {
			em.getTransaction().begin();
			Ciudad city = em.find(Ciudad.class, id);
			city.setNombre(nombre);
			em.getTransaction().begin();
			em.close();
			return true;
		} catch (Exception e) {

			System.err.println(e);
			em.getTransaction().rollback();
			em.close();
			return false;
		}
	}
}
