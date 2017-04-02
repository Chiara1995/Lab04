package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	public List<Corso> getElencoCorsi(){
		CorsoDAO cdao=new CorsoDAO();
		List<Corso> corsi=new LinkedList<Corso>();
		corsi.addAll(cdao.getTuttiICorsi());
		return corsi;
	}

	public Studente getStudente(int matricola) {
		StudenteDAO sdao=new StudenteDAO();
		Studente stemp=sdao.getStudente(matricola);	
		return stemp;
	}
	
	public List<Studente> getStudentiIscritti(Corso corso){
		CorsoDAO cdao=new CorsoDAO();
		cdao.getStudentiIscrittiAlCorso(corso);
		return corso.getStudentiIscritti();
	}
	
}
