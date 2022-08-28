package ru.grabovsky.market.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.dto.ProductDto;
import ru.grabovsky.market.mappers.ProductMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final FileService fileService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public void createReport() {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(fileService.getFilePath("report.xlsx").toFile());) {
            Sheet sheet = workbook.createSheet("Продукты");
            createProductHeader(workbook, sheet);
            createReportData(workbook, sheet);
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createReportData(Workbook workbook, Sheet sheet) {
        CellStyle rowStyle = workbook.createCellStyle();

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(false);
        rowStyle.setFont(font);

        List<ProductDto> products = productService.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());

        for (int i = 0; i < products.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(products.get(i).getId());
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(products.get(i).getTitle());
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(products.get(i).getPrice().doubleValue());
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(Math.random() * 10);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(products.get(i).getCategoryName());
        }
    }

    private static void createProductHeader(Workbook workbook, Sheet sheet) {
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        Row header = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Название");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Цена");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Остаток");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Категория");
        headerCell.setCellStyle(headerStyle);
    }

}
