package com.br.vxassist.fx.view.comp.colunas;

import com.br.vxassist.fx.util.Util;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class ColunaPorcentagem<T> extends TableColumn<T, BigDecimal>{

	public ColunaPorcentagem(String titulo, String campo, double largura) {
		super(titulo);
		
		setCellValueFactory(new PropertyValueFactory<T, BigDecimal>(campo));
		setCellFactory(column -> {return new TableCell<T, BigDecimal>(){
			@Override
			protected void updateItem(BigDecimal item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty) {
					setStyle("");
					setText(null);
				} else {
					 setStyle("-fx-text-fill: green; -fx-alignment: CENTER-RIGHT;");
					 setText(Util.nf.format(item) + " %");
				}
			}			
		};});
		
		setMinWidth(largura == 0d ? 80 : largura);
	}
}
