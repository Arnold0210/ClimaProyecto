package view;

import java.util.ArrayList;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import controller.controllerCiudad;
import controller.controllerClima;
import controller.controllerService;
import model.DTO.Ciudad;
import model.DTO.Clima;

@ManagedBean
@ApplicationScoped
public class selectbycityBean {
	private Ciudad ciudad;
	private Clima clima;
	private List<SelectItem> listaciudades;
	private controllerService controllerService;
	private controllerClima controllerClima;
	private controllerCiudad controllerCiudad;
	private List<Double> maxWeatherCity;
	private double coefPearson;

	public selectbycityBean() {
		ciudad = new Ciudad();
		controllerClima = new controllerClima();
		controllerService = new controllerService();
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<SelectItem> getListaciudades() {
		this.listaciudades = new ArrayList<SelectItem>();
		controllerCiudad = new controllerCiudad();
		List<Ciudad> c = controllerCiudad.selectAllCities();
		listaciudades.clear();
		for (Ciudad ciudades : c) {
			SelectItem ciudadItem = new SelectItem(ciudades.getIdciudad(), ciudades.getNombre());
			this.listaciudades.add(ciudadItem);
		}
		return listaciudades;
	}

	public Clima getClima() {
		return clima;
	}

	public void setClima(Clima clima) {
		this.clima = clima;
	}

	public controllerService getControllerService() {
		return controllerService;
	}

	public void setControllerService(controllerService controllerService) {
		this.controllerService = controllerService;
	}

	public controllerClima getControllerClima() {
		return controllerClima;
	}

	public void setControllerClima(controllerClima controllerClima) {
		this.controllerClima = controllerClima;
	}

	public List<Double> getMaxweathercity() {
		return maxWeatherCity;
	}

	public void setMaxweathercity(List<Double> maxweathercity) {
		this.maxWeatherCity = maxweathercity;
	}

	public double getCoefPearson() {
		return coefPearson;
	}

	public void setCoefPearson(double coefPearson) {
		this.coefPearson = coefPearson;
	}

	public void submit() {
		int id;
		id = this.getCiudad().getIdciudad();
		if (id == 1) {
			controllerClima.selectWeather(id);
//			weather = controllerService.insertWeatherBogota(id);
//			weather = controllerService.insertWeatherOtherCities(id);
		} else {
			controllerService.insertWeatherOtherCities(id);
		}
		controllerClima = new controllerClima();
		setClima(controllerClima.selectWeather(this.ciudad.getIdciudad()));
		setMaxweathercity(controllerClima.selectMaxWeatherbycity(this.ciudad.getIdciudad()));
	}
}
