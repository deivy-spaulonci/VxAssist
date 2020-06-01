package com.br.vxassist.fx.view;

import com.br.vxassist.fx.util.Util;
import com.br.vxassist.fx.view.comp.AutoCompleteBox;
import com.br.vxassist.fx.view.comp.GridDefault;
import com.br.vxassist.fx.view.comp.TextFieldMoney;
import com.br.vxassist.model.*;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.repository.FornecedorRepository;
import com.br.vxassist.repository.TipoDespesaRepository;
import com.br.vxassist.repository.TipoInformacaoExtraRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jiconfont.icons.font_awesome.FontAwesome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.DecimalFormat;
import java.util.Locale;

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

    private GridDefault formCadastro;

    public DespesaCadastroView(ConfigurableApplicationContext applicationContext){
        setTitle("Cadastro Despesas");
        applicationContext.getBeanFactory().autowireBean(this);
        applicationContext.getBean(TipoDespesaRepository.class);
        applicationContext.getBean(FornecedorRepository.class);
        applicationContext.getBean(FormaPagamentoRepository.class);
        applicationContext.getBean(TipoInformacaoExtraRepository.class);

        this.initComponents();


//        Label titulo = new Label("Despesa");
//        titulo.setMinHeight(25);
//        titulo.setPadding(new Insets(5,5,5,10));
//        titulo.setStyle("-fx-background-color: #C0C0C0; -fx-font-weight: bold;");
//        titulo.setMaxWidth(Double.MAX_VALUE);
//
//        formCadastro.add(new Label("Tipo: "), 0, 0);
//        formCadastro.add(this.comboTipoDespesa, 1, 0);
//        formCadastro.add(new Label("Fornecedor: "), 0, 1);
//        formCadastro.add(this.comboFornecedor, 1, 1);
//        formCadastro.add(new Label("Data: "), 0, 2);
//        formCadastro.add(this.pickerData, 1, 2);
//        formCadastro.add(new Label("Pagamento: "), 0, 3);
//        formCadastro.add(this.comboFormaPagamento, 1,3);
//        formCadastro.add(new Label("Valor: "), 0,4);
//        formCadastro.add(this.campoValor, 1,4);
//
//        formCadastro.setHgap(8);
//        formCadastro.setVgap(8);
//        formCadastro.setPadding(new Insets(5));
//
//        VBox boxInfoExtra = new VBox(new Label("Informação Extra"));
//        GridPane formInfoExtra = new GridPane();
//        formInfoExtra.add(new Label("N: "), 0,0);
//        formInfoExtra.add(this.campoNumero, 1,0);
//        formInfoExtra.add(new Label(" Tipo: "), 2,0);
//        formInfoExtra.add(this.comboTipoInformacaoExtra, 3,0);
//        formCadastro.setHgap(8);
//        formCadastro.setVgap(8);
//        formCadastro.setPadding(new Insets(5));
//
//        boxInfoExtra.setPadding(new Insets(5));
//        boxInfoExtra.getChildren().add(formInfoExtra);
//
//        HBox linhaFormCadastro = new HBox(formCadastro, boxInfoExtra, new VBox(new Label("Observação: "), this.campoObs));
//        linhaFormCadastro.setStyle("-fx-background-color: #DCDCDC;");

//        Scene scene = new Scene(new VBox(titulo, linhaFormCadastro, this.btSalvar));

        VBox vBox = new VBox(this.formCadastro);
        vBox.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(vBox);
        this.setScene(scene);
        this.setWidth(800);
        this.setHeight(600);
        this.show();

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

//        this.comboTipoInformacaoExtra = new ComboBox<TipoInformacaoExtra>(listaTipoInformacaoExtra);
//
//        this.campoObs.setPrefRowCount(4);
//        this.campoObs.setPrefColumnCount(20);
//
//        this.btSalvar.setGraphic(Util.getIcone(FontAwesome.FLOPPY_O, null, null));
//
//        this.campoValor.setOnKeyTyped(event -> {
//            String typedCharacter = event.getCharacter();
//            event.consume();
//
//            if (typedCharacter.matches("\\d*")) {
//                String currentText = this.campoValor.getText().replaceAll("\\.", "").replace(",", "");
//                long longVal = Long.parseLong(currentText.concat(typedCharacter));
//                this.campoValor.setText(new DecimalFormat("#,##0").format(longVal));
//            }
//        });
//        this.campoValor.setMaxWidth(200);

        formCadastro = new GridDefault(10,10,10);
        this.formCadastro.addLinha("Despesa: ", this.comboTipoDespesa, 0, 0, 1);
        this.formCadastro.addLinha("Fornecedor: ", this.comboFornecedor, 0, 1, 2);
        this.formCadastro.addLinha("Data: ", this.pickerData, 0, 2, 1);
        this.formCadastro.addLinha("Pagamento: ", this.comboFormaPagamento, 0, 3, 1);
        this.formCadastro.addLinha("Valor: ", this.campoValor, 0, 4, 1);
        this.formCadastro.addLinha("Obs: ", this.campoObs, 0, 5, 2);

    }
    /*
    * public class FormDespesa extends Dialog<Void> {
	private Despesa despesaCadastro = new Despesa();
	private DespesaDao dao = new DespesaDao();
	private TextField codigo = new TextField();
	private ComboTipo<TipoDespesa> tipoDespesa = new ComboTipo<TipoDespesa>(new TipoDespesaDao(), null);
	private CampoFornecedor campoFornecedor = new CampoFornecedor();
	private ComboTipo<FormaPagamento> formaPagamento = new ComboTipo<FormaPagamento>(new FormaPagamentoDao(), null);
	private TextField data = new TextField();
	private CampoMoeda valor = new CampoMoeda();
	private TextField obs = new TextField();
	// Informação extra
	private TextField numero = new TextField();
	private ComboTipo<TipoInformacaoExtra> tipoInformacaoExtra = new ComboTipo<TipoInformacaoExtra>(new TipoInformacaoExtraDao(), null);
	private TableView<InformacaoExtra> tabelaInfoExtra = new TableView<InformacaoExtra>();
	private ObservableList<InformacaoExtra> dataInformacaoExtra = FXCollections.observableArrayList();
	private Button addInfoExtra;

	public FormDespesa(Long idDespesa, Window w) {
		setTitle("Cadastro de Despesa");
		initOwner(w);
		IconFontFX.register(FontAwesome.getIconFont());
		init();

		if(idDespesa!=null)
			preencheEditar(idDespesa);//edicao
		else
			despesaCadastro.setId(null);

		showAndWait();
	}

	public void init() {
		MascarasFX.mascaraData(data);

		codigo.setPromptText("código");
		tipoDespesa.setPromptText("tipo despesa");
		data.setPromptText("data");
		formaPagamento.setPromptText("forma pagamento");
		valor.setPromptText("0,00");
		obs.setPromptText("observação");

		GridDefault grid = new GridDefault(10,10,10);
		grid.addLinha("Código: ", codigo, 0, 0, 1);
		grid.addLinha("Despesa: ", tipoDespesa, 0, 1, 1);
		grid.addLinha("Fornecedor: ", campoFornecedor, 0, 2, 2);
		grid.addLinha("Data: ", data, 0, 3, 1);
		grid.addLinha("Pagamento: ", formaPagamento, 0, 4, 1);
		grid.addLinha("Valor: ", valor, 0, 5, 1);
		grid.addLinha("Obs: ", obs, 0, 6, 2);

		TitledPane gridTitlePane = new TitledPane();
		gridTitlePane.setText("Informação Extra");
		gridTitlePane.setCollapsible(false);
		gridTitlePane.setPadding(new Insets(10));

		tabelaInfoExtra.getColumns().addAll(
			new ColunaTexto<InformacaoExtra>("Número", "numero", 200d, null),
			new ColunaObjeto<InformacaoExtra>("Tipo", "tipoInformacaoExtra", 300d, "CENTER-LEFT")
		);
		tabelaInfoExtra.setMaxHeight(150);
		tabelaInfoExtra.setFocusTraversable(false);

		addInfoExtra = Util.btDefault("", FontAwesome.PLUS_SQUARE, 16d, (ActionEvent) -> {
			addInformacaoExtra();
		});

		numero.setPromptText("número");

		GridDefault gridInfo = new GridDefault(10,10,10);
		gridInfo.addLinha("Número: ", numero, 0, 0, 1);
		gridInfo.addLinha("Tipo Informação extra: ", tipoInformacaoExtra, 2, 0, 1);
		gridInfo.add(addInfoExtra, 4, 0);
		gridInfo.add(tabelaInfoExtra, 0, 1, 5, 1);
		gridTitlePane.setContent(gridInfo);

		Window window = getDialogPane().getScene().getWindow();
		window.setOnCloseRequest(event -> window.hide());

		HBox hbox = new HBox(
			Util.btSalvar(null, (ActionEvent) -> {salvar();}),
			Util.btCancel(null, (ActionEvent) -> { window.hide();})
		);
		hbox.setSpacing(50);
		hbox.setPadding(new Insets(5));
		hbox.setAlignment(Pos.CENTER);

		VBox form = new VBox(grid, gridTitlePane);
		form.setPadding(new Insets(0));

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

		Platform.runLater(() -> codigo.requestFocus());
	}

	public void addInformacaoExtra() {
		if (Util.inputTextNullOrEmpty(numero)) {
			Util.alertErro("campo número vázio!");
		} else {
			InformacaoExtra informacaoExtra = new InformacaoExtra();
			informacaoExtra.setNumero(numero.getText());
			informacaoExtra.setTipoInformacaoExtra(tipoInformacaoExtra.getValue());
			informacaoExtra.setDespesa(despesaCadastro);
			dataInformacaoExtra.add(informacaoExtra);
			tabelaInfoExtra.setItems(dataInformacaoExtra);

			if(despesaCadastro.getInformacaoExtra()==null || despesaCadastro.getInformacaoExtra().isEmpty()){
				despesaCadastro.setInformacaoExtra(new ArrayList<InformacaoExtra>());
			}
			despesaCadastro.getInformacaoExtra().add(informacaoExtra);

			tipoInformacaoExtra.getItems().remove(tipoInformacaoExtra.getValue());
			numero.setText("");
			Platform.runLater(() -> numero.requestFocus());
		}
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
