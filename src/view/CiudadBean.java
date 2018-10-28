package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import controller.controllerCiudad;
import model.DAO.CiudadDAO;
import model.DTO.Ciudad;

@ManagedBean
public class ciudadBean implements Serializable{
	private List<SelectItem> listaciudades;
	private Ciudad ciudad;
	private String ciudadd;
	public ciudadBean() {
		ciudad = new Ciudad();
	}
	public String getCiudadd() {
		return ciudadd;
	}
	public void setCiudadd(String ciudadd) {
		this.ciudadd = ciudadd;
	}
	public List<SelectItem> getListaciudades() {
		this.listaciudades = new ArrayList<SelectItem>();
		CiudadDAO cDAo= new CiudadDAO();
		List<Ciudad> c = cDAo.selectall();
		listaciudades.clear();
		for (Ciudad ciudades : c) {
			SelectItem ciudadItem = new SelectItem(ciudades.getIdciudad(),ciudades.getNombre());
			this.listaciudades.add(ciudadItem);
		}
		return listaciudades;
	}
	public void setListaciudades(List<SelectItem> listaciudades) {
		this.listaciudades = listaciudades;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	
}