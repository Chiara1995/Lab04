package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	/*
	 * Ottengo studente data la matricola
	 */
	public Studente getStudente(int matricola) {
		
		final String sql = "SELECT matricola, cognome, nome, CDS "+
							"FROM studente "+
							"WHERE matricola=?";
		Studente result=null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				Studente stemp=new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				result=stemp;
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	/*
	 * Ottengo tutti i corsi a cui è iscritto lo studente
	 */
	public void getCorsiStudente(Studente studente) {
		
		final String sql = "SELECT corso.codins, corso.crediti, corso.nome, corso.pd "+
							"FROM corso, iscrizione "+
							"WHERE corso.codins=iscrizione.codins AND iscrizione.matricola=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso c=new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				studente.aggiungiCorso(c);
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	/*
	 * Controllo se uno studente (matricola) è iscritto ad un corso (codins)
	 */
	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {

		final String sql = "SELECT * "+
						   "FROM iscrizione "+
						   "WHERE codins=? AND matricola=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodice());
			st.setInt(2, studente.getMatricola());

			ResultSet rs = st.executeQuery();

			boolean returnValue = false;
			if (rs.next())
				returnValue = true;
			conn.close();
			return returnValue;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
		
}
