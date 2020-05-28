package com.br.vxassist.fx.view.comp.colunas;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ColunaObjeto<T> extends TableColumn<T, Object> {
	public ColunaObjeto(String titulo, String campo, Double largura, String alinhamentoCSS) {
		super(titulo);
		setCellValueFactory(new PropertyValueFactory<T, Object>(campo));
		setStyle("-fx-alignment: "+alinhamentoCSS+";");
		if(largura!=null)
			setMinWidth(largura);
	}
}
