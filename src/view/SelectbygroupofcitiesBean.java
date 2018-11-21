package view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import controller.controllerCiudad;
import model.DTO.Ciudad;

@ManagedBean
@ApplicationScoped
public class SelectbygroupofcitiesBean {
	private List<SelectItem> listaciudades;
	private controllerCiudad controllerCiudad;
	private Ciudad ciudad;
	public SelectbygroupofcitiesBean() {
		ciudad = new Ciudad();
		this.controllerCiudad = new controllerCiudad();
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
}
