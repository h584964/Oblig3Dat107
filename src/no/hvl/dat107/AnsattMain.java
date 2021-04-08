package no.hvl.dat107;

import java.time.LocalDate;
import java.util.Scanner;

public class AnsattMain {

	public static void main(String[] args) {
		AnsattDAO ansattDAO = new AnsattDAO();
		AvdelingDAO avdelingDAO = new AvdelingDAO();
		ProsjektDAO prosjektDAO = new ProsjektDAO();
		ProsjektDeltagelseDAO pdDAO = new ProsjektDeltagelseDAO();

		boolean run = true;
		LocalDate ansettelsedato;
		int ansattId, maanedslonn, avdelingI, avdelingId, id;

		Ansatt ansatt = null;
		Avdeling avdeling = null;
		Prosjekt prosjekt = null;
		ProsjektDeltagelse pd = null;
		Ansatt sjef = null;

		String brukernavn, fornavn, etternavn, stilling, navn = null;
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
			System.out.println("Tast 7) for å finne avdeling med id");
			System.out.println("Tast 8) for skive ut alle ansatte med en avdeling");
			System.out.println("Tast 9) for oppdatere ansatt sin avdeling");
			System.out.println("Tast 10) for å legge til en ny avdeling");
			System.out.println("Tast 11) for å finne prosjekt med id");
			System.out.println("Tast 12) for å legge til et nytt prosjekt");
			System.out.println("Tast 13) for å regristere et nytt prosjekt");
			System.out.println("Tast 14) for å regristere timer på en ansatt i et prosjekt");
			System.out.println("Tast 15) for å skrive ut ale ansatte på et prosjekt");

			System.out.println("Tast 0) for å avslutte");
			int valg = tast.nextInt();
			tast.nextLine();

			if (valg == 1) {
				System.out.println("Hva er ansatte sitt id-nummer?");
				ansattId = tast.nextInt();
				tast.nextLine();
				Ansatt a = ansattDAO.hentAnsattId(ansattId);
				System.out.println(a.toString());

			} else if (valg == 2) {
				System.out.println("Hva er ansatte sitt brukernavn?");
				brukernavn = tast.nextLine();

				Ansatt b = ansattDAO.hentBrukernavn(brukernavn);
				System.out.println(b.toString());

			} else if (valg == 3) {
				ansattDAO.skrivUtAlle();

			} else if (valg == 4) {
				System.out.println("AnsattId?");
				ansattId = tast.nextInt();
				tast.nextLine();
				System.out.println("Skriv inn en stilling");
				stilling = tast.nextLine();
				ansattDAO.oppdaterStilling(stilling, ansattId);
				System.out.println("AnsattId: " + ansattId + ", " + "ny stilling er: " + stilling);

			} else if (valg == 5) {
				System.out.println("AnsattId?");
				ansattId = tast.nextInt();
				tast.nextLine();
				System.out.println("Skriv inn ny lønn");
				maanedslonn = tast.nextInt();
				tast.nextLine();
				ansattDAO.oppdaterLonn(maanedslonn, ansattId);
				System.out.println();

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

				System.out.println("Avdeling");
				avdelingI = Integer.parseInt(tast.next());
				tast.nextLine();
				avdeling = avdelingDAO.finnAvdelingMedId(avdelingI);

				Ansatt a = new Ansatt(ansattId, brukernavn, fornavn, etternavn, ansettelsedato, stilling, maanedslonn,
						avdeling);
				ansattDAO.leggTilNyAnsatt(a);
				System.out.println(brukernavn + " lagt til med ansattId: " + a.getAnsattId());

			} else if (valg == 7) {
				System.out.println("AvdelingId?");
				avdelingId = tast.nextInt();
				tast.nextLine();
				avdeling = avdelingDAO.finnAvdelingMedId(avdelingId);
				System.out.println(avdeling);

			} else if (valg == 8) {
				System.out.println("Skriv inn avdelingId?");
				avdelingId = tast.nextInt();
				tast.nextLine();
				avdelingDAO.skrivUtAlle(avdelingId);

			} else if (valg == 9) {
				System.out.println("AnsattId?");
				ansattId = tast.nextInt();
				tast.nextLine();
				System.out.println("Skriv inn en avdelingId");
				avdelingId = tast.nextInt();
				tast.nextLine();
				avdeling = avdelingDAO.finnAvdelingMedId(avdelingId);
				if (avdeling != null) {
					avdelingDAO.oppdaterAvdeling(avdeling, ansattId);
				} else {
					System.out.println("fant ikke avdelingen");
				}
				System.out.println("AnsattId: " + ansattId + ", " + "ny avdeling er: " + avdeling.getNavn());

			} else if (valg == 10) {
				System.out.println("Skriv inn navn på en ny avdeling");
				navn = tast.nextLine();
				System.out.println("Skriv inn ansattId for sjef en ansatt som skal bli sjef");
				ansattId = tast.nextInt();
				tast.nextLine();

				ansatt = ansattDAO.hentAnsattId(ansattId);
				if (ansatt.getAvdeling().getSjef() != ansatt) {
					avdeling = new Avdeling(navn, ansatt); // endret sjef til ansatt. Det funker ikke med Ansatt sjef =
															// null
					avdelingDAO.leggTilNyAvdeling(avdeling, ansatt);

				} else {
					System.out.println("Ansatt er allerde sjef i en annen avdeling og kan ikke flyttes");
				}

			} else if (valg == 11) {
				System.out.println("Skriv inn prosjektId");
				id = tast.nextInt();
				tast.nextLine();

				prosjekt = prosjektDAO.finnProsjekt(id);
				System.out.println(prosjekt);

			} else if (valg == 12) {
				System.out.println("Skriv inn id på nytt prosjekt");
				id = tast.nextInt();
				tast.nextLine();

				System.out.println("Skriv inn navn på en nytt prosjek");
				navn = tast.nextLine();

				Prosjekt p = new Prosjekt(id, navn);
				prosjektDAO.leggTilNyttProsjekt(p);
				System.out.println(navn + " ble lagt til " + "med id: " + p.getprosjektId());

			} else if (valg == 13) {
				System.out.println("Skriv inn ansattId");
				ansattId = tast.nextInt();
				tast.nextLine();

				Ansatt a = ansattDAO.hentAnsattId(ansattId);

				System.out.println("Skriv inn prosjektId");
				id = tast.nextInt();
				tast.nextLine();

				Prosjekt p = prosjektDAO.finnProsjekt(id);

				System.out.println("Skriv inn timer");
				int timer = tast.nextInt();
				tast.nextLine();

				System.out.println("Skriv inn rolle");
				String rolle = tast.nextLine();

				pd = new ProsjektDeltagelse(ansatt, prosjekt, timer, rolle);
				pdDAO.registrerProsjektdeltagelse(a, p, timer, rolle);
				System.out.println(pd.toString());

			} else if (valg == 14) {

				System.out.println("Skriv inn ansattId");
				ansattId = tast.nextInt();
				tast.nextLine();
				System.out.println("Skriv inn ProsjektId");
				id = tast.nextInt();
				System.out.println("Skriv inn Timer");
				int timer = tast.nextInt();
				pd = pdDAO.finnProsjekt_A(ansattId, id);
				if (pd != null) {
					pdDAO.registrerTimer(timer, pd);

				}
				System.out.println(timer + " timer registrert på " + pd.getAnsatt().getNavn() + "på " + pd.getProsjekt());

			} else if (valg == 15) {
				System.out.println("prosjektId?");
				id = tast.nextInt();
				tast.nextLine();
				pdDAO.skrivUtProsjekt(id);

			} else if (valg == 0) {
				run = false;
				System.out.println("Programmet avsluttes! Ha en fin dag videre :)");
			}

		}

	}

}
