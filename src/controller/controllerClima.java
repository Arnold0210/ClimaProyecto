package controller;

import java.sql.Time;
import java.sql.Timestamp;

import model.DAO.ClimaDAO;
import model.DTO.Ciudad;
import model.DTO.Clima;
import services.RESTEasyClientGet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class controllerClima {
	ClimaDAO DAOClima;

	public void createClima(boolean estado, double humedad, double temperatura, Ciudad ciudad) {
		DAOClima = new ClimaDAO();
		Clima clima = new Clima();
		try {
			clima.setIdclima(DAOClima.searchId());
			clima.setEstado(estado);
			clima.setHumedad(humedad);
			clima.setTemperatura(temperatura);
			clima.setCiudad(ciudad);
			Timestamp fecha = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			clima.setFecha(fecha);
			Time hora = new java.sql.Time(Calendar.getInstance().getTime().getTime());
			clima.setHora(hora);
			DAOClima.create(clima);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Clima selectClima(int id) {
		DAOClima = new ClimaDAO();
		Clima clima = DAOClima.select(id);
		return clima;
	}

	public List<Clima> selectAllWeathers() {
		DAOClima = new ClimaDAO();
		List<Clima> climas = DAOClima.selectall();
		return climas;
	}

	public Clima selectWeather(int id) {

		DAOClima = new ClimaDAO();
		Clima clima = DAOClima.selectlastwheaterbycity(id);
		return clima;
	}

	public void insertWeatherOnAllCities(List<Ciudad> cities) {
		RESTEasyClientGet rest = new RESTEasyClientGet();
		for (Ciudad c : cities) {
			List<Double> weather = rest.Temp(c.getNombre());
			createClima(true, weather.get(0), weather.get(1), c);
		}
	}

	public List<Clima> selectAllWeathers(int id) {
		DAOClima = new ClimaDAO();
		List<Clima> climas = DAOClima.selectallbycity(id);
		return climas;
	}
	public List<Double> selectMaxWeatherbycity(int id){
		List<Double> maxweather = new ArrayList<Double>();
		double maxtemp=DAOClima.maxAllTemp(id);
		double maxhumi = DAOClima.maxAllHumi(id);
		maxweather.add(maxtemp);
		maxweather.add(maxhumi);
		return maxweather;
	}
}
//heroku war:deploy "C:/Users/TRABAJO/Documents/2018-III/Arquitectura de Software/ClimaProyecto.war" --app climaproject