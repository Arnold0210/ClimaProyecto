package view;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CiudadBean {
	private String nombreCiudad;
	
	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
}
