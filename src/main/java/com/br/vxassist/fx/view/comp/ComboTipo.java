package com.br.vxassist.fx.view.comp;

import javafx.scene.control.ComboBox;


public class ComboTipo<T> extends ComboBox<T>{
	public ComboTipo() {}
	
	public String nomeSelecionado() {
		if(getSelectionModel().isEmpty() || getValue()==null)
			return "";
		return getValue().toString();			
	}	
}
