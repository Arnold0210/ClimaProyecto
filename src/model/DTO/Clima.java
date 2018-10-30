package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the clima database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Clima.findAll", query="SELECT c FROM Clima c"),
	@NamedQuery(name="Clima.selectweatherbycity",query="SELECT w from Clima w WHERE w.ciudad.idciudad = :idciudad")})

public class Clima implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idclima;

	private Boolean estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Time hora;

	private Double humedad;

	private Double temperatura;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	private Ciudad ciudad;

	public Clima() {
	}

	public Integer getIdclima() {
		return this.idclima;
	}

	public void setIdclima(Integer idclima) {
		this.idclima = idclima;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public double getHumedad() {
		return this.humedad;
	}

	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}

	public double getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}