package com.br.vxassist.fx.view.comp.colunas;

import com.br.vxassist.fx.util.Util;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class ColunaData<T> extends TableColumn<T, Date> {
	public ColunaData(String titulo, String campo, double largura) {
		super(titulo);

		setCellValueFactory(new PropertyValueFactory<T, Date>(campo));
		setCellFactory(column -> {
			return new TableCell<T, Date>() {
				@Override
				protected void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setStyle("");
						setText(null);
					} else {
						setStyle("-fx-alignment: CENTER;");
						setText(Util.df.format(item));
					}
				}
			};
		});

		setMinWidth(largura == 0d ? 90 : largura);
	}
}
