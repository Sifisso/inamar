package mz.ciuem.inamar.util;

public enum PermissionsUtil {

	GESTÃO_DE_PROVINCIAS ("O utilizador poderá registar e actualizar provincias."),
	GESTÃO_DE_DISTRITOS("O utilizador poderá registar e actualizar distritos."),
	GESTÃO_DE_LOCAIS("O utilizador poderá registar e actualizar locais."),
	GESTÃO_DE_UNIVERSIDADES("O utilizador poderá registar e actualizar universidades."),
	GESTÃO_DE_ESCOLAS_PRE_UNIVERSITARIAS("O utilizador poderá registar e actualizar escolas pre-universitarias."),
	GESTÃO_DE_UNIDADE_ORGANICA("O utilizador poderá registar e actualizar unidades organicas."),
	GESTÃO_DE_SECTOR("O utilizador poderá registar e actualizar sectores."),
	GESTÃO_DE_CURSO("O utilizador poderá registar e actualizar cursos."),
	GESTÃO_DE_DISCIPLINA("O utilizador poderá registar e actualizar disciplinas"),
	GESTÃO_DE_AGENTE("O utilizador poderá criar, configurar os ciclos, adicionar fases e respectivos períodos de execução."),
	GESTÃO_DE_PAPEL_AGENTE("O utilizador poderá registar e actualizar papeis do agente."),
	GESTÃO_DE_CONTAS("O utilizador poderá, registar e actualizar contas."),
	GESTÃO_DE_CICLO("O utilizador poderá criar, configurar os ciclos, adicionar universidades e as respectivas disciplinas do ciclo."),
	GESTÃO_DE_PRE_REGISTO("O utilizador poderá validar o pre-registo."),
	GESTÃO_DE_MONITORIA("O utilizador poderá fazer monitoria"),
	GESTÃO_DE_AGENTE_ADMIN("O utilizador poderá gerir todo relacionado aos agentes."),
	GESTÃO_DE_EXPORTACÃO_DE_DADOS("O utilizador poderá exportar dados."),
	E_SUPER_ADMINISTRADOR("O utilizador e administrador do sistema.");
	
	final String value;

	PermissionsUtil(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
