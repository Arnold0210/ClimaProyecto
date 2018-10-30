package controller;

import java.util.ArrayList;

import java.util.List;

import model.DTO.Ciudad;
import model.DTO.Clima;
import services.RESTEasyClientGet;

public class controllerService {

	public List<Double> insertWeatherBogota(int id) {
		controllerClima controllerClima = new controllerClima();
		Clima ciudadBogota;
		List<Double> weather = new ArrayList<Double>();
		ciudadBogota = controllerClima.selectClima(id);
		weather.add(ciudadBogota.getTemperatura());
		weather.add(ciudadBogota.getHumedad());
		System.out.println(weather);
		return weather;
	}

	public List<Double> insertWeatherOtherCities(int id) {
		List<Double> weather;
		String nombre;
		controllerCiudad controllerCiudad = new controllerCiudad();
		RESTEasyClientGet restApi = new RESTEasyClientGet();
		controllerClima controllerClima = new controllerClima();
		Ciudad city = new Ciudad();
		city = controllerCiudad.selectCiudad(id);
		nombre = city.getNombre();
		System.out.println(id + "," + nombre);
		weather = restApi.Temp(nombre);
		controllerClima.createClima(true, weather.get(0), weather.get(1), city);
		return weather;
	}

}
