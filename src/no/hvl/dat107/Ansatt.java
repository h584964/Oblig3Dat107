package no.hvl.dat107;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "oblig_jpa")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int ansattId;
//	@Column(unique = true)
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansettelsedato;
	private String stilling;
	private int maanedslonn;

	// Notering
	@ManyToOne
	@JoinColumn(name = "avdelingId" ,referencedColumnName = "avdelingId")
	private Avdeling avdeling;
//	private List<Ansatt> ansatte;

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

	public Ansatt() {
		
	}
	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelsedato,
			String stilling, int maanedslonn, Avdeling avdeling) {

//		this.ansattId = ansattId;
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsedato = ansettelsedato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avdeling = avdeling;

	}


	@Override
	public String toString() {
		return "Ansatt [ansattId="+ ansattId + ", brukernavn=" + brukernavn + ", fornavn=" + fornavn + ", etternavn="
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
