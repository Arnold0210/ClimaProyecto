package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idciudad;

	private Boolean estado;

	private String nombre;

	//bi-directional many-to-one association to Clima
	@OneToMany(mappedBy="ciudad")
	private Set<Clima> climas;

	public Ciudad() {
		
	}

	public Integer getIdciudad() {
		return this.idciudad;
	}

	public void setIdciudad(Integer idciudad) {
		this.idciudad = idciudad;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Clima> getClimas() {
		return this.climas;
	}

	public void setClimas(Set<Clima> climas) {
		this.climas = climas;
	}

	public Clima addClima(Clima clima) {
		getClimas().add(clima);
		clima.setCiudad(this);

		return clima;
	}

	public Clima removeClima(Clima clima) {
		getClimas().remove(clima);
		clima.setCiudad(null);

		return clima;
	}

}