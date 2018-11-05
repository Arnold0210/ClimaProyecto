package controller;

import java.sql.Time;
import java.sql.Timestamp;

import model.DAO.ClimaDAO;
import model.DTO.Ciudad;
import model.DTO.Clima;

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
			System.err.println("Succesfull save Clima!!!");
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

	public void selectWeather(int id) {
		DAOClima = new ClimaDAO();
		List<Double> weather;
		weather = new ArrayList<Double>();
		Clima clima = DAOClima.selectlastwheaterbycity(id);
		weather.add(clima.getHumedad());
		weather.add(clima.getTemperatura());
	}

	public List<Clima> selectAllWeathers(int id) {
		DAOClima = new ClimaDAO();
		List<Clima> climas = DAOClima.selectallbycity(id);
		return climas;
	}
}
