package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	public List<Corso> getElencoCorsi(){
		CorsoDAO cdao=new CorsoDAO();
		List<Corso> corsi=new LinkedList<Corso>();
		corsi.addAll(cdao.getTuttiICorsi());
		Collections.sort(corsi, new Comparator<Corso>(){
			public int compare(Corso c1, Corso c2){
				return c1.getNome().compareTo(c2.getNome());
			}
		});
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
	
	public List<Corso> getCorsiStudente(Studente studente){
		StudenteDAO sdao=new StudenteDAO();
		sdao.getCorsiStudente(studente);
		return studente.getCorsiIscrizione();
	}
	
	public boolean iscrivi(Studente studente, Corso corso){
		CorsoDAO cdao=new CorsoDAO();
		if(cdao.iscriviStudenteACorso(studente, corso))
			return true;
		else
			return false;
	}
	
}
