package cn.adwalker.ad.admin.report.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

public class ExcelExportService {
    Logger logger = Logger.getLogger(ExcelExportService.class);

    public void exportXlsFile(HttpServletResponse response, String[] titles,
            String[] total, List content, String fileName, String workbookName) {
        if (fileName == null || fileName.equals("")) {
            fileName = "统计报表";
        }
        if (workbookName == null || workbookName.equals("")) {
            workbookName = "报表";
        }
        try {
            fileName = new String((fileName).getBytes(), "ISO-8859-1");

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            logger.error("转码出错!" + e);
        }
        response.setHeader("Content-Disposition", "attachment;   filename="
                + fileName);
        response.setContentType("APPLICATION/OCTET-STREAM;   charset=UTF-8");
        try {
            ServletOutputStream out = response.getOutputStream();

            writeExcel(out, titles, total, content, workbookName);

            out.close();
        } catch (IOException e) {
            logger.error("导出Excel文件时出错", e);
        }
    }

    /**
     * @param total
     * @param startDate
     * @param endDate
     * @return List<List<Object>>这种形式的结果集
     */
    public String writeExcel(OutputStream os, String[] title, String[] total,
            List dataList, String workbookName) {

        WritableWorkbook wbook = null;

        // 设置头部字体格式
        WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,

        WritableFont.BOLD, false,

        jxl.format.UnderlineStyle.NO_UNDERLINE,

        jxl.format.Colour.BLACK);

        WritableCellFormat titleFormat = new WritableCellFormat(wfont);

        // 创建一个excel文件
        try {
            wbook = Workbook.createWorkbook(os);

            WritableSheet wsheet = wbook.createSheet(workbookName, 0); // 工作表名称

            // 添加头部名称
            for (int i = 0; i < title.length; i++) {
                Label excelTitle = new Label(i, 0, title[i], titleFormat);

                wsheet.addCell(excelTitle);
            }
            // 填充总和数据
            int dataBegin = 1;
            if (total != null && total.length >= 1) {
                dataBegin = 2;
                for (int i = 0; i < total.length; i++) {
                    Label excelTitle = new Label(i, 1, total[i]);

                    wsheet.addCell(excelTitle);
                }
            }
            

            // 创建内容
            int len = dataList.size();
            for (int i = 0; i < len; i++) {
                List tmpList = (List) dataList.get(i);

                for (int j = 0; j < tmpList.size(); j++) {
                    Object o = tmpList.get(j);
                    String temp = "";
                    if (o != null) {
                        temp = o.toString();
                    }
                    Label label = null;
                    label = new Label(j, i + dataBegin, temp);
                    WritableCellFormat cellFormat=new WritableCellFormat();
                    cellFormat.setAlignment(jxl.format.Alignment.LEFT);
                    cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
                    cellFormat.setWrap(true);
                    label.setCellFormat(cellFormat);
                    
                    wsheet.addCell(label);

                }
            }

            wbook.write();
            wbook.close();

            os.close();
        } catch (Exception e) {
            logger.error("创建Excel时出错", e);
        }

        return null;
    }
}
