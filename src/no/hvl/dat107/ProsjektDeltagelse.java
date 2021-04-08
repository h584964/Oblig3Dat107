package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prosjektDeltagelse", schema = "oblig_jpa")
public class ProsjektDeltagelse { // dette er mellomTabellen
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektDeltagelse_Id;
	
	private int timer;
	 
	private String rolle;
	
    @ManyToOne
    @JoinColumn(name="ansattid") 
    private Ansatt ansatt;
	
    
    @ManyToOne
    @JoinColumn(name="prosjektid")  
    private Prosjekt prosjekt;
    
  
    
    public ProsjektDeltagelse() {
    	
    }


	public ProsjektDeltagelse(Ansatt ansatt, Prosjekt prosjekt, int timer, String rolle) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		this.timer = timer;
		this.rolle = rolle;
		

	}
	
	public void leggTilTimer(int timer) {
		this.timer = this.timer + timer;
	}


	public int getTimer() {
		return timer;
	}


	public void setTimer(int timer) {
		this.timer = timer;
	}


	public Ansatt getAnsatt() {
		return ansatt;
	}


	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}


	public Prosjekt getProsjekt() {
		return prosjekt;
	}


	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}


	public String getRolle() {
		return rolle;
	}


	public void setRolle(String rolle) {
		this.rolle = rolle;
	}


	@Override
	public String toString() {
		return "ProsjektDeltagelse [timer=" + timer + ", rolle=" + rolle + ", prosjekt="
				+ prosjekt + "]";
	}


	
	
	
    
    
}
