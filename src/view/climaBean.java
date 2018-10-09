package view;
import java.sql.Time;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.DTO.Ciudad;

@ManagedBean(name = "ClimaBean")
public class climaBean {
	private double temperatura;
	private double humedad;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private Time hora;
	private int idClima;
	private Boolean estado;
	private Ciudad ciudad;
	public climaBean() {

	}
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public double getHumedad() {
		return humedad;
	}
	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public int getIdClima() {
		return idClima;
	}
	public void setIdClima(int idClima) {
		this.idClima = idClima;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}
