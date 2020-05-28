package com.br.vxassist.fx.view.comp.colunas;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ColunaInteger<T> extends TableColumn<T, Integer> {

	public ColunaInteger(String titulo, String campo, double largura) {
		super(titulo);
		setCellValueFactory(new PropertyValueFactory<T, Integer>(campo));
		setStyle("-fx-alignment: CENTER;");
		setMinWidth(largura);
		setSortable(true);
	}
}
