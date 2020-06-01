package com.br.vxassist.fx.view.comp;


import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.control.*;

public class GridDefault extends GridPane{
	public GridDefault() {
		setPadding(new Insets(5));
		setHgap(8);
		setVgap(8);
	}
	public GridDefault(int hgap, int vgap) {
		setHgap(hgap);
		setVgap(vgap);
	}
	public GridDefault(int hgap, int vgap, int padding) {
		setHgap(hgap);
		setVgap(vgap);
		setPadding(new Insets(padding));
	}
	public void addLinha(String label, Node node, int coluna, int linha, int colspan) {
		Label rotulo = new Label(label);
		rotulo.setLabelFor(node);
		add(rotulo, coluna, linha, 1, 1);
		add(node, coluna+1, linha, colspan, 1);
	}
	
}
