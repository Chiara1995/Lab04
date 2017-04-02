package it.polito.tdp.lab04.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();

	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		this.model=model;
		this.comboCorso.getItems().addAll(model.getElencoCorsi());
		this.comboCorso.getItems().add(new Corso(null,0,null,0));
        if(this.comboCorso.getItems().size()>0)
        	this.comboCorso.setValue(this.comboCorso.getItems().get(0));
	}

	@FXML
	void doReset(ActionEvent event) {
		this.txtResult.clear();
		this.txtCognome.clear();
		this.txtNome.clear();
		this.txtMatricola.clear();
	}

	@FXML
	void doCercaNome(ActionEvent event) {
		int matricola=Integer.parseInt(txtMatricola.getText());
		Studente stemp=model.getStudente(matricola);
		if(stemp==null){
			txtResult.setText("Matricola "+matricola+" non trovata.");
		}
		else{
			txtNome.setText(stemp.getNome());
			txtCognome.setText(stemp.getCognome());
		}
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		Corso ctemp=this.comboCorso.getValue();
		if(ctemp.equals(new Corso(null, 0, null, 0))){
			txtResult.setText("Errore: selezionare un corso.");
		}
		else{
			List<Studente> stemp=model.getStudentiIscritti(ctemp);
			for(Studente s : stemp)
				txtResult.appendText(s+"\n");
		}
	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
	}

	@FXML
	void doIscrivi(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	}

}
