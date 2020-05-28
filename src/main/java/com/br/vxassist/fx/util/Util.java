package com.br.vxassist.fx.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.javafx.IconNode;

import java.math.BigDecimal;
import java.text.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class Util {
	
	public static NumberFormat nf 		= new DecimalFormat("#,###,##0.00");
	public static SimpleDateFormat df 	= new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat dfUs = new SimpleDateFormat("yyyy-MM-dd");
	public static String[] monthName = {"Janeiro", "Fevereiro", "Mar�o", "Abril", "Maio", "Junho", "Julho","Agosto", "Setembro", "Outubro", "Novembro","Dezembro"};

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ALERTAS
	public static void alertErro(String aviso) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-text-fill: orange; -fx-font-weight : bold;");
		alert.setTitle("Erro");
			
		alert.setContentText(aviso);
		alert.showAndWait();
	}
	
	public static void alertInfoSucesso(String titulo, String aviso) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);		
		alert.setHeaderText("Sucesso");
		alert.setContentText(aviso);
		alert.showAndWait();
		
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DIALOGS
	public static boolean confirmaExclusao(String msgConfirmacao, Window window){
		Dialog<Boolean> dialog = new Dialog<Boolean>();
		dialog.setTitle("Confirma Exclus�o");
		dialog.setHeaderText(msgConfirmacao);
		dialog.initOwner(window);
		IconNode iconNode = new IconNode(FontAwesome.EXCLAMATION_TRIANGLE);
		iconNode.setIconSize(64);
		iconNode.setFill(Color.DARKORANGE);
		dialog.setGraphic(iconNode);
		
		ButtonType btExcluir = new ButtonType("Excluir", ButtonData.OK_DONE);
		ButtonType btCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
	    
		dialog.getDialogPane().getButtonTypes().clear();
		dialog.getDialogPane().getButtonTypes().addAll(btCancelar, btExcluir);

		Node delButton = dialog.getDialogPane().lookupButton(btExcluir);
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == btExcluir) {
				return true;
			}
			return false;
		});
		return dialog.showAndWait().get();
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> BOTOES
	public static Button btDefault(String texto, FontAwesome font, Double larg, EventHandler<ActionEvent> action) {
		Button button = new Button(texto);
		button.getStyleClass().add("button-raised");
		button.setOnAction(action);
		IconNode iconNode = new IconNode(font);
		iconNode.setIconSize(16);
		button.setGraphic(iconNode);
		button.setDefaultButton(true);
		if(larg==null) {
			button.setMinWidth(100);
		}
		return button;		
	}
	public static Button btNovo(Double larg, EventHandler<ActionEvent> action) {
		return btDefault("Novo", FontAwesome.PLUS_SQUARE_O, larg, action);		
	}
	public static Button btEditar(Double larg, EventHandler<ActionEvent> action) {
		return btDefault("Editar", FontAwesome.PENCIL_SQUARE_O, larg, action);		
	}	
	public static Button btCancel(Double larg, EventHandler<ActionEvent> action) {
		return btDefault("Cancelar", FontAwesome.TIMES_CIRCLE, larg, action);
	}
	public static Button btSalvar(Double larg, EventHandler<ActionEvent> action) {
		return btDefault("Salvar", FontAwesome.CHECK_SQUARE_O, larg, action);
	}
	public static Button btConsultar(Double larg, EventHandler<ActionEvent> action) {		
		return btDefault("Consultar", FontAwesome.SEARCH, larg, action);
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ITENS CONTEXTO MENU
	public static MenuItem itemMenuDefault(String texto, EventHandler<ActionEvent> value) {
		MenuItem item = new MenuItem(texto);		
		item.setOnAction(value);
		return item;
	}
	public static MenuItem itemEditar(EventHandler<ActionEvent> value) {
		MenuItem item = new MenuItem("Editar");
		item.setOnAction(value);	
		IconNode iconNode = new IconNode(FontAwesome.PENCIL_SQUARE_O);
		iconNode.setIconSize(14);
		item.setGraphic(iconNode);
		return item;
	}
	public static MenuItem itemNovo(EventHandler<ActionEvent> value) {
		MenuItem item = new MenuItem("Novo(a)");
		item.setOnAction(value);	
		IconNode iconNode = new IconNode(FontAwesome.PLUS_SQUARE_O);
		iconNode.setIconSize(14);
		item.setGraphic(iconNode);
		return item;
	}
	public static MenuItem itemExclui(EventHandler<ActionEvent> value) {
		MenuItem item = new MenuItem("Excluir");
		item.setOnAction(value);	
		IconNode iconNode = new IconNode(FontAwesome.TIMES);
		iconNode.setIconSize(14);
		item.setGraphic(iconNode);
		return item;
	}	

	public static ImageView getImage(String arquivo) {
		//return new ImageView(new Image(Main.class.getResourceAsStream("view/img/" + arquivo)));
		return null;
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> VALIDA��O
	public static boolean stringNullOrEmpty(String texto){
		return (texto==null || texto.trim().isEmpty());
	}
	public static boolean inputTextNullOrEmpty(TextField field) {
		return (field.getText()==null || field.getText().trim().isEmpty());
	}
	public static boolean moedaStringValida(String valor) {
		return (valor.trim().isEmpty() || valor.equals("0,00") || valor.equals("00") || valor.equals("0"));
	}
/*	
	public Object boolean comboInvalido(ComboBox combo){
		return combo.getSelectionModel().getSelectedItem();		
	}
*/	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CONVERS�O
	
	public static BigDecimal moedaBrToBigDecimal(String valor) {
		if(valor==null || valor.trim().isEmpty())
			return BigDecimal.ZERO;
		else {
			Locale Local = new Locale("pt","BR");
			DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Local));
			try {
				return new BigDecimal(df.parseObject(valor).toString());
			} catch (ParseException e) {
				e.printStackTrace();
				return BigDecimal.ZERO;
			}
		}
	}
	
	public static Integer inputTextToInteger(TextField field) {
		if(field.getText() == null || field.getText().trim().isEmpty())
			return null;
		return Integer.valueOf(field.getText());
	}
	public static Date localDatetoDate(LocalDate  localDate) {
		if(localDate!=null) {
			Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			return Date.from(instant);			
		}
		return null;
	}
	public static Date dataStringBRToDateUS(String data) {
		try {
			df.setLenient(false);			
			return df.parse(data);
		} catch (ParseException e) {
			return null;
		}
	}

	public static LocalDate dataToLocalDate(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}
	
	public static boolean dataEntrePeriodo(Date data, LocalDate inicio, LocalDate fim) {
		if(inicio==null && fim==null) {
			return true;
		}else if(inicio!=null || fim!=null) {
			if(inicio!=null && fim!=null) {
				if((data.equals(localDatetoDate(inicio)) || data.after(localDatetoDate(inicio))) &&
					(data.equals(localDatetoDate(fim)) || data.before(localDatetoDate(fim)))){
					return true;
				}
				return false;
			}else if(inicio!=null && fim==null) {
				if(data.equals(localDatetoDate(inicio)) || data.after(localDatetoDate(inicio))) {
					return true;
				}
				return false;
			}else if(inicio==null && fim!=null) {
				if(data.equals(localDatetoDate(fim)) || data.before(localDatetoDate(fim))) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ICONES
	public static IconNode getIcone(FontAwesome font, Color color, Number tamanho){
		IconNode iconNode = new IconNode(font);
		if(color!=null)
			iconNode.setFill(color);
		if(tamanho!=null)
			iconNode.setIconSize(tamanho);
		return iconNode;
	}
}
