package view;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.model.SelectItem;

import controller.controllerCiudad;
import controller.controllerClima;
import model.DTO.Ciudad;
import model.DTO.Clima;

@ManagedBean
@ApplicationScoped
public class indexBean {

	private List<SelectItem> listaciudades;
	private Ciudad ciudad;

	private controllerCiudad controllerCiudad;
	private List<Clima> clima;
	private controllerClima controllerClima;

	public indexBean() {
		ciudad = new Ciudad();
		controllerClima = new controllerClima();
		controllerCiudad = new controllerCiudad();
		List<Ciudad> c = controllerCiudad.selectAllCities();
		controllerClima.insertWeatherOnAllCities(c);
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

	public void setListaciudades(List<SelectItem> listaciudades) {
		this.listaciudades = listaciudades;
	}

	public List<Clima> getClima() {
		this.clima = new ArrayList<Clima>();

		controllerClima = new controllerClima();
		clima = controllerClima.selectAllWeathers();

		Collections.sort(clima, new Comparator<Clima>() {
			@Override
			public int compare(Clima c1, Clima c2) {
				return c1.getCiudad().getNombre().compareTo(c2.getCiudad().getNombre());
			}
		});
		return clima;
	}

	public float toFahrenheit(double temp) {
		float fahrenheit;
		fahrenheit = (float) (temp * 1.8);
		fahrenheit = fahrenheit + 32;
		return (float) (Math.round(fahrenheit * 100) / 100.0);
	}
}
