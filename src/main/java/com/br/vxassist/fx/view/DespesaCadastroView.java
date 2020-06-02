package com.br.vxassist.fx.view;

import com.br.vxassist.fx.util.MascarasFX;
import com.br.vxassist.fx.util.Util;
import com.br.vxassist.fx.view.comp.AutoCompleteBox;
import com.br.vxassist.fx.view.comp.GridDefault;
import com.br.vxassist.fx.view.comp.TextFieldMoney;
import com.br.vxassist.fx.view.comp.colunas.ColunaObjeto;
import com.br.vxassist.fx.view.comp.colunas.ColunaTexto;
import com.br.vxassist.model.*;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.repository.FornecedorRepository;
import com.br.vxassist.repository.TipoDespesaRepository;
import com.br.vxassist.repository.TipoInformacaoExtraRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jiconfont.icons.font_awesome.FontAwesome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class DespesaCadastroView extends Stage {
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

    private Despesa despesaCadastro = new Despesa();

    private ComboBox<TipoDespesa> comboTipoDespesa = new ComboBox<>();
    private ComboBox<Fornecedor> comboFornecedor = new ComboBox<>();
    private ComboBox<FormaPagamento> comboFormaPagamento = new ComboBox<>();
    private DatePicker pickerData = new DatePicker();
    private TextFieldMoney campoValor = new TextFieldMoney(new Locale("pt","BR"));
    private TextArea campoObs = new TextArea();

    private GridDefault formCadastro;

    // Informação extra
    private TextField campoNumero = new TextField();
    private ComboBox<TipoInformacaoExtra> comboTipoInformacaoExtra = new ComboBox<TipoInformacaoExtra>();
    private TableView<InformacaoExtra> tabelaInfoExtra = new TableView<InformacaoExtra>();
    private ObservableList<InformacaoExtra> dataInformacaoExtra = FXCollections.observableArrayList();
    private Button addInfoExtra;

    private TitledPane gridTitlePane = new TitledPane();

    //footer
    private HBox footer = new HBox();

    public DespesaCadastroView(ConfigurableApplicationContext applicationContext){
        setTitle("Cadastro Despesas");
        applicationContext.getBeanFactory().autowireBean(this);
        applicationContext.getBean(TipoDespesaRepository.class);
        applicationContext.getBean(FornecedorRepository.class);
        applicationContext.getBean(FormaPagamentoRepository.class);
        applicationContext.getBean(TipoInformacaoExtraRepository.class);

        this.initComponents();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(this.formCadastro);
        borderPane.setCenter(gridTitlePane);
        borderPane.setBottom(footer);

        Scene scene = new Scene(borderPane);
        this.setScene(scene);
        this.setWidth(700);
        this.setHeight(600);
        this.show();

        Platform.runLater(() -> this.comboTipoDespesa.requestFocus());
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
        this.campoObs.setPrefRowCount(2);
        this.campoObs.setPrefColumnCount(45);

        MascarasFX.mascaraData(this.pickerData);

        this.comboTipoDespesa.setPromptText("Tipo Despesa");
        this.comboFornecedor.setPromptText("Fornecedor");
        this.pickerData.setPromptText("data");
        this.comboFormaPagamento.setPromptText("Forma Pagamento");
        this.campoValor.setPromptText("0,00");
        this.campoObs.setPromptText("Observação");

        formCadastro = new GridDefault(10,10,10);
        this.formCadastro.addLinha("Despesa: ", this.comboTipoDespesa, 0, 0, 1);
        this.formCadastro.addLinha("Fornecedor: ", this.comboFornecedor, 0, 1, 2);
        this.formCadastro.addLinha("Data: ", this.pickerData, 0, 2, 1);
        this.formCadastro.addLinha("Pagamento: ", this.comboFormaPagamento, 0, 3, 1);
        this.formCadastro.addLinha("Valor: ", this.campoValor, 0, 4, 1);
        this.formCadastro.addLinha("Obs: ", this.campoObs, 0, 5, 2);

        //informacao extra
        this.comboTipoInformacaoExtra = new ComboBox<TipoInformacaoExtra>(this.listaTipoInformacaoExtra);
        addInfoExtra = Util.btDefault("", FontAwesome.PLUS_SQUARE, 16d, (ActionEvent) -> {
            addInformacaoExtra();
        });
        tabelaInfoExtra.getColumns().addAll(
                new ColunaTexto<InformacaoExtra>("Número", "numero", 200d, null),
                new ColunaObjeto<InformacaoExtra>("Tipo", "tipoInformacaoExtra", 300d, "CENTER-LEFT")
        );
        tabelaInfoExtra.setMaxHeight(150);
        tabelaInfoExtra.setFocusTraversable(false);
        campoNumero.setPromptText("número");
        new AutoCompleteBox(this.comboTipoInformacaoExtra);

        gridTitlePane.setText("Informação Extra");
        gridTitlePane.setCollapsible(false);
        gridTitlePane.setPadding(new Insets(10));

        GridDefault gridInfo = new GridDefault(10,10,10);
        gridInfo.addLinha("Número: ", campoNumero, 0, 0, 1);
        gridInfo.addLinha("Tipo Informação extra: ", this.comboTipoInformacaoExtra, 2, 0, 1);
        gridInfo.add(addInfoExtra, 4, 0);
        gridInfo.add(tabelaInfoExtra, 0, 1, 5, 1);
        this.gridTitlePane.setContent(gridInfo);

        //rodape
        Button btSalvar = Util.btSalvar(220D, (ActionEvent) -> { this.salvar();});
        Button btCancel = Util.btCancel(220D, (ActionEvent) -> { this.close();});
        btSalvar.setPrefSize(250, 40);
        btCancel.setPrefSize(250, 40);
        this.footer = new HBox(btSalvar,btCancel);
        this.footer.setPadding(new Insets(5));
        this.footer.setSpacing(10);
        this.footer.setAlignment(Pos.CENTER);
    }

    public void salvar(){

    }

    public void addInformacaoExtra(){
        if (Util.inputTextNullOrEmpty(campoNumero)) {
            Util.alertErro("campo número vázio!");
        } else {
            InformacaoExtra informacaoExtra = new InformacaoExtra();
            informacaoExtra.setNumero(campoNumero.getText());
            informacaoExtra.setTipoInformacaoExtra(this.comboTipoInformacaoExtra.getValue());
            this.dataInformacaoExtra.add(informacaoExtra);
            this.tabelaInfoExtra.setItems(dataInformacaoExtra);

            if(Objects.isNull(this.despesaCadastro.getInformacaoExtra()) || despesaCadastro.getInformacaoExtra().isEmpty()){
                despesaCadastro.setInformacaoExtra(new ArrayList<InformacaoExtra>());
            }
            despesaCadastro.getInformacaoExtra().add(informacaoExtra);

            this.campoNumero.setText("");
            Platform.runLater(() -> campoNumero.requestFocus());
        }
    }
    /*

	public void init() {




		Window window = getDialogPane().getScene().getWindow();
		window.setOnCloseRequest(event -> window.hide());



		getDialogPane().setHeader(form);
		getDialogPane().setContent(hbox);
		getDialogPane().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.isControlDown() && (event.getCode()==KeyCode.S)) {
					salvar();
				}
			}
		});


	}

	public void preencheEditar(Long id){
		despesaCadastro = dao.find(id);
		codigo.setText(despesaCadastro.getCodigo());
		tipoDespesa.setValue(despesaCadastro.getTipoDespesa());
		if(despesaCadastro.getFornecedor()!=null)
			campoFornecedor.setFornecedor(despesaCadastro.getFornecedor().getId());
        data.setText(Util.df.format(despesaCadastro.getData()));
        formaPagamento.setValue(despesaCadastro.getFormaPagamento());
        valor.setText(Util.nf.format(despesaCadastro.getValor()));
        obs.setText(despesaCadastro.getObs());
        for(InformacaoExtra ie : despesaCadastro.getInformacaoExtra())
            dataInformacaoExtra.add(ie);

        tabelaInfoExtra.setItems(dataInformacaoExtra);
	}

	public void salvar() {
		ValidationFields.checkEmptyFields(codigo, data, valor);

		if(Util.inputTextNullOrEmpty(codigo)) {
			Util.alertErro("campo código inválido!");
		}else if(Util.moedaStringValida(valor.getText())) {
			Util.alertErro("campo valor inválido!");
		}else if(campoFornecedor.getFornecedorSelecionado()==null) {
			Util.alertErro("campo fornecedor inválido!");
		}else{
			despesaCadastro.setFornecedor(campoFornecedor.getFornecedorSelecionado());
			if(despesaCadastro.getFornecedor().getId()==null){
				Util.alertErro("campo fornecedor inválido!");
			}
			Date inputData = Util.dataStringBRToDateUS(data.getText());
			if(data==null){
				Util.alertErro("campo data inválida!");
			}else{
				despesaCadastro.setCodigo(codigo.getText());
				despesaCadastro.setTipoDespesa(tipoDespesa.getValue());
				despesaCadastro.setData(inputData);
				despesaCadastro.setFormaPagamento(formaPagamento.getValue());
				despesaCadastro.setValor(valor.getValorBigDecimal());
				despesaCadastro.setObs(obs.getText());

				if(despesaCadastro.getId()==null) {
					dao.save(despesaCadastro);
					data.setText("");
					codigo.setText("");
					valor.setText("0,00");
					obs.setText("");
					Fornecedor fornecedor = despesaCadastro.getFornecedor();
					despesaCadastro = new Despesa();
					despesaCadastro.setFornecedor(fornecedor);

					dataInformacaoExtra.clear();
					tabelaInfoExtra.getItems().clear();

					tipoInformacaoExtra.getItems().clear();
					tipoInformacaoExtra.getItems().addAll(new TipoInformacaoExtraDao().findAllTipo());
					tipoInformacaoExtra.getSelectionModel().select(0);

					Util.alertInfoSucesso("Sucesso", "Despesa salva!");
					Platform.runLater(() -> codigo.requestFocus());
				}else {
					dao.update(despesaCadastro);
					Util.alertInfoSucesso("Sucesso", "Despesa atualizada!");
					getDialogPane().getScene().getWindow().hide();
				}
			}
		}
	}
}
    * */
}
