package view;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

import controller.controllerCiudad;
import controller.controllerClima;
import controller.controllerService;
import model.DTO.Ciudad;

@ManagedBean
@ApplicationScoped
public class indexBean {

	private List<SelectItem> listaciudades;
	private Ciudad ciudad;
	private controllerService controllerService;
	private controllerCiudad controllerCiudad;
	private controllerClima controllerClima;

	public indexBean() {
		ciudad = new Ciudad();
		controllerService = new controllerService();
		controllerClima = new controllerClima();

	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<SelectItem> getListaciudades() {
		this.listaciudades = new ArrayList<SelectItem>();
		// CiudadDAO cDAo = new CiudadDAO();
		controllerCiudad = new controllerCiudad();
		// List<Ciudad> c = cDAo.selectall();
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
	}
}
