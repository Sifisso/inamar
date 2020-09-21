package mz.ciuem.inamar.comps;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

@SuppressWarnings({ "serial", "rawtypes" })
public class MasterRep extends GenericForwardComposer {

	@SuppressWarnings("unused")
	private Iframe frame_report;
	private static byte bytes[] = null;
	Include inc;

	@SuppressWarnings("unchecked")
	public static void imprimir(String path, List beans, Map map, Window win)
			throws JRException {

		final Execution exc = Executions.getCurrent();

		JasperReport report = JasperCompileManager.compileReport(exc
				.getDesktop().getWebApp().getRealPath(path));

		bytes = JasperRunManager.runReportToPdf(report, map,
				new JRBeanCollectionDataSource(beans));
		

		try {
			final InputStream mediaias = new ByteArrayInputStream(bytes);
			final AMedia amedia = new AMedia("relatorio_"
					+ (int) (Math.random() * 1000) + ".pdf", "pdf",
					"application/pdf", mediaias);

			Iframe frame = new Iframe();
			frame.setWidth("100%");
			frame.setHeight("100%");
			frame.setContent(amedia);
			Window wind = new Window();
			wind.setClosable(true);
			wind.setBorder(true);
			wind.setWidth("70%");
			wind.setHeight("90%");
			wind.setTitle("....");
			wind.setParent(win);
			wind.doModal();
			frame.setParent(wind);

		} catch (Exception ed) {

			ed.printStackTrace();
		}

	}

}