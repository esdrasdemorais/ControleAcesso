package br.com.proguaru.relatorios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * Classe com m�todos utilit�rios para gerar relat�rios.
 *
 * @author David Buzatto
 */
public class ReportUtils {

    /**
     * Gera o relat�rio em PDF.
     *
     * @param inputStream InputStream que cont�m o relat�rio.
     * @param parametros Par�metros utilizados pelo relat�rio.
     * @param conexao Conex�o utilizada para a execu��o da query.
     * @param response HttpServletResponse que ser� usado como base para
     * gerar o relat�rio.
     * @return O OutputStream do HttpServletResponse passado.
     * @throws JRException Caso ocorra algum problema na gera��o do relat�rio.
     * @throws IOException Caso ocorra algum problema na obten��o do
     * OutputStream.
     */
    public static OutputStream createPDFReport(
            InputStream inputStream,
            Map<String, Object> parametros,
            Connection conexao,
            HttpServletResponse response ) throws JRException, IOException {

        // configura o content type do response
        response.setContentType( "application/pdf" );

        // obt�m o OutputStream para escrever o relat�rio
        OutputStream out = response.getOutputStream();

        /*
         * Cria um JasperPrint, que � a vers�o preenchida do relat�rio,
         * usando uma conex�o.
         */
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                inputStream, parametros, conexao );

        // Exporta em PDF, escrevendo os dados no output stream do response.
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter( JRExporterParameter.JASPER_PRINT,
                jasperPrint );
        exporter.setParameter( JRExporterParameter.OUTPUT_STREAM,
                out );

        // gera o relat�rio
        exporter.exportReport();

        // retorna o OutputStream
        return out;

    }

}