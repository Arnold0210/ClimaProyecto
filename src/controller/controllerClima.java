package controller;

import java.sql.Time;
import java.sql.Timestamp;
import model.DAO.ClimaDAO;
import model.DTO.Ciudad;
import model.DTO.Clima;
import java.util.Calendar;

public class controllerClima {
	ClimaDAO DAOClima;
	public void createClima( boolean estado, double humedad, double temperatura, Ciudad ciudad) {
		DAOClima = new ClimaDAO();
		Clima clima = new Clima();
		try {
			clima.setIdclima(DAOClima.searchId());
			clima.setEstado(estado);
			clima.setHumedad(humedad);
			clima.setTemperatura(temperatura);
			clima.setCiudad(ciudad);
			Timestamp fecha = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			clima.setFecha(fecha);
			Time hora = new java.sql.Time(Calendar.getInstance().getTime().getTime());
			clima.setHora(hora);
			DAOClima.create(clima);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
}
