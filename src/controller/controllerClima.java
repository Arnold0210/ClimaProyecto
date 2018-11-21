package controller;

import java.util.Date;
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

	public List<Double> selectMaxWeatherbycity(int id) {
		List<Double> maxweather = new ArrayList<Double>();
		double maxtemp = DAOClima.maxAllTemp(id);
		double maxhumi = DAOClima.maxAllHumi(id);
		maxweather.add(maxtemp);
		maxweather.add(maxhumi);
		return maxweather;
	}

	public List<Double> selectAvgTemp(int id) {
		List<Double> temp = new ArrayList<Double>();
		temp = DAOClima.avgTem(id);
		return temp;
	}

	public List<Double> selectAvgHum(int id) {
		List<Double> hum = new ArrayList<Double>();
		hum = DAOClima.avgHum(id);
		return hum;
	}

	public List<Date> selectDate(int id) {
		List<Date> date = DAOClima.date(id);
		return date;
	}

	public List<Double> selectHumlastDay(int id) {
		List<Clima> climas = DAOClima.getWeatherLastDay(id);
		int step = 0, currenthour = 0, pasthour = -1;
		List<Double> humiHour = new ArrayList<Double>();
		List<Integer> hour = new ArrayList<Integer>();
		double sumHum = 0;
		for (Clima c : climas) {
			currenthour = c.getHora().getHours();
			if (step != 0 && (currenthour != pasthour)) {
				humiHour.add(sumHum / step);
				hour.add(pasthour);
				step = 0;
				sumHum = 0;
			}
			sumHum += c.getHumedad();
			pasthour = currenthour;
			step++;
		}
		humiHour.add(sumHum / step);
		hour.add(currenthour);
		for (int i = 0; i < hour.size(); i++) {
			System.out.println("Hora: " + hour.get(i) + " Temp: " + humiHour.get(i) + " Humd: ");
		}
		return humiHour;
	}

	public List<Double> selectTemplastDay(int id) {
		List<Clima> climas = DAOClima.getWeatherLastDay(id);
		int step = 0, currenthour = 0, pasthour = -1;
		List<Double> tempHour = new ArrayList<Double>();

		List<Integer> hour = new ArrayList<Integer>();
		double sumTemp = 0;
		for (Clima c : climas) {
			currenthour = c.getHora().getHours();
			if (step != 0 && (currenthour != pasthour)) {
				tempHour.add(sumTemp / step);
				hour.add(pasthour);
				step = 0;
				sumTemp = 0;
			}
			sumTemp += c.getTemperatura();
			pasthour = currenthour;
			step++;
		}
		tempHour.add(sumTemp / step);
		hour.add(currenthour);
		for (int i = 0; i < hour.size(); i++) {
			System.out.println("Hora: " + hour.get(i) + " Temp: " + tempHour.get(i) + " Humd: ");
		}
		return tempHour;
	}

	public List<Integer> selectHourlastDay(int id) {
		List<Clima> climas = DAOClima.getWeatherLastDay(id);
		int step = 0, currenthour = 0, pasthour = -1;
		List<Integer> hour = new ArrayList<Integer>();

		for (Clima c : climas) {
			currenthour = c.getHora().getHours();
			if (step != 0 && (currenthour != pasthour)) {
				hour.add(pasthour);

			}
			pasthour = currenthour;
			step++;
		}
		hour.add(currenthour);
		for (int i = 0; i < hour.size(); i++) {
			System.out.println("Hora: " + hour.get(i));
		}
		return hour;
	}
}
//heroku war:deploy "C:/Users/TRABAJO/Documents/2018-III/Arquitectura de Software/ClimaProyecto.war" --app climaproject