package view;

import javax.faces.bean.ManagedBean;

import controller.controllerCiudad;
import model.DTO.Ciudad;
import model.DTO.Clima;

@ManagedBean
public class ciudadBean {
	private String nombreCiudad;
	private boolean estado;
	private int idciudad;
	private controllerCiudad cCity = new controllerCiudad();

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getIdciudad() {
		return idciudad;
	}

	public void setIdciudad(int idciudad) {
		this.idciudad = idciudad;
	}

	public void buscarCiudad() {
		String ciudad_form = getNombreCiudad();
		Ciudad ciudad_model = cCity.selectCiudadNombre(ciudad_form);
		System.out.println(ciudad_model.getIdciudad());

	}

}