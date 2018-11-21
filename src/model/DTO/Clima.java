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
@NamedQueries({ @NamedQuery(name = "Clima.findAll", query = "SELECT c FROM Clima c"),
	@NamedQuery(name = "Clima.selectweatherbycity", query = "SELECT c FROM Clima c WHERE c.ciudad.idciudad = :idciudad"),
	@NamedQuery(name = "Clima.selectweatherbycityindex", query = "SELECT c FROM Clima c,Ciudad t WHERE c.ciudad.idciudad = t.idciudad group by c.ciudad.nombre order by c.ciudad.nombre"),
	@NamedQuery(name = "Clima.selectMaxAllTemp", query = "SELECT MAX(c.temperatura) FROM Clima c WHERE c.ciudad.idciudad=:idciudad"),
	@NamedQuery(name = "Clima.selectMinAllTemp", query = "SELECT MIN(c.temperatura) FROM Clima c WHERE c.ciudad.idciudad=:idciudad"),
	@NamedQuery(name = "Clima.selectMaxAllHumi", query = "SELECT MAX(c.humedad) FROM Clima c WHERE c.ciudad.idciudad=:idciudad"),
	@NamedQuery(name = "Clima.selectMinAllHumi", query = "SELECT MIN(c.humedad) FROM Clima c WHERE c.ciudad.idciudad=:idciudad"),
	@NamedQuery(name = "Clima.selectLastDayWeather", query = "SELECT c FROM Clima c WHERE c.ciudad.idciudad = :idciudad AND c.fecha <= current_date AND c.fecha >=:fecha"),
	@NamedQuery(name = "Clima.selectavgTemp",query = "SELECT AVG(c.temperatura) FROM Clima c where c.ciudad.idciudad =:idciudad group by c.fecha"),
	@NamedQuery(name = "Clima.selectavgHumi",query = "SELECT AVG(c.humedad) FROM Clima c where c.ciudad.idciudad =:idciudad group by c.fecha"),
	@NamedQuery(name = "Clima.selectdate",query = "SELECT c.fecha FROM Clima c where c.ciudad.idciudad =:idciudad group by c.fecha"),
	@NamedQuery(name = "Clima.selectdateWeather", query = "select c from Clima c where  c.ciudad.idciudad = :idciudad and c.fecha = CURRENT_DATE")})

public class Clima implements Serializable{
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