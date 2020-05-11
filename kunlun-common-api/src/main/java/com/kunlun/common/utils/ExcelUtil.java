package com.kunlun.common.utils;

import com.kunlun.common.constant.CommonConstant;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    private static final int HEADER_HEIGHT = 25;

    private static final int CELL_HEIGHT = 20;

    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, List<?> excelList, Object object, String sheetName, String[] headerNames) throws Exception {
        // 设置请求及响应Header
        OutputStream outputStream = setResponseHeader(request, response);

        // 创建工作簿及sheet页
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);

        // 设置全局默认的列宽和行高
//        sheet.setDefaultColumnWidth(20);
//        sheet.setDefaultRowHeightInPoints(20);

        // 表头单元格/字体样式
        XSSFCellStyle headerStyle = setHeaderStyle(workbook);
        setHeaderFont(workbook, headerStyle);

        // 单元格/字体样式，及设置单元格内容自动换行
        XSSFCellStyle cellStyle = setCellStyle(workbook);
        setCellFont(workbook, cellStyle);
        cellStyle.setWrapText(false);

        // 设置Excel表头，及表头行高
        generateHeaderRow(headerNames, sheet, headerStyle);

        // 设置数据到单元格
        int size = excelList.size();
        for (int i = 0; i < size; i++) {
            // 设置单元格行高
            XSSFRow row = sheet.createRow(i + 1);
            row.setHeightInPoints(CELL_HEIGHT);
            Field[] fields = ((Class) object).getDeclaredFields();
            int cellIndex = 0;
            for (int j = 0; j < fields.length; j++) {
                // 获取数字段
                String fieldName = fields[j].getName();
                if (fieldName.contains("id") || fieldName.contains("Id")) {
                    cellIndex--;
                    continue;
                }

                // 获取字段的值
                String property = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = ((Class) object).getMethod(property);
                Object obj = method.invoke(excelList.get(i));
                String value = String.valueOf(obj);
                if (obj instanceof Date) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
                    value = dateFormat.format(obj);
                }

                // 根据表头/单元格内容长度设置列宽
//                int length = value.getBytes().length;
//                sheet.setColumnWidth((short) j, (short) (length * 512) + 300);

                // 设置单元格值及样式
                Cell cell = row.createCell(j + cellIndex);
                cell.setCellValue(value);
                cell.setCellStyle(cellStyle);
            }
        }

        // 输出流操作及关闭
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }

    private static void generateHeaderRow(String[] headerNames, XSSFSheet sheet, XSSFCellStyle headerStyle) {
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(HEADER_HEIGHT);
        for (int j = 0; j < headerNames.length; j++) {
            Cell cell = headerRow.createCell(j);
            cell.setCellValue(headerNames[j]);
            cell.setCellStyle(headerStyle);

            // 表头列宽自动设置
            sheet.autoSizeColumn(j);
            sheet.setColumnWidth(j, sheet.getColumnWidth(j) * 17 / 10);
        }
    }

    private static void setCellFont(XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
        XSSFFont CellFont = workbook.createFont();
        CellFont.setFontName("宋体_GB2312");
        CellFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        CellFont.setFontHeightInPoints((short) 11);
        cellStyle.setFont(CellFont);
    }

    private static XSSFCellStyle setCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private static void setHeaderFont(XSSFWorkbook workbook, XSSFCellStyle headerStyle) {
        XSSFFont headerFont = workbook.createFont();
        headerFont.setFontName("宋体_GB2312");
        headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
    }

    private static XSSFCellStyle setHeaderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return headerStyle;
    }

    private static OutputStream setResponseHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding(CommonConstant.CODE_FORMAT);
        response.setCharacterEncoding(CommonConstant.CODE_FORMAT);
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=导出Excel.xlsx");
        return response.getOutputStream();
    }
}
