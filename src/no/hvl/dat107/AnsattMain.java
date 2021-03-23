package no.hvl.dat107;

import java.time.LocalDate;
import java.util.Scanner;

public class AnsattMain {

	public static void main(String[] args) {
		AnsattDAO ansattDAO = new AnsattDAO();
		
		boolean run = true;
		LocalDate ansettelsedato;
		int ansattId, maanedslonn;
		String brukernavn, fornavn, etternavn, stilling;
		Scanner tast = new Scanner(System.in);
		while (run) {
			System.out.println("----------------Meny----------------");
			System.out.println();
			System.out.println("Tast 1) for å skrive inn et ansattId");
			System.out.println("Tast 2) for å skrive inn et brukernavn");
			System.out.println("Tast 3) for å skrive ut alle ansatte");
			System.out.println("Tast 4) for oppdatere en ansatt sin stilling");
			System.out.println("Tast 5) for oppdatere en ansatt sin lønn");
			System.out.println("Tast 6) for å legge til en ny ansatt");
			System.out.println("Tast 0) for å avslutte");
			int valg = tast.nextInt();
			tast.nextLine();

			if (valg == 1) {
				System.out.println("Hva er ansatte sitt id-nummer?");
				ansattId = tast.nextInt();
				tast.nextLine();
				Ansatt a = ansattDAO.hentAnsattId(ansattId);
				System.out.println(a);

			} else if (valg == 2) {
				System.out.println("Hva er ansatte sitt brukernavn?");
				brukernavn = tast.nextLine();
				
				Ansatt b = ansattDAO.hentBrukernavn(brukernavn);
				System.out.println(b);
				
			} else if (valg == 3) {
				ansattDAO.skrivUtAlle();
				
			} else if (valg == 4) {
				System.out.println("AnsattId?");
				ansattId = tast.nextInt();
				tast.nextLine();
				System.out.println("Skriv inn en stilling");
				stilling = tast.nextLine();
				ansattDAO.oppdaterStilling(stilling, ansattId);
				
			} else if (valg == 5) {
				System.out.println("AnsattId?");
				ansattId = tast.nextInt();
				tast.nextLine();
				System.out.println("Skriv inn ny lønn");
				maanedslonn = tast.nextInt();
				tast.nextLine();
				ansattDAO.oppdaterLonn(maanedslonn, ansattId);
				
			} else if (valg == 6) {
				System.out.println("Skriv inn et ansattId");
				ansattId = tast.nextInt();
				tast.nextLine();
				
				System.out.println("Skriv inn et brukernavn");
				brukernavn = tast.nextLine();
				
				System.out.println("Skiv inn fornavn");
				fornavn = tast.nextLine();
				
				System.out.println("Skriv inn etternavn");
				etternavn = tast.nextLine();
				
				System.out.println("Skriv inn ansettelsedato");
				ansettelsedato = LocalDate.parse(tast.next());
				tast.nextLine();
				
				System.out.println("Skriv inn stilling");
				stilling = tast.nextLine();
				
				System.out.println("Skriv inn lønn");
				maanedslonn = tast.nextInt();
				tast.nextLine();
				
				Ansatt a = new Ansatt(ansattId, brukernavn, fornavn, etternavn, ansettelsedato, stilling, maanedslonn);
				ansattDAO.leggTilNyAnsatt(a);
				System.out.println(brukernavn + " lagt til med ansattId: " + a.getAnsattId());

			} else if (valg == 0) {
				run = false;
				System.out.println("Programmet avsluttes! Ha en fin dag videre :)");
			}

		}

	}

}
