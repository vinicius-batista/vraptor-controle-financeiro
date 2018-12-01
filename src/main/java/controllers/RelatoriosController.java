package controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.InputStreamDownload;
import modelo.dao.ConexaoFactory;
import modelo.dao.DAOFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Controller
public class RelatoriosController {

  public Download usuarios() {
    ByteArrayOutputStream exported = new ByteArrayOutputStream();

    try {
      InputStream arquivoJasper;
      arquivoJasper = getClass().getResourceAsStream("/RelatorioUsuarios.jasper");
      var conexao = ConexaoFactory.getConexao();
      JasperPrint print = JasperFillManager.fillReport(arquivoJasper, null, conexao);

      JRExporter exporter = new JRPdfExporter();
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, exported);
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

      exporter.exportReport();
    } catch (JRException ex) {
      System.out.println("Problemas na geracao do PDF." + "\n" + ex);
    } catch (SQLException ex) {
      DAOFactory.mostrarSQLException(ex);
    }
    byte[] content = exported.toByteArray();
    return new InputStreamDownload(new ByteArrayInputStream(content), "application/pdf", "relatorio-usuarios", false,
        content.length);
  }
}