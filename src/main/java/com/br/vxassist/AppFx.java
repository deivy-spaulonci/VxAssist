package com.br.vxassist;

import com.br.vxassist.fx.view.DespesaView;
import com.br.vxassist.fx.view.iconutil.IconUtil;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.TipoDespesaRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.javafx.IconFontFX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class AppFx extends Application {

    private BorderPane borderPane = new BorderPane();
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        //applicationContext = new SpringApplicationBuilder(VxassistfxApplication.class).run();
        this.applicationContext = new SpringApplicationBuilder().sources(VxPayofApplication.class).run();
        this.applicationContext.getBeanFactory().autowireBean(DespesaView.class);
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) {

        IconFontFX.register(FontAwesome.getIconFont());

        borderPane.setPrefWidth(1080);
        borderPane.setPrefHeight(720);

        borderPane.setTop(createToolBarTitle());
        borderPane.setLeft(createToolBarMenu());

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.show();
    }

    public ToolBar createToolBarMenu(){
        Button btDespesa = new Button();
        Button btConta = new Button();
        Button btFornecedor = new Button();

        btDespesa.setGraphic(IconUtil.getIcon(FontAwesome.MONEY));
        btConta.setGraphic(IconUtil.getIcon(FontAwesome.BARCODE));
        btFornecedor.setGraphic(IconUtil.getIcon(FontAwesome.CUBE));

        btDespesa.setMaxWidth(Double.MAX_VALUE);
        btConta.setMaxWidth(Double.MAX_VALUE);
        btFornecedor.setMaxWidth(Double.MAX_VALUE);

        btDespesa.setOnAction(event -> {
            DespesaView despesaView =  new DespesaView();
            borderPane.setCenter(despesaView);
            borderPane.setAlignment(despesaView, Pos.TOP_CENTER);
        });

        final ToolBar toolBar = new ToolBar(btDespesa,btConta,btFornecedor);
        toolBar.setOrientation(Orientation.VERTICAL);
        return toolBar;
    }

    public HBox createToolBarTitle(){
        HBox hbox = new HBox(new Label("VxAssistFX"));
        hbox.setPadding(new Insets(6,3,3,20));
        return hbox;
    }
}
