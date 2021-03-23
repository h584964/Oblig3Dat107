package no.hvl.dat107;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "oblig_jpa")
public class Ansatt {

	@Id
	private int ansattId;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansettelsedato;
	private String stilling;
	private int maanedslonn;

	// Notering
//	@OneToMany(mappedBy = "ansatt")
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
	
	public Ansatt() {
		
	}
	public Ansatt(int ansattId, String brukernavn, String fornavn, String etternavn, LocalDate ansettelsedato,
			String stilling, int maanedslonn) {

		this.ansattId = ansattId;
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsedato = ansettelsedato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;

	}

	

	public int getMaanedslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(int maanedslonn) {
		this.maanedslonn = maanedslonn;
	}

	@Override
	public String toString() {
		return "Ansatt [ansattId=" + ansattId + ", brukernavn=" + brukernavn + ", fornavn=" + fornavn + ", etternavn="
				+ etternavn + ", ansettelsedato=" + ansettelsedato + ", stilling=" + stilling + ", maanedslonn="
				+ maanedslonn + "]";
	}

	public void skrivUt() {
		System.out.println(this);
	}

//	public void leggTilStilling(String stilling) {
//		ansatte.add(this);
//	}

}
