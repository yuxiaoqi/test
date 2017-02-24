package ExcelPoi;

import com.sun.media.sound.InvalidFormatException;
import com.sun.rowset.internal.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Dscription:
 * @Created: yyq
 * @Date: 2017/2/9 11:47
 * @version: 1.0.0.0
 */
public class CreateSql {
    public static void main(String[] args) {
        File excelFile = new File("C:\\Users\\admin\\Desktop\\25项报废修正.xlsx"); //创建文件对象
        File outexcelFile = new File("C:\\Users\\admin\\Desktop\\问题.xlsx"); //创建文件对象
        FileInputStream is = null; //文件流
        FileOutputStream out = null;
        try {
            is = new FileInputStream(excelFile);
            out = new FileOutputStream(outexcelFile);
            Workbook workbook = WorkbookFactory.create(is);
            //生成Workbook
            Workbook wb = new XSSFWorkbook();
            Sheet wbSt = wb.createSheet("sheet1");

            Sheet sheet =workbook.getSheetAt(0);
            if (sheet == null){
                throw new Exception("excel 格式不合适！");
            }
            for (int i = 1 ; i<=sheet.getLastRowNum() ;i++){
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if (null == row){
                    break;
                }
                if (row.getCell(1).equals("泉州不良品仓")) {
                    System.out.println("-------------------------------查询记录为空---------------------");
                    org.apache.poi.ss.usermodel.Row temp = wbSt.createRow(i-1);
                    for (int j = 0;j <row.getLastCellNum();j++) {
                        temp.createCell(j).setCellValue(row.getCell(j).toString());
                    }
                    System.out.println("仓库"+row.getCell(1).toString()+"   批次号"+row.getCell(5).toString() +"  库位"+row.getCell(7).toString());

                }else {

                    System.out.println("update erp_stock_inventory set " +
                            " qty = qty -" + row.getCell(9) +
                            " , batch_qty = batch_qty -" + row.getCell(9) +
                            " , available_qty = available_qty -" + row.getCell(9) +
                            "  where warehouse_id = " + row.getCell(1) +
                            " and batch_no =" + "'" + row.getCell(5) +
                            " ' and stock_allocation_id = " + row.getCell(7) + " ;");
                }
            }
            wb.write(out);
            is.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
