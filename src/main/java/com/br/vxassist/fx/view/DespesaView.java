package com.br.vxassist.fx.view;

import com.br.vxassist.fx.view.comp.AutoCompleteBox;
import com.br.vxassist.fx.view.comp.TextFieldMoney;
import com.br.vxassist.fx.util.iconutil.IconUtil;
import com.br.vxassist.model.*;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.repository.FornecedorRepository;
import com.br.vxassist.repository.TipoDespesaRepository;
import com.br.vxassist.repository.TipoInformacaoExtraRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jiconfont.icons.font_awesome.FontAwesome;
import org.graalvm.compiler.phases.graph.ScheduledNodeIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.DecimalFormat;
import java.util.Locale;

public class DespesaView extends Stage {
    @Autowired
    TipoDespesaRepository tipoDespesaRepository;
    @Autowired
    FornecedorRepository fornecedorRepository;
    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    TipoInformacaoExtraRepository tipoInformacaoExtraRepository;

    ObservableList<TipoDespesa> listTipoDespesa = FXCollections.observableArrayList();
    ObservableList<Fornecedor> listaFornecedor = FXCollections.observableArrayList();
    ObservableList<FormaPagamento> listaFormaPagamento = FXCollections.observableArrayList();
    ObservableList<TipoInformacaoExtra> listaTipoInformacaoExtra = FXCollections.observableArrayList();

    private TableView<InformacaoExtra> tabelaInfoExtra = new TableView<InformacaoExtra>();
    private ObservableList<InformacaoExtra> dataInformacaoExtra = FXCollections.observableArrayList();

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
        applicationContext.getBean(FormaPagamentoRepository.class);
        applicationContext.getBean(TipoInformacaoExtraRepository.class);

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

        formCadastro.setHgap(8);
        formCadastro.setVgap(8);
        formCadastro.setPadding(new Insets(5));

        VBox boxInfoExtra = new VBox(new Label("Informação Extra"));
        GridPane formInfoExtra = new GridPane();
        formInfoExtra.add(new Label("N: "), 0,0);
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

        Scene scene = new Scene(new VBox(titulo, linhaFormCadastro, this.btSalvar));
        setScene(scene);
        show();

    }

    public void initComponents(){

        this.listTipoDespesa = FXCollections.observableList(tipoDespesaRepository.findAll());
        this.listaFornecedor = FXCollections.observableList(fornecedorRepository.findAll());
        this.listaFormaPagamento = FXCollections.observableList(formaPagamentoRepository.findAll());
        this.listaTipoInformacaoExtra = FXCollections.observableList(tipoInformacaoExtraRepository.findAll());

        this.comboTipoDespesa = new ComboBox<TipoDespesa>(listTipoDespesa);

        this.comboFornecedor = new ComboBox<Fornecedor>(listaFornecedor);
        new AutoCompleteBox(this.comboFornecedor);
        this.comboFornecedor.setMaxWidth(400);
        this.comboFormaPagamento = new ComboBox<FormaPagamento>(listaFormaPagamento);
        this.comboTipoInformacaoExtra = new ComboBox<TipoInformacaoExtra>(listaTipoInformacaoExtra);

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
        this.campoValor.setMaxWidth(200);
    }
}
