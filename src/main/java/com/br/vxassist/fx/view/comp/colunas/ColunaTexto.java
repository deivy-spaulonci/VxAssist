package com.br.vxassist.fx.view.comp.colunas;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ColunaTexto<T> extends TableColumn<T, String> {

	public ColunaTexto(String titulo, String campo, Double largura, String alinhamentoCSS) {
		super(titulo);
		setCellValueFactory(new PropertyValueFactory<T, String>(campo));
		setStyle("-fx-alignment: "+alinhamentoCSS+";");
		if(largura!=null)
			setMinWidth(largura);
	}
}
