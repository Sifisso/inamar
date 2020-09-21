package mz.ciuem.inamar.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.inamar.entity.ErrorLog;
import mz.ciuem.inamar.service.ErrorLogService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ErrorLogVM  {

	@WireVariable
	private ErrorLogService errorLogService;

	@Wire
	private Button reportError;

	private ErrorLog errorLog;

	private List<ErrorLog> errorLogs;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass=true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {


		setErrorLog(new ErrorLog());

		setErrorLogs(errorLogService.getAll());
	}

	@NotifyChange({ "reportError", "errorLogs" })
	@Command
	public void report(@BindingParam("message") String msg,
			@BindingParam("type") String type) throws InterruptedException {

		reportError.setDisabled(true);

		Thread.sleep(2000);

		errorLog.setType(type);
		errorLog.setDescription(msg);

		errorLogService.create(errorLog);

		setErrorLog(new ErrorLog());

		errorLogs = errorLogService.getAll();
	}

	@Command
	public void seeAllErrors(@BindingParam("ui") Div ui,
			@BindingParam("side") Div side) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", ui);
		ui.getChildren().clear();
		Executions.createComponents("views/error/pagErrors.zul", ui, map);

		List<String> links = new ArrayList<String>();
		links.add("Todos Erros");
	}

	@Command
	public void solvedError(@BindingParam("errorId") Long id) {

		ErrorLog found = errorLogService.find(id);

		found.setStatus(true);

		errorLogService.update(found);

	}

	public List<ErrorLog> lastFive() {

		if (this.errorLogs.size() > 3) {

			return this.errorLogs.subList(0, 3);
		}

		return this.errorLogs;
	}

	public String desc(ErrorLog el) {

		if (el.getDescription().length() > 125) {

			return el.getDescription().substring(0, 125);
		}

		return el.getDescription();
	}

	public void refresh(Component ui) {

		ui.invalidate();
		Clients.evalJavaScript("initDataTable()");
	}

	public ErrorLog getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(ErrorLog errorLog) {
		this.errorLog = errorLog;
	}

	public List<ErrorLog> getErrorLogs() {
		return errorLogs;
	}

	public void setErrorLogs(List<ErrorLog> errorLogs) {
		this.errorLogs = errorLogs;
	}
}
