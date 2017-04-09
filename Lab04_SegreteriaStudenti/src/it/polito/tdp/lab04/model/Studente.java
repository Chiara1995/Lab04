package it.polito.tdp.lab04.model;

import java.util.*;

public class Studente {
	
	private int matricola;
	private String cognome;
	private String nome;
	private String cds;
	
	private List<Corso> corsiIscrizione;
	
	public Studente(int matricola, String cognome, String nome, String cds) {
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
		corsiIscrizione=new LinkedList<Corso>();
	}

	/**
	 * @return the corsiIscrizione
	 */
	public List<Corso> getCorsiIscrizione() {
		return corsiIscrizione;
	}

	/**
	 * @param corsiIscrizione the corsiIscrizione to set
	 */
	public void aggiungiCorso(Corso c) {
		corsiIscrizione.add(c);
	}

	/**
	 * @return the matricola
	 */
	public int getMatricola() {
		return matricola;
	}

	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cds
	 */
	public String getCds() {
		return cds;
	}

	/**
	 * @param cds the cds to set
	 */
	public void setCds(String cds) {
		this.cds = cds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return matricola+" "+nome+" "+cognome+" "+cds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}	
		
}
