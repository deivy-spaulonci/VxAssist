package com.br.vxassist.fx.view;

import com.br.vxassist.fx.util.Util;
import com.br.vxassist.fx.view.comp.colunas.*;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

public class DespesaListView extends Stage {
    @Autowired
    TipoDespesaRepository tipoDespesaRepository;
    @Autowired
    FornecedorRepository fornecedorRepository;
    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    DespesaRepository despesaRepository;

    private ObservableList<TipoDespesa> listTipoDespesa = FXCollections.observableArrayList();
    private ObservableList<Fornecedor> listaFornecedor = FXCollections.observableArrayList();
    private ObservableList<FormaPagamento> listaFormaPagamento = FXCollections.observableArrayList();

    private TableView<Despesa> tabela = new TableView<Despesa>();
    private ObservableList<Despesa> data = FXCollections.observableArrayList();
    private FilteredList<Despesa> filteredData;
    private BigDecimal valorTotal = BigDecimal.ZERO;

    // rodape
    private Label total = new Label("0,00");

    private BorderPane mainPane = new BorderPane();

    public DespesaListView(ConfigurableApplicationContext applicationContext){
        applicationContext.getBeanFactory().autowireBean(this);
        applicationContext.getBean(TipoDespesaRepository.class);
        applicationContext.getBean(FornecedorRepository.class);
        applicationContext.getBean(FormaPagamentoRepository.class);
        applicationContext.getBean(DespesaRepository.class);

        this.initComponents();

        this.mainPane.setCenter(this.tabela);
        this.mainPane.setBottom(new HBox(5, this.total));

        Scene scene = new Scene(new BorderPane(this.tabela));
        setScene(scene);
        show();
    }

    public void initComponents(){
        this.tabela.getColumns().addAll(
                new ColunaInteger<Despesa>("Id","id", 60),
                new ColunaTexto<Despesa>("Codigo","codigo", 150d, null),
                new ColunaObjeto<Despesa>("Despesa", "tipoDespesa", 140d, "CENTER-LEFT"),
                new ColunaObjeto<Despesa>("Fornecedor", "fornecedor", 400d, "CENTER-LEFT"),
                new ColunaData<Despesa>("Data","data", 0d),
                new ColunaObjeto<Despesa>("Pagamento", "formaPagamento", 180d, "CENTER-LEFT"),
                new ColunaMoeda<>("Valor", "valor", 90)
        );

        consultarBanco();
        pesquisa();

        this.tabela.getSelectionModel().select(0);

        total.setStyle("-fx-font-size: 14px; -fx-text-fill: darkblue; -fx-font-weight : bold;");
        total.setMinWidth(20);
    }

    public void consultarBanco() {
        data = FXCollections.observableArrayList(this.despesaRepository.findAll());
        filteredData = new FilteredList<>(data, p -> true);
    }

    public void pesquisa(){
        valorTotal = new BigDecimal(0);

        SortedList<Despesa> sortedData = new SortedList<>(
                filteredData.filtered(
                        /*
                        despesa ->  (codigo!=null ? despesa.getCodigo().toLowerCase().contains(codigo.toLowerCase()) : true)
                                && (idTipoDespesa!=null ? despesa.getTipoDespesa().getId().equals(idTipoDespesa) : true)
                                && (idFornecedor!=null ? (despesa.getFornecedor()!=null ? despesa.getFornecedor().getId().equals(idFornecedor) : false ) : true)
                                && (idFormaPagamento!=null ? despesa.getFormaPagamento().getId().equals(idFormaPagamento) : true)
                                && Util.dataEntrePeriodo(despesa.getData(), inicio, fim)

                         */
                        despesa ->  true
                )
        );

        sortedData.forEach(conta ->{
            valorTotal = valorTotal.add(conta.getValor());
            total.setText("R$ " + this.getTotalStrig());
        });

        sortedData.comparatorProperty().bind(this.tabela.comparatorProperty());
        this.tabela.setItems(sortedData);
    }


    public void delDespesa() {
        if (Util.confirmaExclusao("Confirma a exclusão da despesa?",this)) {
            if(this.tabela.getSelectionModel().getSelectedItem().getId()!=null) {
                this.despesaRepository.deleteById(this.tabela.getSelectionModel().getSelectedItem().getId());
                //dao.excluir(this.tabela.getSelectionModel().getSelectedItem().getId());
                //consultarBanco();
                //pesquisa(null, null, null, null, null, null, new ArrayList<String>());
            }else {
                Util.alertErro("Selecione uma despesa para excluir!");
            }
        }
    }

    public Long getIdSelecionado(){
        if(this.tabela.getSelectionModel().getSelectedItem()!=null && this.tabela.getSelectionModel().getSelectedItem().getId()!=null)
            return this.tabela.getSelectionModel().getSelectedItem().getId();
        return null;
    }

    public String getTotalStrig(){
        return Util.nf.format(valorTotal);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /*



private TextField filtroCodigo = new TextField();
	private ComboTipo<TipoDespesa> filtroTipoDespesa = new ComboTipo<TipoDespesa>(new TipoDespesaDao(), new TipoDespesa());
	private CampoFornecedor filtroFornecedor = new CampoFornecedor();
	private ComboTipo<FormaPagamento> filtroFormaPagamento = new ComboTipo<FormaPagamento>(new FormaPagamentoDao(), new FormaPagamento());
	private DatePicker datePickerInicio = new DatePicker();
	private DatePicker datePickerFim = new DatePicker();
	// tabela
	private TabelaDespesa tabela;
	private ContextMenu cm = new ContextMenu();
	//tipo consulta
	private ToggleGroup radioGroupTipoConsulta = new ToggleGroup();
	private RadioButton radioDespesa = new RadioButton("Despesa");
	private RadioButton radioFornecedor = new RadioButton("Fornecedor");
	private RadioButton radioFormaPagamento = new RadioButton("Forma Pagamento");
	private RadioButton radioMeses = new RadioButton("Mês");
	private TableView<LinhaResultado> tabelaResultado = new TableView<LinhaResultado>();

	public ListDespesa() {
		setTitle("Despesa");
		IconFontFX.register(FontAwesome.getIconFont());
		init();
	}

	public void init() {
		datePickerInicio.setPrefWidth(120);
		datePickerInicio.setOnAction(evt -> pesquisaFiltro());

		datePickerFim.setPrefWidth(120);
		datePickerFim.setOnAction(evt -> pesquisaFiltro());

		filtroCodigo.setPromptText("codigo");
		filtroTipoDespesa.setPromptText("tipo despesa");
		datePickerInicio.setPromptText("inicio");
		datePickerFim.setPromptText("término");
		filtroFormaPagamento.setPromptText("forma pagamento");

		GridDefault gridPesquisa = new GridDefault(8,8,5);
		gridPesquisa.addLinha("Código: ", filtroCodigo, 0, 0, 3);
		gridPesquisa.addLinha("Despesa: ", filtroTipoDespesa, 0, 1, 3);
		gridPesquisa.addLinha("Fornecedor: ", filtroFornecedor, 0, 2, 5);
		gridPesquisa.addLinha("Pagamento: ", filtroFormaPagamento, 0, 3, 3);
		gridPesquisa.addLinha("Periodo: ", datePickerInicio, 0, 4, 1);
		gridPesquisa.addLinha("até: ", datePickerFim, 2, 4, 1);
		gridPesquisa.add(Util.btConsultar(null, (ActionEvent)-> {pesquisaFiltro(); carregaResultado();}), 4, 4);
		gridPesquisa.add(Util.btNovo(null, (ActionEvent)-> {showAddDespesa(null);}), 5, 4);


		tabela = new TabelaDespesa(this);
		MenuItem itemEdit = Util.itemEditar((ActionEvent)-> {showAddDespesa(tabela.getIdSelecionado());});
		MenuItem itemNova = Util.itemNovo((ActionEvent)-> {showAddDespesa(null);});
		MenuItem itemExcl = Util.itemExclui((ActionEvent)-> {tabela.delDespesa();});
		cm.getItems().addAll(itemNova, itemEdit, itemExcl);
		tabela.setContextMenu(cm);
		tabela.setOnMousePressed(mouseEvent ->{
			if(mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount()==2)
				showAddDespesa(tabela.getIdSelecionado());
		});
		tabela.setOnKeyPressed(keyEvent ->{
			if(keyEvent.getCode().equals(KeyCode.INSERT))
				showAddDespesa(null);
			else if(keyEvent.getCode().equals(KeyCode.DELETE))
				tabela.delDespesa();
			else if(keyEvent.getCode().equals(KeyCode.ENTER))
				showAddDespesa(tabela.getIdSelecionado());
		});

		Tab tabTabela = new Tab("Consulta", tabela);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PANEL RESULTADOS START
		radioDespesa.setToggleGroup(radioGroupTipoConsulta);
		radioFornecedor.setToggleGroup(radioGroupTipoConsulta);
		radioFormaPagamento.setToggleGroup(radioGroupTipoConsulta);
		radioMeses.setToggleGroup(radioGroupTipoConsulta);

		radioDespesa.setOnAction(action -> carregaResultado());
		radioFornecedor.setOnAction(action -> carregaResultado());
		radioFormaPagamento.setOnAction(action -> carregaResultado());
		radioMeses.setOnAction(action -> carregaResultado());

		radioDespesa.setSelected(true);

		HBox boxTipoConsulta = new HBox(10,radioDespesa,radioFornecedor,radioFormaPagamento,radioMeses);
		TitledPane tipoConsulta = new TitledPane("Tipo de Resultado" , boxTipoConsulta);
		tipoConsulta.setCollapsible(false);

		tabelaResultado.getColumns().addAll(
				new ColunaTexto<LinhaResultado>("Nome","nome", 350d, null),
				new ColunaMoeda<LinhaResultado>("Valor","valor", 200),
				new ColunaPorcentagem<LinhaResultado>("Porcentagem","porcentagem", 200)
		);

		tabelaResultado.getColumns().get(0).setStyle("-fx-text-fill: darkgreen;");

		BorderPane resultadoBox =  new BorderPane();
		resultadoBox.setTop(tipoConsulta);
		resultadoBox.setCenter(tabelaResultado);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PANEL RESULTADOS END

		Tab tabResultado = new Tab("Resultado", resultadoBox);
		tabTabela.setClosable(false);
		tabResultado.setClosable(false);
		TabPane center = new TabPane(tabTabela, tabResultado);

		BorderPane layout = new BorderPane();
		layout.setPrefSize(1000, 700);
		layout.setTop(gridPesquisa);
		layout.setCenter(center);
		layout.setBottom(rodape);
		layout.setPadding(new Insets(5));
		Scene scene = new Scene(layout);

		total.setText("R$ " +tabela.getTotalStrig());

		scene.getStylesheets().add(Main.class.getResource("/application.css").toExternalForm());
		setScene(scene);

		Platform.runLater(() -> tabela.requestFocus());
	}

	public void showAddDespesa(Long id) {
		new FormDespesa(id, this);
		pesquisaFiltro();
	}

	public void carregaResultado() {
		tabelaResultado.getItems().clear();

		if(radioMeses.isSelected()) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
			Map<String, BigDecimal> result = new HashMap<>();
			result = tabela.getItems().stream().collect(Collectors.groupingBy(Despesa::getDataMesAno,Collectors.mapping(Despesa::getValor,Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
			result.entrySet().stream().sorted(Map.Entry.comparingByKey()).sorted((d1,d2) -> {
				try {
					return sdf.parse(d2.getKey()).compareTo(sdf.parse(d1.getKey()));
				} catch (ParseException e) {
					return 0;
				}
			}).forEach(entry -> {
				LinhaResultado linharesultado = new LinhaResultado();
				String mes = " " + String.valueOf(entry.getKey()) + " - " + Util.monthName[Integer.parseInt(entry.getKey().toString().substring(0, 2))-1];
				linharesultado.setNome(mes);
				linharesultado.setValor(new BigDecimal(entry.getValue().toString()));

				BigDecimal subtotal = new BigDecimal(linharesultado.getValor().doubleValue() / tabela.getValorTotal().doubleValue());

				linharesultado.setPorcentagem(subtotal.multiply(new BigDecimal(100)));
				tabelaResultado.getItems().add(linharesultado);
			});

		}else {
			Map<Object, BigDecimal> result = new HashMap<>();
			if(radioFormaPagamento.isSelected()) //POR FORMA PAGAMENTO
				result = tabela.getItems().stream().collect(Collectors.groupingBy(Despesa::getFormaPagamento,Collectors.mapping(Despesa::getValor, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
			else if(radioFornecedor.isSelected()) //POR FORNECEDOR
				result = tabela.getItems().stream().filter(p -> p.getFornecedor() != null).collect(Collectors.groupingBy(Despesa::getFornecedor,Collectors.mapping(Despesa::getValor, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
			else //POR TIPO DE DESPESA
				result = tabela.getItems().stream().collect(Collectors.groupingBy(Despesa::getTipoDespesa,Collectors.mapping(Despesa::getValor, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

			for (Map.Entry<Object, BigDecimal> entry : result.entrySet()){
				LinhaResultado linharesultado = new LinhaResultado();
				linharesultado.setNome(String.valueOf(entry.getKey()));
				linharesultado.setValor(new BigDecimal(entry.getValue().toString()));

				BigDecimal subtotal = new BigDecimal(new BigDecimal(entry.getValue().toString()).doubleValue() / tabela.getValorTotal().doubleValue());

				linharesultado.setPorcentagem(subtotal.multiply(new BigDecimal(100)));
				tabelaResultado.getItems().add(linharesultado);
			}
		}
	}

	public void pesquisaFiltro() {
		List<String> filtros = new ArrayList<String>();
		filtros.add(0,filtroCodigo.getText());
		filtros.add(1,filtroTipoDespesa.nomeSelecionado());
		filtros.add(2, ((filtroFornecedor.getFornecedorSelecionado()!=null && filtroFornecedor.getFornecedorSelecionado().getId()!=null) ? filtroFornecedor.getFornecedorSelecionado().getId().toString() : ""));
		filtros.add(3,filtroFormaPagamento.nomeSelecionado());
		filtros.add(4,(datePickerInicio.getValue()==null ? "" : Util.df.format(Util.localDatetoDate(datePickerInicio.getValue()))));
		filtros.add(5,(datePickerFim.getValue()==null ? "" :Util.df.format(Util.localDatetoDate(datePickerFim.getValue()))));
		tabela.consultarBanco();
		tabela.pesquisa(filtroCodigo.getText(),
				(filtroTipoDespesa.getValue()==null ? null : filtroTipoDespesa.getValue().getId()),
				(filtroFornecedor.getFornecedorSelecionado()==null ? null :  filtroFornecedor.getFornecedorSelecionado().getId()),
				(filtroFormaPagamento.getValue()==null ? null : filtroFormaPagamento.getValue().getId()),
				datePickerInicio.getValue(),
				datePickerFim.getValue(),
				filtros);

		total.setText("R$ " +tabela.getTotalStrig());
	}



	*/
}
