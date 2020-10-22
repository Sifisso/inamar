package mz.ciuem.inamar.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.impl.LogServiceImpl;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MainVM extends PagVM {

	private static final long serialVersionUID = 1L;

	@Wire("#mainlayout")
	private Div target;

	@Wire("#breadcrumb")
	private Ol ol;

	@WireVariable
	private LogServiceImpl logService;

	@Wire
	private Image imgPfl;

	@Wire("#sidebar #imgPflSide")
	private Image imgPflSide;

	// SideBar Menus

	private String initPage;

	private String hoursPage;

	private String recruitPage;

	private String perfomPage;

	private String leavePage;

	private String trainPage;

	private String recPage;

	private String aproovPage;

	private String morePage;
	
	private String delegacao;
	
	
	@WireVariable
	private InstituicaoService _instituicaoService;
	
	private Instituicao instituicao;

/*	@WireVariable
	private AgenteService agenteService;

	private Ciclo ciclo;
	
	@WireVariable
	private CicloService cicloService;
	
	private Agente agente;*/

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		final HashMap<String, Object> map = new HashMap<String, Object>();

		Selectors.wireComponents(view, this, false);

		target.getChildren().clear();

		map.put("target", target);
		map.put("breadcrumb", ol);
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
	

		if (target != null) {

			imgPflSide.invalidate();
		}

	}

	@Init
	public void init() {
		menuReset();
		setInitPage("active");
		loggeduser = userService.getUser(authentication.getName());
		Executions.getCurrent().getDesktop().getSession()
				.setAttribute("ss_utilizador", loggeduser);
		/*ciclo = new Ciclo();
		agente = new Agente();*/
		delegacao=loggeduser.getFuncionario().getSector().getDelegacaoDepartamento().getDelegacao().getNome();
		
	Messagebox.show(loggeduser.getFuncionario().getNome()+"-"+delegacao);

	}

	@Command
	public void home() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);

		menuReset();
		setInitPage("active");
		
	}
	
	
	@Command
	public void homeCadidato() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("dashboardCandidato.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);

		menuReset();
		setInitPage("active");
		
	}
	
	
	
	@Command
	public void brevemente() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("brevemente.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);

		menuReset();
		setInitPage("active");
	}
	
	@Command
	public void estadoPreIncricao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("dashboardCandidato.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);

		menuReset();
		setInitPage("active");
	}

	@Command
	public void sideBarMore() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/more.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void anexarDocumentos() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/upload_docs.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarEstudante() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions
				.createComponents("views/funcionario/create.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void universidade() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_universidade.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void unidadeOrganica() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_unidade_organica.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarCurso() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_curso.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarDisciplina() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_disciplina.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarAgente() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_agente.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarInstituicao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_instituicao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	

	@Command
	public void registarPapel() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_papel.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarConta() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_conta.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarCategoria() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_categoria.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarTipoRquisito() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_TipoRequisito.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarArea() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_area.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarSubArea() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_subArea.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void registarTarefa() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_tarefa.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarEtapa() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_etapa.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarFluxo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_fluxo.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarEstado() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_estado.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarInsLegal() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_insLegal.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarClasseEmbarcacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_classeEmbarcacao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarTipoCombustivel() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_TipoCombustivel.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void registarZonaActividadeM() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_zonaActividadeM.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarServicoDestino() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_servicoDestino.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarAparelhoGoverno() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_aparelhoGoverno.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarMeioEsgoto() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_meioEsgoto.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarGrupoMaritimo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_GrupoMaritimo.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarLocaisPratica() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_LocalPratica.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarRota() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_rota.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarEquipamento() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_equipamento.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarTipoEmbarcacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_tipoEmbarcacao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarMeioTransporte() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_meioTransporte.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void registarContagem() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_contagem.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void registarClasseMaritimo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_classeMaritimo.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarIntituicao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_intituicao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarFuncionario() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_agente.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void totalFuncionarios() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/total_funcionarios.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void totalMaritimos() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/total_maritimos.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void totalPeticoes() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/total_peticoes.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void totalNavios() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/total_navios.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarFuncionarioEstatistica() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/funcionario_estatisticas.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void registarProvincia() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_provincia.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarDistrito() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_distrito.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarDepartamento() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_departamento.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarPreUniversitaria() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_preuniversitarias.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarDelegacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_delegacao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarSector() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_sector.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarUtilizador() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Administracao/registar_utilizador.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void totalPreRegistados() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Reporter/total_preRegistados.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void totalValidados() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Reporter/total_validados.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void exportarDados() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Reporter/exportarDados.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void desempenhoAgentes() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Reporter/desempenhoAgentes.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void pagamentosEfectuados() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Reporter/pagamentosEfectuados.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void permissoes() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Administracao/permissoes.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void perfisUtilizadores() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Administracao/perfis.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarCiclo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Ciclo/registar_ciclo.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarExpediente() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/registar_pedido.zul", target,map);
		Executions.getCurrent().getSession().removeAttribute("ss_utente");
		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void pedidoEmbarcacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/Embarcacao/pedido_embarcacao.zul", target,map);
		Executions.getCurrent().getSession().removeAttribute("ss_utente");
		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	@Command
	public void pedidoMaritimos() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/Maritimo/pedido_maritimos.zul", target,map);
		Executions.getCurrent().getSession().removeAttribute("ss_utente");
		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void registarExpedienteChefeSecretaria() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/ChefeSecretaria/registar_pedido.zul", target,map);
		Executions.getCurrent().getSession().removeAttribute("ss_utente");
		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void expedienteTesouraria() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/registar_pedido.zul", target,map);
		Executions.getCurrent().getSession().removeAttribute("ss_utente");
		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarExpedienteUtente() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/peticoes_utentes.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarExpedienteTesouraria() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Tesouraria/registar_pedido.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarExpedienteAdminMaritimo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/AdministradorMaritimo/registar_pedido.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarExpedienteSeccaoTecnica() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/SeccaoTecnica/registar_pedido.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void relatorioGestor() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Relatorios/Gestor/relatorio_gestor.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarUtente() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/gerir_utentes.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarMaritimo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/gerir_maritimo.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void gerirMaritimoMaritimo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/gerir_maritimo_maritimo.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void editarUtente() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/escolher_tipo_utente.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
		
		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void adicionarMaritimo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/expediente/registar_maritimo.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	
	@Command
	public void temp() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Maritimo/emissaoCedulaMaritima.zul", target,map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	/*@Command
	public void configurarCiclo(@BindingParam("_cicloId") Long _cicloId) {

		ciclo = cicloService.find(_cicloId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_ciclo", ciclo);
		target.getChildren().clear();
		Executions.createComponents("views/Ciclo/configurar_ciclo.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}*/
	
	@Command
	public void configurarInstituicao(@BindingParam("_instId") Long _instId) {
		
		instituicao = (Instituicao) _instituicaoService.find((long) 2);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_intituicao", instituicao);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_delegacao.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	@NotifyChange()
	public void criarUtilizador(@BindingParam("_agenteID") Long _agenteID) {

		//agente = agenteService.find(_agenteID);
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		//map.put("agente", agente);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Administracao/registar_utilizador.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void pesquisarEstudante() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/DadosBasicos.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void calendario() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/Calendario.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void dadosBasicos() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/DadosBasicos.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void dadosBasicos2() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/dadosBasicos2.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void loginEstudante() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/login.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void submeterDoxs() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/anexarDocx.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void escolherCurso() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/escolherCurso.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void dadosFactura() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/dadosFactura.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void calendarioExame() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/calendarioExame.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void validarDados() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/validacao-pre-registo/validar_dados_pessoais.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void importarTransacoes() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/validacao-pre-registo/importar_transacoes.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarEmbarcacaoImportada() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/registarEmbarcacaoImportada.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void embarcacaoImportada() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/embarcacaoImportada.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void construcaoDeEmbarcacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/construcaoDeEmbarcacao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void embarcacaoAcabadaConstruir() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/embarcacaoAcabadaConstruir.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void cancelamentoRegistoEmbarcacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/cancelamentoDeReg.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registoEmbarcacaoComprada() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/embAcabadaComprar.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void tituloPropriedade() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/tituloPropriedade.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void segundaViaTituloPropriedade() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/segundaViaTituloPropriedade.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarEmbarcacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/embarcacao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarDescricaoAcontecimento() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/embarcacaoAcontecimento.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarAcontecimento() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/acontecimento.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarPorto() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/porto.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarExame() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_exame.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarEntrada() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/entrada.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarSaida() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao2/saida.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void cancelamentoRegistoEmbVendida() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/cancelamentoRegistoEmbarcacaoVendida.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void embarcacaoImportadaa() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/embarcacaoImportada.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void emissaoLicensaConstrucaoEmbarcacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/emissaoLicencaConstrucaoEmbarcacao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void emissaoTituloPropriedade() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/emissaoTituloPropriedade.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void emissaoTituloPropriedadeSegundaVia() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/emissaoTituloPropriedadeSegundaVia.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void embarcacaoAcabadaComprar() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/registoEmbarcacaoAcabadaComprar.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registoEmbarcacaoAcabadaConstruir() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Embarcacao/registoEmbarcacaoAcabadaConstruir.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	private void menuReset() {

		setInitPage("");
		setHoursPage("");
		setRecruitPage("");
		setRecPage("");
		setPerfomPage("");
		setLeavePage("");
		setTrainPage("");
		setAproovPage("");
		setMorePage("");
	}

	public String getInitPage() {
		return initPage;
	}

	public void setInitPage(String initPage) {
		this.initPage = initPage;
	}

	public String getHoursPage() {
		return hoursPage;
	}

	public void setHoursPage(String hoursPage) {
		this.hoursPage = hoursPage;
	}

	public String getRecruitPage() {
		return recruitPage;
	}

	public void setRecruitPage(String recruitPage) {
		this.recruitPage = recruitPage;
	}

	public String getPerfomPage() {
		return perfomPage;
	}

	public void setPerfomPage(String perfomPage) {
		this.perfomPage = perfomPage;
	}

	public String getLeavePage() {
		return leavePage;
	}

	public void setLeavePage(String leavePage) {
		this.leavePage = leavePage;
	}

	public String getTrainPage() {
		return trainPage;
	}

	public void setTrainPage(String trainPage) {
		this.trainPage = trainPage;
	}

	public String getRecPage() {
		return recPage;
	}

	public void setRecPage(String recPage) {
		this.recPage = recPage;
	}

	public String getAproovPage() {
		return aproovPage;
	}

	public void setAproovPage(String aproovPage) {
		this.aproovPage = aproovPage;
	}

	public String getMorePage() {
		return morePage;
	}

	public void setMorePage(String morePage) {
		this.morePage = morePage;
	}
}