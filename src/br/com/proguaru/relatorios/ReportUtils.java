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
 * Classe com métodos utilitários para gerar relatórios.
 *
 * @author David Buzatto
 */
public class ReportUtils {

    /**
     * Gera o relatório em PDF.
     *
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param conexao Conexão utilizada para a execução da query.
     * @param response HttpServletResponse que será usado como base para
     * gerar o relatório.
     * @return O OutputStream do HttpServletResponse passado.
     * @throws JRException Caso ocorra algum problema na geração do relatório.
     * @throws IOException Caso ocorra algum problema na obtenção do
     * OutputStream.
     */
    public static OutputStream createPDFReport(
            InputStream inputStream,
            Map<String, Object> parametros,
            Connection conexao,
            HttpServletResponse response ) throws JRException, IOException {

        // configura o content type do response
        response.setContentType( "application/pdf" );

        // obtém o OutputStream para escrever o relatório
        OutputStream out = response.getOutputStream();

        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando uma conexão.
         */
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                inputStream, parametros, conexao );

        // Exporta em PDF, escrevendo os dados no output stream do response.
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter( JRExporterParameter.JASPER_PRINT,
                jasperPrint );
        exporter.setParameter( JRExporterParameter.OUTPUT_STREAM,
                out );

        // gera o relatório
        exporter.exportReport();

        // retorna o OutputStream
        return out;

    }

}