package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Studente;

public class TestStudenteDAO {

	public static void main(String[] args) {
		
		StudenteDAO sdao=new StudenteDAO();
		Studente s1=sdao.getStudente(146101);
		System.out.println(s1);
		Studente s2=sdao.getStudente(146102);
		System.out.println(s2);
	}
}
