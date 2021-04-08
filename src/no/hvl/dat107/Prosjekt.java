package no.hvl.dat107;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prosjekt", schema = "oblig_jpa")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektId;
	private String prosjektNavn;

	@OneToMany(mappedBy = "prosjekt")
	private List<ProsjektDeltagelse> deltagelser;

	private List<Ansatt> ansatte;

	public int getprosjektId() {
		return prosjektId;
	}

	public void setprosjektId(int prosjektId) {
		this.prosjektId = prosjektId;
	}

	public String getProsjektNavn() {
		return prosjektNavn;
	}

	public void setProsjektNavn(String prosjektNavn) {
		this.prosjektNavn = prosjektNavn;
	}
	
	public List<ProsjektDeltagelse> getDeltagelser() {
		return deltagelser;
	}

	public void setDeltagelser(List<ProsjektDeltagelse> deltagelser) {
		this.deltagelser = deltagelser;
	}

	public void leggTilProsjektdeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
	       
		deltagelser.add(prosjektdeltagelse);
	}

	public Prosjekt() {

	}

	public Prosjekt(int prosjektId, String prosjektNavn) { 
		this.prosjektId = prosjektId;
		this.prosjektNavn = prosjektNavn;

	}

	@Override
	public String toString() {
		return "Prosjekt [prosjektId=" + prosjektId + ", prosjektNavn=" + getProsjektNavn() + "]"; // ", ansatte=" + ansatte
																								// +
	}

//	public void skrivUt() {
//		System.out.println(this);
//	}
//


}
