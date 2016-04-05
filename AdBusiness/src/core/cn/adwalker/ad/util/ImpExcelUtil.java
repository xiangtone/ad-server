/**
 * 
 */
package cn.adwalker.ad.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Workbook;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wjp
 * 解析excel
 */ 
public class ImpExcelUtil {

	/**
	 * 解析excel公用方法
	 * @param file
	 * @return
	 */
	public static Workbook getWorkbook(@RequestParam MultipartFile file) {

		Workbook workbook = null; //构建excel工作薄
		try {
			if (!file.isEmpty()) {
				workbook = Workbook.getWorkbook(file.getInputStream());
			}
		} catch (Exception e) {
			System.out.println("解析excel文件异常");
			//e.printStackTrace();
		} 
		return workbook;
	}
	
	/**
	 * 获取表格里面日期类型的数据
	 * 
	 * @param cell
	 * @return
	 */
	public static String getDateCell(Cell cell){
		String cellcon="";
		if(cell.getType() == CellType.DATE){
			DateCell dc = (DateCell)cell;
			Date date = dc.getDate();
			SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
			cellcon = ds.format(date);
		}
	
		return cellcon;
	}
	
	/**
	 * 关闭
	 * @param workbook
	 */
	public static void close(Workbook workbook){
		if (workbook != null) {
			workbook.close();
		}
	}
}
