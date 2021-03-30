package com.shadowspring.util;


import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ExcelExporter {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<Cliente> listUsers;

    public ExcelExporter(List<Cliente> listClientes) {
        this.listUsers = listClientes;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Clientes");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Codigo Cliente", style);
        createCell(row, 1, "Nome", style);
        createCell(row, 2, "Sexo", style);
        createCell(row, 3, "Data Nascimento", style);
        createCell(row, 4, "Idade", style);

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
        } else if (value instanceof Sexo) {
            cell.setCellValue(String.valueOf((Sexo) value));
        } else if (value instanceof LocalDate) {
            cell.setCellValue(String.valueOf((LocalDate) value));
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Cliente cliente : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, cliente.getId(), style);
            createCell(row, columnCount++, cliente.getNome(),style);
            createCell(row, columnCount++, cliente.getSexo(), style);
            createCell(row, columnCount++, cliente.getDataNascimento(), style);
            createCell(row, columnCount, cliente.getIdade(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
