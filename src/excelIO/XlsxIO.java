package excelIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


public class XlsxIO {

    public static Row row(Sheet sheet, int i){
        Row row = sheet.createRow(i++);
        return row;
    }

    public static Cell cell(Row row, int colNum){
        Cell cell = row.createCell(colNum);
        return cell;
    }

    public static void setCell(Cell cell1, String value){
        cell1.setCellValue(value);
    }




}
