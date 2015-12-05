/**
 * 
 */
package cn.adwalker.core.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.MathUtil;

/**
 * @author zhaozengbin 数据库导出
 */
public class DatabaseExportUtil {

	/**
	 * @param <T>
	 * @param type
	 *            要导出的实体类
	 * @param sheetName
	 *            表 标签名称
	 * @param list
	 *            要导出的数据
	 * @param titles
	 *            导出的数据名称
	 * @param fieldNames
	 *            数据名称对应字段
	 * @param exportPath
	 *            导出的路径
	 * @throws Exception
	 */
	public static <T> void exportExcel(List<T> list, String sheetName,
			String[] titles, String[] fieldNames, HttpServletResponse response)
			throws Exception {
		OutputStream os = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String name = sdf.format(new Date());
			// File file = new File(exportFileDir + name + ".xsl");
			// OutputStream os = new FileOutputStream(file);
			// 组装请求
			// HttpServletResponse response =
			// ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			/*
			 * response.setHeader("content-disposition", "attachment;filename="
			 * + name + ".xls");
			 * response.setContentType("application/ms-excel");
			 */
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment"
					+ " ;filename=" + name + ".xls");

			WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
			WritableSheet wsheet = wbook.createSheet(sheetName, 0); // 工作表名称
			// 设置Excel字体
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.RED);
			WritableCellFormat titleFormat = new WritableCellFormat(wfont);
			String[] title = titles;
			// 设置Excel表头
			for (int i = 0; i < title.length; i++) {
				Label excelTitle = new Label(i, 0, title[i], titleFormat);
				wsheet.addCell(excelTitle);
			}
			int c = 1; // 用于循环时Excel的行号
			Iterator<T> it = list.iterator();
			int count = 0;
			while (it.hasNext()) {
				try {
					T t = (T) it.next();
					Class<? extends Object> type = t.getClass();
					// 通过类实体找到所有成员变量
					Field fields[] = type.getDeclaredFields();
					for (int i = 0; i < fieldNames.length; i++) {
						String feildName = fieldNames[i];
						for (Field field : fields) {
							// 找到需要导出成员变量
							if (field.getName().equals(feildName)) {
								// 获取属性的get方法名称
								String head = field.getName().toUpperCase()
										.substring(0, 1);
								String getName = "get" + head
										+ field.getName().substring(1);
								Method getMethod = type.getMethod(getName,
										new Class[] {});
								// 执行get方便
								Object result = getMethod.invoke(t,
										new Object[] {});
								if (result instanceof Date) {
									Date date = (Date) result;
									result = sdf.format(date);
								}
								if (result instanceof Integer) {
									jxl.write.NumberFormat nf = new jxl.write.NumberFormat(
											"#,###");
									jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(
											nf);

									jxl.write.Number labelN = new jxl.write.Number(
											i, c, Integer.parseInt(result
													.toString()), wcfN);
									wsheet.setColumnView(i, 15);
									wsheet.addCell(labelN);
								} else if (result instanceof Double) {
									jxl.write.NumberFormat nf = new jxl.write.NumberFormat(
											"#,##0.0#");
									jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(
											nf);

									jxl.write.Number labelN = new jxl.write.Number(
											i, c, Double.parseDouble(result
													.toString()), wcfN);
									wsheet.setColumnView(i, 15);
									wsheet.addCell(labelN);
								} else {
									Label contentName = new Label(i, c, result
											+ "");
									wsheet.addCell(contentName);
								}
							}
						}
					}
					c++;
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				++count;
			}
			// System.out.print("处理了【" + count + "】个资源");
			wbook.write(); // 写入文件
			wbook.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	public static <T> void exportExcel(List<T> list, String fileName,
			String sheetName, String[] titles, String[] fieldNames,
			String[] summary, HttpServletResponse response) throws Exception {
		OutputStream os = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String name = sdf.format(new Date()) + "_";
			// File file = new File(exportFileDir + name + ".xsl");
			// OutputStream os = new FileOutputStream(file);
			// 组装请求
			// HttpServletResponse response =
			// ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			/*
			 * response.setHeader("content-disposition", "attachment;filename="
			 * + name + ".xls");
			 * response.setContentType("application/ms-excel");
			 */
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment"
							+ " ;filename="
							+ name
							+ new String(fileName.getBytes("gb2312"),
									"ISO8859-1") + ".xls");

			WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
			WritableSheet wsheet = wbook.createSheet(sheetName, 0); // 工作表名称

			// 设置Excel字体
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);

			WritableCellFormat titleFormat = new WritableCellFormat(wfont);
			String[] title = titles;
			// 设置Excel表头
			for (int i = 0; i < title.length; i++) {
				Label excelTitle = new Label(i, 0, title[i], titleFormat);
				wsheet.addCell(excelTitle);
			}
			// 整型的格式化
			jxl.write.NumberFormat inteFormat = new jxl.write.NumberFormat(
					"#,###");
			jxl.write.WritableCellFormat inteWriteFormat = new jxl.write.WritableCellFormat(
					inteFormat);
			// 浮点型的格式化
			jxl.write.NumberFormat doubleFormat = new jxl.write.NumberFormat(
					"#,##0.0#");
			jxl.write.WritableCellFormat doubleWriteFormat = new jxl.write.WritableCellFormat(
					doubleFormat);
			// 添加汇总
			if (summary != null) {
				for (int i = 0; i < summary.length; i++) {
					// 判断是否指数字格式
					if (NumberUtils.isNumber(summary[i].toString())) {
						// 利用异常机制 ，当出现异常时就不是int类型，
						try {
							int sum = Integer.parseInt(summary[i].toString());

							jxl.write.Number excelTitle = new jxl.write.Number(
									i, 1, sum, inteWriteFormat);

							wsheet.setColumnView(i, 15);
							wsheet.addCell(excelTitle);
						} catch (Exception e) {
							double sum = Double.parseDouble(summary[i]
									.toString());
							sum = MathUtil.truncF(sum, 2);
							jxl.write.Number excelTitle = new jxl.write.Number(
									i, 1, sum, doubleWriteFormat);

							wsheet.setColumnView(i, 15);
							wsheet.addCell(excelTitle);
						}
					} else {
						Label excelTitle = new Label(i, 1, summary[i],
								titleFormat);
						wsheet.addCell(excelTitle);
					}
				}
			}
			int c = 0; // 用于循环时Excel的行号
			if (summary != null) {
				c = 2;
			} else {
				c = 1;
			}
			Iterator<T> it = list.iterator();
			int count = 0;
			while (it.hasNext()) {
				try {
					T t = (T) it.next();
					Class<? extends Object> type = t.getClass();
					// 通过类实体找到所有成员变量
					Field fields[] = type.getDeclaredFields();
					for (int i = 0; i < fieldNames.length; i++) {
						String feildName = fieldNames[i];
						for (Field field : fields) {
							// 找到需要导出成员变量
							if (field.getName().equals(feildName)) {
								// 获取属性的get方法名称
								String head = field.getName().toUpperCase()
										.substring(0, 1);
								String getName = "get" + head
										+ field.getName().substring(1);
								Method getMethod = type.getMethod(getName,
										new Class[] {});
								// 执行get方便
								Object result = getMethod.invoke(t,
										new Object[] {});
								if (result == null||result.equals("")) {
									result = "--";
								}
								if (result instanceof Date) {

									Date date = (Date) result;
									result = sdf.format(date);
								}
								if (result instanceof Integer) {
									jxl.write.Number labelN = new jxl.write.Number(
											i, c, Integer.parseInt(result
													.toString()),
											inteWriteFormat);
									wsheet.addCell(labelN);
								} else if (result instanceof Double) {
									double result1 = MathUtil.truncF(Double
											.parseDouble(result.toString()), 2);
									jxl.write.Number labelN = new jxl.write.Number(
											i, c, result1, doubleWriteFormat);
									wsheet.addCell(labelN);
								}else if (result instanceof Long) {
									jxl.write.Number labelN = new jxl.write.Number(
											i, c, Long.parseLong(result
													.toString()),
											inteWriteFormat);
									wsheet.addCell(labelN);
								} else if (result instanceof Float) {
									double result1 = MathUtil.truncF(Double
											.parseDouble(result.toString()), 2);
									jxl.write.Number labelN = new jxl.write.Number(
											i, c, result1, doubleWriteFormat);
									wsheet.addCell(labelN);
								} else {
									Label contentName = new Label(i, c, result
											+ " ");
									wsheet.addCell(contentName);
								}
							}
						}
					}
					c++;
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				++count;
			}
			// System.out.print("处理了【" + count + "】个资源");
			wbook.write(); // 写入文件
			wbook.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
	/**
	 * @param <T>
	 * @param type
	 *            要导出的实体类
	 * @param sheetName
	 *            表 标签名称
	 * @param list
	 *            要导出的数据
	 * @param titles
	 *            导出的数据名称
	 * @param fieldNames
	 *            数据名称对应字段
	 * @param exportPath
	 *            导出的路径
	 * @throws Exception
	 */
	public static <T> void exportTextExcel(List<T> list, String sheetName,
			String[] titles, String[] fieldNames, HttpServletResponse response)
			throws Exception {
		OutputStream os = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String name = sdf.format(new Date());
			// File file = new File(exportFileDir + name + ".xsl");
			// OutputStream os = new FileOutputStream(file);
			// 组装请求
			// HttpServletResponse response =
			// ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			/*
			 * response.setHeader("content-disposition", "attachment;filename="
			 * + name + ".xls");
			 * response.setContentType("application/ms-excel");
			 */
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment"
					+ " ;filename=" + name + ".xls");

			WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
			WritableSheet wsheet = wbook.createSheet(sheetName, 0); // 工作表名称
			// 设置Excel字体
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.RED);
			WritableCellFormat titleFormat = new WritableCellFormat(wfont);
			String[] title = titles;
			// 设置Excel表头
			for (int i = 0; i < title.length; i++) {
				Label excelTitle = new Label(i, 0, title[i], titleFormat);
				wsheet.addCell(excelTitle);
			}
			int c = 1; // 用于循环时Excel的行号
			Iterator<T> it = list.iterator();
			int count = 0;
			while (it.hasNext()) {
				try {
					T t = (T) it.next();
					Class<? extends Object> type = t.getClass();
					// 通过类实体找到所有成员变量
					Field fields[] = type.getDeclaredFields();
					for (int i = 0; i < fieldNames.length; i++) {
						String feildName = fieldNames[i];
						for (Field field : fields) {
							// 找到需要导出成员变量
							if (field.getName().equals(feildName)) {
								// 获取属性的get方法名称
								String head = field.getName().toUpperCase()
										.substring(0, 1);
								String getName = "get" + head
										+ field.getName().substring(1);
								Method getMethod = type.getMethod(getName,
										new Class[] {});
								// 执行get方便
								Object result = getMethod.invoke(t,
										new Object[] {});
								if (result instanceof Date) {
									Date date = (Date) result;
									result = sdf.format(date);
								}
								if (result instanceof Integer) {
									jxl.write.NumberFormat nf = new jxl.write.NumberFormat(
											"#,###");
									jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(
											nf);

									jxl.write.Number labelN = new jxl.write.Number(
											i, c, Integer.parseInt(result
													.toString()), wcfN);
									wsheet.setColumnView(i, 15);
									wsheet.addCell(labelN);
								} else if (result instanceof Double) {
									jxl.write.NumberFormat nf = new jxl.write.NumberFormat(
											"#,##0.0#");
									jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(
											nf);

									jxl.write.Number labelN = new jxl.write.Number(
											i, c, Double.parseDouble(result
													.toString()), wcfN);
									wsheet.setColumnView(i, 15);
									wsheet.addCell(labelN);
								} else {
									WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
									Label excelTitles = new Label(i, c, result+"", contentFromart);
									wsheet.addCell(excelTitles);
								}
							}
						}
					}
					c++;
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				++count;
			}
			// System.out.print("处理了【" + count + "】个资源");
			wbook.write(); // 写入文件
			wbook.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
	
	public static <T> void exportExcelText(List<T> list, String fileName,
			String sheetName, String[] titles, String[] fieldNames,
			String[] summary, HttpServletResponse response) throws Exception {
		OutputStream os = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String name = sdf.format(new Date()) + "_";
			// File file = new File(exportFileDir + name + ".xsl");
			// OutputStream os = new FileOutputStream(file);
			// 组装请求
			// HttpServletResponse response =
			// ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			/*
			 * response.setHeader("content-disposition", "attachment;filename="
			 * + name + ".xls");
			 * response.setContentType("application/ms-excel");
			 */
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment"
							+ " ;filename="
							+ new String(fileName.getBytes("gb2312"),
									"ISO8859-1") + ".xls");

			WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
			WritableSheet wsheet = wbook.createSheet(sheetName, 0); // 工作表名称

			// 设置Excel字体
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);

			WritableCellFormat titleFormat = new WritableCellFormat(wfont);
			String[] title = titles;
			// 设置Excel表头
			for (int i = 0; i < title.length; i++) {
				Label excelTitle = new Label(i, 0, title[i], titleFormat);
				wsheet.addCell(excelTitle);
			}
			// 整型的格式化
			jxl.write.NumberFormat inteFormat = new jxl.write.NumberFormat(
					"#,###");
			jxl.write.WritableCellFormat inteWriteFormat = new jxl.write.WritableCellFormat(
					inteFormat);
			// 浮点型的格式化
			jxl.write.NumberFormat doubleFormat = new jxl.write.NumberFormat(
					"#,##0.0#");
			
			// 添加汇总
			if (summary != null) {
				for (int i = 0; i < summary.length; i++) {
					// 判断是否指数字格式
					if (NumberUtils.isNumber(summary[i].toString())) {
						// 利用异常机制 ，当出现异常时就不是int类型，
						try {
							int sum = Integer.parseInt(summary[i].toString());
//							jxl.write.Number excelTitle = new jxl.write.Number(
//									i, 1, sum, inteWriteFormat);
//							wsheet.setColumnView(i, 15);
//							wsheet.addCell(excelTitle);
							WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
							Label excelTitles = new Label(i, 1, sum+"", contentFromart);
							wsheet.addCell(excelTitles);
						} catch (Exception e) {
							double sum = Double.parseDouble(summary[i]
									.toString());
							WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
							Label excelTitles = new Label(i, 1, sum+"", contentFromart);
							wsheet.addCell(excelTitles);
						}
					} else {
						WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
						Label excelTitles = new Label(i, 1,  summary[i]+"", contentFromart);
						wsheet.addCell(excelTitles);
					}
				}
			}
			int c = 0; // 用于循环时Excel的行号
			if (summary != null) {
				c = 2;
			} else {
				c = 1;
			}
			Iterator<T> it = list.iterator();
			int count = 0;
			while (it.hasNext()) {
				try {
					T t = (T) it.next();
					Class<? extends Object> type = t.getClass();
					// 通过类实体找到所有成员变量
					Field fields[] = type.getDeclaredFields();
					for (int i = 0; i < fieldNames.length; i++) {
						String feildName = fieldNames[i];
						for (Field field : fields) {
							// 找到需要导出成员变量
							if (field.getName().equals(feildName)) {
								// 获取属性的get方法名称
								String head = field.getName().toUpperCase()
										.substring(0, 1);
								String getName = "get" + head
										+ field.getName().substring(1);
								Method getMethod = type.getMethod(getName,
										new Class[] {});
								// 执行get方便
								Object result = getMethod.invoke(t,
										new Object[] {});
								if (result == null) {
									result = "--";
								}
								if (result instanceof Date) {

									Date date = (Date) result;
									result = sdf.format(date);
								}
								if (result instanceof Integer) {
									WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
									Label excelTitles = new Label(i, c, result+"", contentFromart);
									wsheet.addCell(excelTitles);
								} else if (result instanceof Double) {
//									double result1 = MathUtil.truncF(Double
//											.parseDouble(result.toString()), 2);
//									jxl.write.Number labelN = new jxl.write.Number(
//											i, c, result1, doubleWriteFormat);
//									wsheet.addCell(labelN);
									WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
									Label excelTitles = new Label(i, c, result+"", contentFromart);
									wsheet.addCell(excelTitles);
								}else if (result instanceof Long) {
//									jxl.write.Number labelN = new jxl.write.Number(
//											i, c, Long.parseLong(result
//													.toString()),
//											inteWriteFormat);
//									wsheet.addCell(labelN);
									WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
									Label excelTitles = new Label(i, c, result+"", contentFromart);
									wsheet.addCell(excelTitles);
								} else if (result instanceof Float) {
//									double result1 = MathUtil.truncF(Double
//											.parseDouble(result.toString()), 2);
//									jxl.write.Number labelN = new jxl.write.Number(
//											i, c, result1, doubleWriteFormat);
//									wsheet.addCell(labelN);
									
									WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
									Label excelTitles = new Label(i, c, result+"", contentFromart);
									wsheet.addCell(excelTitles);
								} else {
//									Label contentName = new Label(i, c, result
//											+ "");
//									wsheet.addCell(contentName);
									WritableCellFormat   contentFromart   =   new   WritableCellFormat(NumberFormats.TEXT);
									Label excelTitles = new Label(i, c, result+"", contentFromart);
									wsheet.addCell(excelTitles);
								}
							}
						}
					}
					c++;
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				++count;
			}
			// System.out.print("处理了【" + count + "】个资源");
			wbook.write(); // 写入文件
			wbook.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
}
