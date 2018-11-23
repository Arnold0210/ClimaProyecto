package view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import controller.controllerCiudad;
import controller.controllerClima;
import controller.controllerService;
import model.DAO.CiudadDAO;
import model.DAO.ClimaDAO;
import model.DTO.Ciudad;
import model.DTO.Clima;

@ManagedBean
@ApplicationScoped
public class SelectbygroupofcitiesBean {
	private List<SelectItem> listaciudades;
	private controllerCiudad controllerCiudad;
	private Ciudad ciudad;
	private int[] idcities;
	private List<Clima> weather;
	private List<Double> maxTemp;
	private List<Double> maxHumi;
	Ciudad city = new Ciudad();
	CiudadDAO citydao;
	controllerService controllerService;
	controllerClima controllerClima;

	public SelectbygroupofcitiesBean() {
		ciudad = new Ciudad();
		this.controllerCiudad = new controllerCiudad();
		this.weather = new ArrayList<Clima>();
		this.maxTemp =  new ArrayList<Double>();
		this.maxHumi =  new ArrayList<Double>();
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
		this.listaciudades.clear();
		for (Ciudad ciudades : c) {
			SelectItem ciudadItem = new SelectItem(ciudades.getIdciudad(), ciudades.getNombre());
			this.listaciudades.add(ciudadItem);
		}
		return listaciudades;
	}

	public void setListaciudades(List<SelectItem> listaciudades) {
		this.listaciudades = listaciudades;
	}

	public int[] getIdcities() {
		return idcities;
	}

	public void setIdcities(int[] idcities) {
		this.idcities = idcities;
	}

	public List<Clima> getWeather() {
		return weather;
	}

	public void setWeather(List<Clima> weather) {
		this.weather = weather;
	}


	public List<Double> getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(List<Double> maxTemp) {
		this.maxTemp = maxTemp;
	}
	public List<Double> getMaxHumi() {
		return maxHumi;
	}

	public void setMaxHumi(List<Double> maxHumi) {
		this.maxHumi = maxHumi;
	}

	public void submit() {
		int[] cities = this.getIdcities();
		citydao = new CiudadDAO();
		controllerService = new controllerService();
		controllerClima = new controllerClima();
		List<Clima> weathers = new ArrayList<Clima>();
		List<Double> maxT = new ArrayList<Double>();
		List<Double> maxH = new ArrayList<Double>();
		for (int i = 0; i < cities.length; i++) {
			controllerService.insertWeatherOtherCities(cities[i]);
			System.out.println("insert"+cities[i]);
			weathers.add(controllerClima.selectWeather(cities[i]));
			maxT.add((controllerClima.selectMaxWeatherbycity(cities[i]).get(0)));
			maxH.add((controllerClima.selectMaxWeatherbycity(cities[i]).get(1)));
		}
		this.setWeather(weathers);
		this.setMaxTemp(maxT);
		this.setMaxHumi(maxH);
		System.out.println(this.getWeather());
	}
}
