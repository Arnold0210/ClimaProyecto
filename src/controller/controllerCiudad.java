package controller;

import model.DTO.Ciudad;

import java.util.List;

import model.DAO.CiudadDAO;

public class controllerCiudad {
	CiudadDAO DAOcity;
	public static final boolean DEFAULT_STATUS = true;

	public void createCiudad(String nombre, boolean estado) {
		DAOcity = new CiudadDAO();
		Ciudad ciudad = new Ciudad();
		try {
			ciudad.setIdciudad(DAOcity.searchId());
			ciudad.setNombre(nombre);
			ciudad.setEstado(estado);
			DAOcity.create(ciudad);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	}

	public Ciudad selectCiudad(int id) {
		DAOcity = new CiudadDAO();
		Ciudad ciudad = DAOcity.select(id);
		return ciudad;
	}

	public List<Ciudad> selectAllCities() {
		DAOcity = new CiudadDAO();
		List<Ciudad> ciudades = DAOcity.selectall();
		return ciudades;
	}
	
	/*
	 * private void updateCiudad(String nombre, boolean estado) { DAOcity = new
	 * CiudadDAO(); Ciudad ciudad = new Ciudad(); try { DAOcity.update(id, nombre);
	 * }catch (Exception e) {}
	 * 
	 * }
	 */
}
