package com.br.vxassist.fx.view.comp.colunas;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ColunaCnpj<T> extends TableColumn<T, String> {

	public ColunaCnpj(String titulo, String campo, double largura) {
		super(titulo);
		setCellValueFactory(new PropertyValueFactory<T, String>(campo));
		setCellFactory(column -> {
			return new TableCell<T, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setStyle("");
						setText(null);
					} else {
						item = item.replaceAll("[^0-9]", "");
						item = item.replaceFirst("(\\d{2})(\\d)", "$1.$2");
						item = item.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
						item = item.replaceFirst("\\.(\\d{3})(\\d)", ".$1/$2");
						item = item.replaceFirst("(\\d{4})(\\d)", "$1-$2");
						setText(item);
					}
				}
			};
		});
		setMinWidth(largura == 0d ? 80 : largura);
	}
}
