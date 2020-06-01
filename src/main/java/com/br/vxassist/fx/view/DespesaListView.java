package com.br.vxassist.fx.view;

import com.br.vxassist.fx.service.DespesaService;
import com.br.vxassist.fx.util.Util;
import com.br.vxassist.fx.view.comp.AutoCompleteComboBoxListener;
import com.br.vxassist.fx.view.comp.colunas.ColunaData;
import com.br.vxassist.fx.view.comp.colunas.ColunaInteger;
import com.br.vxassist.fx.view.comp.colunas.ColunaMoeda;
import com.br.vxassist.fx.view.comp.colunas.ColunaObjeto;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.repository.FornecedorRepository;
import com.br.vxassist.repository.TipoDespesaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
    private DespesaService despesaService;

    private ConfigurableApplicationContext applicationContext;

    private ObservableList<TipoDespesa> listTipoDespesa = FXCollections.observableArrayList();
    private ObservableList<Fornecedor> listaFornecedor = FXCollections.observableArrayList();
    private ObservableList<FormaPagamento> listaFormaPagamento = FXCollections.observableArrayList();

    private ComboBox<TipoDespesa> filtroTipoDespesa = new ComboBox<TipoDespesa>();
    private ComboBox<Fornecedor> filtroFornecedor = new ComboBox<Fornecedor>();
    private ComboBox<FormaPagamento> filtroFormaPagamento = new ComboBox<FormaPagamento>();
    private DatePicker datePickerInicio = new DatePicker();
    private DatePicker datePickerFim = new DatePicker();
    private HBox gridPesquisa;

    private ContextMenu cm = new ContextMenu();
    private TableView<Despesa> tabela = new TableView<Despesa>();
    private ObservableList<Despesa> data = FXCollections.observableArrayList();
    private FilteredList<Despesa> filteredData;
    private BigDecimal valorTotal = BigDecimal.ZERO;

    // rodape
    private Text total = new Text("0,00");

    private BorderPane mainPane = new BorderPane();

    public DespesaListView(ConfigurableApplicationContext applicationContext){
        setTitle("Despesas");
        applicationContext.getBeanFactory().autowireBean(this);
        applicationContext.getBean(TipoDespesaRepository.class);
        applicationContext.getBean(FornecedorRepository.class);
        applicationContext.getBean(FormaPagamentoRepository.class);
        applicationContext.getBean(DespesaService.class);
        this.applicationContext = applicationContext;

        this.initComponents();

        this.mainPane.setTop(this.gridPesquisa);
        this.mainPane.setCenter(this.tabela);
        this.mainPane.setBottom(this.total);

        Scene scene = new Scene(this.mainPane);
        setScene(scene);
        show();
    }

    public void initComponents(){
        //FILTROS
        this.listTipoDespesa = FXCollections.observableList(tipoDespesaRepository.findAll());
        this.filtroTipoDespesa = new ComboBox<TipoDespesa>(listTipoDespesa);
        this.filtroTipoDespesa.setPromptText("Tipo Despesa");
        this.filtroTipoDespesa.setOnAction(evt -> pesquisa());

        this.listaFornecedor = FXCollections.observableList(fornecedorRepository.findAll());
        this.filtroFornecedor = new ComboBox<Fornecedor>(listaFornecedor);
        new AutoCompleteComboBoxListener(this.filtroFornecedor);
        this.filtroFornecedor.setMaxWidth(400);
        this.filtroFornecedor.setOnAction(evt-> pesquisa());

        this.listaFormaPagamento = FXCollections.observableList(formaPagamentoRepository.findAll());
        this.filtroFormaPagamento = new ComboBox<FormaPagamento>(listaFormaPagamento);
        this.filtroFormaPagamento.setPromptText("Forma Pagamento");
        this.filtroFormaPagamento.setOnAction(evt -> pesquisa());

        this.datePickerInicio.setPromptText("inicio");
        this.datePickerInicio.setPrefWidth(120);
        this.datePickerInicio.setOnAction(evt -> pesquisa());

        this.datePickerFim.setPromptText("término");
        this.datePickerFim.setPrefWidth(120);
        this.datePickerFim.setOnAction(evt -> pesquisa());

        this.gridPesquisa = new HBox(filtroTipoDespesa,
                filtroFornecedor,
                datePickerInicio,
                datePickerFim,
                filtroFormaPagamento,
                Util.btNovo(null, (ActionEvent)-> { showAddDespesa(null); }));
        this.gridPesquisa.setSpacing(5);
        this.gridPesquisa.setPadding(new Insets(3));

        //TABELA
        MenuItem itemEdit = Util.itemEditar((ActionEvent)-> {/*showAddDespesa(tabela.getIdSelecionado());*/});
        MenuItem itemNova = Util.itemNovo((ActionEvent)-> {/*showAddDespesa(null);*/});
        MenuItem itemExcl = Util.itemExclui((ActionEvent)-> {/*tabela.delDespesa();*/});
        this.cm.getItems().addAll(itemNova, itemEdit, itemExcl);
        this.tabela.setContextMenu(cm);

        this.tabela.getColumns().addAll(
                new ColunaInteger<Despesa>("Id","id", 60),
                new ColunaObjeto<Despesa>("Despesa", "tipoDespesa", 140d, "CENTER-LEFT"),
                new ColunaObjeto<Despesa>("Fornecedor", "fornecedor", 400d, "CENTER-LEFT"),
                new ColunaData<Despesa>("Data","data", 0d),
                new ColunaObjeto<Despesa>("Pagamento", "formaPagamento", 180d, "CENTER-LEFT"),
                new ColunaMoeda<>("Valor", "valor", 90)
        );

        this.pesquisa();

        this.tabela.getSelectionModel().select(0);

        //TOTAL
        this.total.setStyle("-fx-font-size: 14px; -fx-font-weight : bold;");
    }

    public void pesquisa(){
        data = FXCollections.observableArrayList(
                this.despesaService.listaDespesas(
                        this.filtroTipoDespesa.getValue(),
                        this.filtroFornecedor.getValue(),
                        Util.localDatetoDate(this.datePickerInicio.getValue()),
                        Util.localDatetoDate(this.datePickerFim.getValue()),
                        this.filtroFormaPagamento.getValue()
                )
        );
        valorTotal = new BigDecimal(0);

        SortedList<Despesa> sortedData = new SortedList<>(data);

        sortedData.forEach(conta ->{
            valorTotal = valorTotal.add(conta.getValor());
            total.setText("R$ " + this.getTotalStrig());
        });

        sortedData.comparatorProperty().bind(this.tabela.comparatorProperty());
        this.tabela.setItems(sortedData);
    }

    public void showAddDespesa(Long id) {
        DespesaCadastroView despesaCadastroView =  new DespesaCadastroView(this.applicationContext);
    }

    public void delDespesa() {
        if (Util.confirmaExclusao("Confirma a exclusão da despesa?",this)) {
            if(this.tabela.getSelectionModel().getSelectedItem().getId()!=null) {
                //this.despesaRepository.deleteById(this.tabela.getSelectionModel().getSelectedItem().getId());
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
	// tabela
	private TabelaDespesa tabela;

	//tipo consulta
	private ToggleGroup radioGroupTipoConsulta = new ToggleGroup();
	private RadioButton radioDespesa = new RadioButton("Despesa");
	private RadioButton radioFornecedor = new RadioButton("Fornecedor");
	private RadioButton radioFormaPagamento = new RadioButton("Forma Pagamento");
	private RadioButton radioMeses = new RadioButton("Mês");
	private TableView<LinhaResultado> tabelaResultado = new TableView<LinhaResultado>();


	public void init() {


		tabela = new TabelaDespesa(this);
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
