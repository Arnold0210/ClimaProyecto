package model.DAO;

import java.util.Date;
import java.util.ArrayList;
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
			if (clima.getHumedad() != 0 && clima.getTemperatura() != 0) {
				em.getTransaction().begin();
				em.persist(clima);
				em.getTransaction().commit();
				em.close();
				return true;
			} else {
				System.err.println("No existen valores de temperatura ni de humedad\n");
				em.getTransaction().rollback();
				em.close();
				return false;
			}
		} catch (Exception e) {
			System.err.print("Excepcion: " + e + "\n");
			em.getTransaction().rollback();
			em.close();
			return false;
		}
	}

	public Clima select(int id) {

		Clima clima = em.find(Clima.class, id);
		return clima;
	}

	public int searchId() {
		int maximo = 0;
		TypedQuery<Clima> query = em.createQuery("select t from Clima t", Clima.class);
		List<Clima> clima = query.getResultList();
		for (Clima c : clima) {
			maximo = c.getIdclima();
		}
		maximo++;
		return maximo;
	}

	public List<Clima> selectall() {
		TypedQuery<Ciudad> query = em.createQuery("SELECT c from Ciudad c", Ciudad.class);
		List<Ciudad> allcities = query.getResultList();
		List<Clima> climas = new ArrayList<Clima>();
		for (Ciudad c : allcities) {
			climas.add(selectlastwheaterbycity(c.getIdciudad()));
		}

		return climas;
	}

	public List<Clima> selectallbycity(int id) {
		TypedQuery<Clima> query = em.createNamedQuery("Clima.selectweatherbycityindex", Clima.class);
		query.setParameter("idciudad", id);
		List<Clima> clima = query.getResultList();
		return clima;
	}

	public List<Clima> selectallbycity2(int id) {
		TypedQuery<Clima> query = em.createNamedQuery("Clima.selectweatherbycity", Clima.class);
		query.setParameter("idciudad", id);
		List<Clima> clima = query.getResultList();
		return clima;
	}

	public Clima selectlastwheaterbycity(int id) {

		TypedQuery<Clima> query = em.createNamedQuery("Clima.selectweatherbycity", Clima.class);
		query.setParameter("idciudad", id);
		List<Clima> clima = query.getResultList();
		int maximo = 0;
		for (Clima c : clima) {
			maximo = c.getIdclima();
		}
		Clima lastweather = select(maximo);
		return lastweather;
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

	public double maxAllTemp(int id) {
		TypedQuery<Double> query = em.createNamedQuery("Clima.selectMaxAllTemp", Double.class);
		query.setParameter("idciudad", id);
		Double max = query.getSingleResult();
		return max;
	}

	public double minAllTemp(int id) {
		TypedQuery<Double> query = em.createNamedQuery("Clima.selectMaxAllTemp", Double.class);
		query.setParameter("idciudad", id);
		Double min = query.getSingleResult();
		return min;
	}

	public double maxAllHumi(int id) {
		TypedQuery<Double> query = em.createNamedQuery("Clima.selectMaxAllHumi", Double.class);
		query.setParameter("idciudad", id);
		Double max = query.getSingleResult();
		return max;
	}

	public double minAllHumi(int id) {
		TypedQuery<Double> query = em.createNamedQuery("Clima.selectMaxAllHumi", Double.class);
		query.setParameter("idciudad", id);
		Double min = query.getSingleResult();
		return min;
	}

	public List<Double> avgTem(int id) {
		TypedQuery<Double> query = em.createNamedQuery("Clima.selectavgTemp", Double.class);
		query.setParameter("idciudad", id);
		List<Double> temp = query.getResultList();
		return temp;
	}

	public List<Double> avgHum(int id) {
		TypedQuery<Double> query = em.createNamedQuery("Clima.selectavgHumi", Double.class);
		query.setParameter("idciudad", id);
		List<Double> hum = query.getResultList();
		return hum;
	}

	public List<Date> date(int id) {
		TypedQuery<Date> query = em.createNamedQuery("Clima.selectdate", Date.class);
		query.setParameter("idciudad", id);
		List<Date> date = query.getResultList();
		return date;
	}

	public List<Clima> getWeatherLastDay(int id) {
		TypedQuery<Clima> query = em.createNamedQuery("Clima.selectdateWeather", Clima.class);
		query.setParameter("idciudad", id);
		List<Clima> clima = query.getResultList();
		return clima;
	}
}
