package com.br.vxassist.fx.view;

import com.br.vxassist.fx.view.comp.AutoCompleteBox;
import com.br.vxassist.fx.view.comp.TextFieldMoney;
import com.br.vxassist.fx.view.iconutil.IconUtil;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.model.TipoInformacaoExtra;
import com.br.vxassist.repository.FornecedorRepository;
import com.br.vxassist.repository.TipoDespesaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jiconfont.icons.font_awesome.FontAwesome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DespesaView extends BorderPane {
    @Autowired
    TipoDespesaRepository tipoDespesaRepository;
    @Autowired
    FornecedorRepository fornecedorRepository;

    private ComboBox<TipoDespesa> comboTipoDespesa = new ComboBox<>();
    private ComboBox<Fornecedor> comboFornecedor = new ComboBox<>();
    private ComboBox<FormaPagamento> comboFormaPagamento = new ComboBox<>();
    private ComboBox<TipoInformacaoExtra> comboTipoInformacaoExtra = new ComboBox<>();
    private DatePicker pickerData = new DatePicker();
    private TextFieldMoney campoValor = new TextFieldMoney(new Locale("pt","BR"));
    private TextField campoNumero = new TextField();
    private TextArea campoObs = new TextArea();
    private Button btSalvar = new Button("Salvar");

    public DespesaView(ConfigurableApplicationContext applicationContext){
        applicationContext.getBeanFactory().autowireBean(this);
        applicationContext.getBean(TipoDespesaRepository.class);
        applicationContext.getBean(FornecedorRepository.class);


        this.initComponents();

        GridPane formCadastro = new GridPane();

        Label titulo = new Label("Despesa");
        titulo.setMinHeight(25);
        titulo.setPadding(new Insets(5,5,5,10));
        titulo.setStyle("-fx-background-color: #C0C0C0; -fx-font-weight: bold;");
        titulo.setMaxWidth(Double.MAX_VALUE);

        formCadastro.add(new Label("Tipo: "), 0, 0);
        formCadastro.add(this.comboTipoDespesa, 1, 0);
        formCadastro.add(new Label("Fornecedor: "), 0, 1);
        formCadastro.add(this.comboFornecedor, 1, 1);
        formCadastro.add(new Label("Data: "), 0, 2);
        formCadastro.add(this.pickerData, 1, 2);
        formCadastro.add(new Label("Pagamento: "), 0, 3);
        formCadastro.add(this.comboFormaPagamento, 1,3);
        formCadastro.add(new Label("Valor: "), 0,4);
        formCadastro.add(this.campoValor, 1,4);
//        formCadastro.add(new Label("Observação"), 2,0);
//        formCadastro.add(this.campoObs, 2, 1);

        formCadastro.setHgap(8);
        formCadastro.setVgap(8);
        formCadastro.setPadding(new Insets(5));

        VBox boxInfoExtra = new VBox(new Label("Informação Extra"));
        GridPane formInfoExtra = new GridPane();
        formInfoExtra.add(new Label("Número: "), 0,0);
        formInfoExtra.add(this.campoNumero, 1,0);
        formInfoExtra.add(new Label(" Tipo: "), 2,0);
        formInfoExtra.add(this.comboTipoInformacaoExtra, 3,0);
        formCadastro.setHgap(8);
        formCadastro.setVgap(8);
        formCadastro.setPadding(new Insets(5));

        boxInfoExtra.setPadding(new Insets(5));
        boxInfoExtra.getChildren().add(formInfoExtra);

        HBox linhaFormCadastro = new HBox(formCadastro, boxInfoExtra, new VBox(new Label("Observação: "), this.campoObs));
        linhaFormCadastro.setStyle("-fx-background-color: #DCDCDC;");

        this.setTop(new VBox(titulo, linhaFormCadastro, this.btSalvar));

    }

    public void initComponents(){

        ObservableList<TipoDespesa> listTipoDespesa = FXCollections.observableList(tipoDespesaRepository.findAll());
        ObservableList<Fornecedor> listaFornecedor = FXCollections.observableList(fornecedorRepository.findAll());

        this.comboTipoDespesa = new ComboBox<TipoDespesa>(listTipoDespesa);
        this.comboFornecedor = new ComboBox<Fornecedor>(listaFornecedor);
        new AutoCompleteBox(this.comboFornecedor);


        this.campoObs.setPrefRowCount(4);
        this.campoObs.setPrefColumnCount(20);

        this.btSalvar.setGraphic(IconUtil.getIcon(FontAwesome.FLOPPY_O));

        this.campoValor.setOnKeyTyped(event -> {
            String typedCharacter = event.getCharacter();
            event.consume();

            if (typedCharacter.matches("\\d*")) {
                String currentText = this.campoValor.getText().replaceAll("\\.", "").replace(",", "");
                long longVal = Long.parseLong(currentText.concat(typedCharacter));
                this.campoValor.setText(new DecimalFormat("#,##0").format(longVal));
            }
        });
    }
}
