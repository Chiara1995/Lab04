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
	private Button btnCerca;
	
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
		this.comboCorso.getItems().add(new Corso(null,0,null,0));
		this.comboCorso.getItems().addAll(model.getElencoCorsi());
        if(this.comboCorso.getItems().size()>0)
        	this.comboCorso.setValue(this.comboCorso.getItems().get(0));
	}

	@FXML
	void doReset(ActionEvent event) {
		this.makeGUIVisible(false);
		this.txtMatricola.setEditable(true);
		this.txtResult.clear();
		this.txtCognome.clear();
		this.txtNome.clear();
		this.txtMatricola.clear();
		this.comboCorso.getSelectionModel().clearSelection();
		return;
	}
	
	public void makeGUIVisible(boolean visible){
		this.btnCercaCorsi.setDisable(visible);
		this.btnCercaIscrittiCorso.setDisable(visible);
		this.btnCercaNome.setDisable(visible);
		this.btnIscrivi.setDisable(visible);
		this.comboCorso.setDisable(visible);
	}
	
	@FXML
	void doCercaNome(ActionEvent event) {
		txtResult.clear();
		txtNome.clear();
		txtCognome.clear();
		if(!txtMatricola.getText().matches("[\\d]+")){
			txtResult.setText("Errore: inserire matricola nel formato corretto.");
			return;
		}
		int matricola=Integer.parseInt(txtMatricola.getText());
		Studente stemp=model.getStudente(matricola);
		if(stemp==null){
			txtResult.setText("Studente con matricola "+matricola+" non presente nel database.\n");
			return;
		}
		else{
			txtNome.setText(stemp.getNome());
			txtCognome.setText(stemp.getCognome());
			return;
		}
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		Corso ctemp=this.comboCorso.getValue();
		if(ctemp.equals(new Corso(null, 0, null, 0))){
			txtResult.setText("Errore: nessun corso selezionato.\n");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		List<Studente> stemp=model.getStudentiIscritti(ctemp);
		if(stemp.size()==0){
			txtResult.setText("Errore: corso senza iscritti.\n");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		for(Studente s : stemp)
			txtResult.appendText(s+"\n");
		this.makeGUIVisible(true);
		this.txtMatricola.setEditable(false);
		return;				
	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		if(!txtMatricola.getText().matches("[\\d]+")){
			txtResult.setText("Errore: inserire matricola nel formato corretto.");
			return;
		}
		int matricola=Integer.parseInt(txtMatricola.getText());
		Studente stemp=model.getStudente(matricola);
		if(stemp==null){
			txtResult.setText("Studente con matricola "+matricola+" non presente nel database.");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		List<Corso> clist=model.getCorsiStudente(stemp);
		if(clist.size()==0){
			txtResult.setText("Studente non iscritto ad alcun corso.\n");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		for(Corso c : clist)
			txtResult.appendText(c.toString2()+"\n");
		this.makeGUIVisible(true);
		this.txtMatricola.setEditable(false);
		return;
				
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		Corso ctemp=comboCorso.getValue();
		if(ctemp.equals(new Corso(null, 0, null, 0))){
			txtResult.setText("Errore: nessun corso selezionato.");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		if(txtMatricola.getText().isEmpty()){
			txtResult.setText("Errore: inserire una matricola");
			return;
		}
		int matricola=Integer.parseInt(txtMatricola.getText());
		Studente stemp=model.getStudente(matricola);
		if(stemp==null){
			txtResult.setText("Matricola "+matricola+" non presente nel database.");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		//Controllo se studente è già iscritto al corso
		if(model.isStudenteIscrittoACorso(stemp, ctemp)){
			txtResult.setText("Studente già iscritto al corso");
			return;
		}
		if(!model.iscrivi(stemp, ctemp)){
			txtResult.setText("Errore: iscrizione non avvenuta.");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		else{
			txtResult.setText("Studente "+stemp.getMatricola()+" iscritto al corso "+ctemp.getCodice()+".");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		
	}
	
	@FXML
	void doIscritto(ActionEvent event) {
		if(!txtMatricola.getText().matches("[\\d]+")){
			txtResult.setText("Errore: inserire matricola nel formato corretto.");
			return;
		}
		int matricola=Integer.parseInt(txtMatricola.getText());
		Studente stemp=model.getStudente(matricola);
		if(stemp==null){
			txtResult.setText("Studente con matricola "+matricola+" non presente nel database.\n");
			return;
		}
		Corso ctemp=this.comboCorso.getValue();
		if(ctemp.equals(new Corso(null, 0, null, 0))){
			txtResult.setText("Errore: nessun corso selezionato.\n");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		List<Studente> studenti=model.getStudentiIscritti(ctemp);
		if(studenti.size()==0){
			txtResult.setText("Errore: corso senza iscritti.\n");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		if(!studenti.contains(stemp)){
			txtResult.setText("Studente con matricola "+stemp.getMatricola()+" non iscritto al corso "+ctemp.getNome()+" .");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}
		else{
			txtResult.setText("Studente con matricola "+stemp.getMatricola()+" iscritto al corso "+ctemp.getNome()+" .");
			this.makeGUIVisible(true);
			this.txtMatricola.setEditable(false);
			return;
		}				
		
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
	
		//Utilizzare questo font per incolonnare correttamente i dati
		txtResult.setStyle("-fx-font-family: monospace");
		
	}

}
