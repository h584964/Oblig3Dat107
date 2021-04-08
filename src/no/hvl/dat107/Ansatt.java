package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "oblig_jpa")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattId;
	private String brukernavn, fornavn, etternavn, stilling;
	private LocalDate ansettelsedato;
	private int maanedslonn;

	// Notering
	@ManyToOne
	@JoinColumn(name = "avdelingId", referencedColumnName = "avdelingId")
	private Avdeling avdeling;
//	private List<Ansatt> ansatte;
	
//	@ManyToMany(mappedBy="ansatte")
//	private List<Prosjekt> prosjekter;
//	
	@OneToMany(mappedBy="ansatt")
	private List<ProsjektDeltagelse> deltagelser;

	public int getAnsattId() {
		return ansattId;
	}

	public void setAnsattId(int ansattId) {
		this.ansattId = ansattId;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public int getMaanedslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(int maanedslonn) {
		this.maanedslonn = maanedslonn;
	}

	public String getNavn() {
		return fornavn;
	}

	public Avdeling getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}
	

//	public List<Prosjekt> getProsjekter() {
//		return prosjekt();
//	}
//
//	public void setProsjekter(List<Prosjekt> prosjekter) {
//		this.prosjekter = prosjekter;
//	}

	public List<ProsjektDeltagelse> getDeltagelser() {
		return deltagelser;
	}

	public void setDeltagelser(List<ProsjektDeltagelse> deltagelser) {
		this.deltagelser = deltagelser;
	}

	public Ansatt() {

	}

	public Ansatt(int ansattId,String brukernavn, String fornavn, String etternavn, LocalDate ansettelsedato, String stilling,
			int maanedslonn, Avdeling avdeling) {

		this.ansattId = ansattId;
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsedato = ansettelsedato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avdeling = avdeling;

	}
	
	  public void leggTilProsjektdeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
	        deltagelser.add(prosjektdeltagelse);
	    }
	  
	  

	@Override
	public String toString() {
		return "Ansatt [ansattId=" + ansattId + ", brukernavn=" + brukernavn + ", fornavn=" + fornavn + ", etternavn="
				+ etternavn + ", ansettelsedato=" + ansettelsedato + ", stilling=" + stilling + ", maanedslonn="
				+ maanedslonn + ", avdeling=" + avdeling.getNavn() + "]";
	}

	public void skrivUt() {
		System.out.println(this);
	}

	

//	public void leggTilStilling(String stilling) {
//		ansatte.add(this);
//	}

}
