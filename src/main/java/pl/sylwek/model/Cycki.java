package pl.sylwek.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cycki")
public class Cycki implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id_test;

	@Column(name = "rozmiar")
	private String rozmiar;

	public int getId_test() {
		return id_test;
	}

	public void setId_test(int id_test) {
		this.id_test = id_test;
	}

	public String getRozmiar() {
		return rozmiar;
	}

	public void setRozmiar(String rozmiar) {
		this.rozmiar = rozmiar;
	}

	@Override
	public String toString() {
		return "Cycki [id_test=" + id_test + ", rozmiar=" + rozmiar + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_test;
		result = prime * result + ((rozmiar == null) ? 0 : rozmiar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cycki other = (Cycki) obj;
		if (id_test != other.id_test)
			return false;
		if (rozmiar == null) {
			if (other.rozmiar != null)
				return false;
		} else if (!rozmiar.equals(other.rozmiar))
			return false;
		return true;
	}
}
