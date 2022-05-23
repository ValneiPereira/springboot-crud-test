package com.shadowspring.util;

import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;
import com.shadowspring.estatics.Formatacao;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelExporter {
    public static final DecimalFormat MASCARA_PORCENTO = Formatacao.MASCARA_PORCENTO;
    private final XSSFWorkbook workbook;
    private final List<Cliente> listUsers;
    private XSSFSheet sheet;

    public ExcelExporter(List<Cliente> listClientes) {
        this.listUsers = listClientes;
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Clientes");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Arial");
        font.setFontHeight(10);
        style.setFont(font);

        createCell(row, 0, "Código Cliente", style);
        createCell(row, 1, "Nome", style);
        createCell(row, 2, "Sexo", style);
        createCell(row, 3, "Data Nascimento", style);
        createCell(row, 4, "Valor Rentabilidade", style);
        createCell(row, 5, "Idade", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if(value instanceof BigDecimal ) {
            cell.setCellValue (String.valueOf(value));
        } else if (value instanceof Sexo) {
            cell.setCellValue(String.valueOf(value));
        } else if (value instanceof LocalDate) {
            cell.setCellValue(String.valueOf(value));
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        AtomicInteger rowCount = new AtomicInteger(1);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        font.setFontName("Arial");
        style.setFont(font);

        listUsers.forEach(cliente -> {
            Row row = sheet.createRow(rowCount.getAndIncrement());
            AtomicInteger columnCount = new AtomicInteger();
            createCell(row, columnCount.getAndIncrement(), cliente.getId(), style);
            createCell(row, columnCount.getAndIncrement(), cliente.getNome(),style);
            createCell(row, columnCount.getAndIncrement(), cliente.getSexo(), style);
            createCell(row, columnCount.getAndIncrement(), cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), style);
            createCell(row, columnCount.getAndIncrement(), MASCARA_PORCENTO.format(cliente.getVrRentabilidade().setScale(2, RoundingMode.HALF_UP)).replace(".", ","), style);
            createCell(row, columnCount.getAndIncrement(), cliente.getIdade(), style);
        });
    }

    public String export() throws IOException {

        writeHeaderLine();
        writeDataLines();

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        workbook.write(b);
        byte[] bytes = b.toByteArray();
        var base64 = Base64.encodeBase64String(bytes);
        workbook.close();

        return base64;
    }
}