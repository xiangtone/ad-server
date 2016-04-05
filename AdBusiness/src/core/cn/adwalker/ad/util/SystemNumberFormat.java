package cn.adwalker.ad.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.math.NumberUtils;

public class SystemNumberFormat {
	
	public static String formatNumber(String time, Object number) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat dfOld = new DecimalFormat("#,##0.0#");
		DecimalFormat dfNew = new DecimalFormat("#,##0");
		String numberformat_StartTime = ConfigUtil
				.getString("numberformat_StartTime");
		if (number == null) {
			number = 0;
		}
		String numberStr = dfOld.format(number);
		if (time != null && !time.trim().equals("")) {
			if (time.contains("至")) {
				time = time.substring(0, time.indexOf("至"));
			}
			try {
				long d1 = sdf.parse(numberformat_StartTime).getTime();
				long d2 = sdf.parse(time).getTime();
				if (d2 >= d1) {
					numberStr = dfNew.format(number);
				}
			} catch (Exception e1) {
			}
		}
		return numberStr;
	}

	/**
	 * 
	 * <p>
	 * Title: exportExcel 系统后台数字格式调整需求专用导出Excel
	 * </p>
	 * 
	 * @param list
	 * @param fileName
	 * @param sheetName
	 * @param titles
	 * @param fieldNames
	 * @param summary
	 * @param startTime
	 * @param statDateList
	 * @param isMoney
	 * @param pattern
	 * @param response
	 * @throws Exception
	 * @return void
	 * @throws
	 */
	public static <T> void exportExcel(List<T> list, String fileName,
			String sheetName, String[] titles, String[] fieldNames,
			String[] summary, String startTime, List<String> statDateList,
			boolean[] isMoney, String[] pattern, HttpServletResponse response)
			throws Exception {
		OutputStream os = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String name = sdf.format(new Date()) + "_";
			// 组装请求
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment"
							+ " ;filename="
							+ name
							+ new String(fileName.getBytes("gb2312"),
									"ISO8859-1") + ".xls");

			WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
			WritableSheet wsheet = wbook.createSheet(fileName, 0); // 工作表名称

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

			String numberformat_StartTime = ConfigUtil
					.getString("numberformat_StartTime");
			long d1 = sdf.parse(numberformat_StartTime).getTime();

			List<jxl.write.WritableCellFormat> formatList = new ArrayList<WritableCellFormat>();
			if (pattern != null && pattern.length > 0) {
				for (int i = 0; i < pattern.length; i++) {
					jxl.write.WritableCellFormat writeFormat = null;
					if (pattern[i] != null && !pattern[i].trim().equals("")) {
						jxl.write.NumberFormat format = new jxl.write.NumberFormat(
								pattern[i]);
						writeFormat = new jxl.write.WritableCellFormat(format);
					}
					formatList.add(writeFormat);
				}
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
			// 金额的格式化
			jxl.write.NumberFormat moneyFormat = new jxl.write.NumberFormat(
					"#,##0.00");
			jxl.write.WritableCellFormat moneyWriteFormat = new jxl.write.WritableCellFormat(
					moneyFormat);
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

							if (isMoney[i]) {
								excelTitle = new jxl.write.Number(i, 1, sum,
										moneyWriteFormat);
							} else {
								if (formatList != null && formatList.size() > 0) {
									if (formatList.get(i) != null) {
										if (startTime != null
												&& !startTime.trim().equals("")) {
											try {
												long d2 = sdf.parse(startTime)
														.getTime();
												if (d2 >= d1) {
													excelTitle = new jxl.write.Number(
															i, 1, sum,
															formatList.get(i));
												}
											} catch (Exception e1) {
												// TODO: handle exception
											}
										}
									}
								}
							}

							wsheet.setColumnView(i, 15);
							wsheet.addCell(excelTitle);
						} catch (Exception e) {
							double sum = Double.parseDouble(summary[i]
									.toString());
							sum = Function.truncF(sum, 2);
							jxl.write.Number excelTitle = new jxl.write.Number(
									i, 1, sum, doubleWriteFormat);

							if (isMoney[i]) {
								excelTitle = new jxl.write.Number(i, 1, sum,
										moneyWriteFormat);
							} else {
								if (formatList != null && formatList.size() > 0) {
									if (formatList.get(i) != null) {
										if (startTime != null
												&& !startTime.trim().equals("")) {
											try {
												long d2 = sdf.parse(startTime)
														.getTime();
												if (d2 >= d1) {
													excelTitle = new jxl.write.Number(
															i, 1, sum,
															formatList.get(i));
												}
											} catch (Exception e1) {
												// TODO: handle exception
											}
										}
									}
								}
							}

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
			final int distance = c;
			Iterator<T> it = list.iterator();
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
								if (result == null || result.equals("")) {
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

									if (isMoney[i]) {
										labelN = new jxl.write.Number(i, c,
												Integer.parseInt(result
														.toString()),
												moneyWriteFormat);
									} else {
										if (formatList != null
												&& formatList.size() > 0) {
											if (formatList.get(i) != null) {
												if (statDateList != null
														&& statDateList.size() > 0) {
													try {
														String statDate = statDateList
																.get(c
																		- distance);
														if (statDate
																.contains("至")) {
															statDate = statDate
																	.substring(
																			0,
																			statDate.indexOf("至"));
														}
														long d2 = sdf.parse(
																statDate)
																.getTime();
														if (d2 >= d1) {
															labelN = new jxl.write.Number(
																	i,
																	c,
																	Integer.parseInt(result
																			.toString()),
																	formatList
																			.get(i));
														}
													} catch (Exception e1) {
														// TODO: handle
														// exception
													}
												}
											}
										}
									}

									wsheet.addCell(labelN);
								} else if (result instanceof Double) {
									double result1 = Function.truncF(Double
											.parseDouble(result.toString()), 2);
									jxl.write.Number labelN = new jxl.write.Number(
											i, c, result1, doubleWriteFormat);

									if (isMoney[i]) {
										labelN = new jxl.write.Number(i, c,
												result1, moneyWriteFormat);
									} else {
										if (formatList != null
												&& formatList.size() > 0) {
											if (formatList.get(i) != null) {
												if (statDateList != null
														&& statDateList.size() > 0) {
													try {
														String statDate = statDateList
																.get(c
																		- distance);
														if (statDate
																.contains("至")) {
															statDate = statDate
																	.substring(
																			0,
																			statDate.indexOf("至"));
														}
														long d2 = sdf.parse(
																statDate)
																.getTime();
														if (d2 >= d1) {
															labelN = new jxl.write.Number(
																	i,
																	c,
																	result1,
																	formatList
																			.get(i));
														}
													} catch (Exception e1) {
														// TODO: handle
														// exception
													}
												}
											}
										}
									}

									wsheet.addCell(labelN);
								} else if (result instanceof Long) {
									jxl.write.Number labelN = new jxl.write.Number(
											i, c, Long.parseLong(result
													.toString()),
											inteWriteFormat);

									if (isMoney[i]) {
										labelN = new jxl.write.Number(i, c,
												Long.parseLong(result
														.toString()),
												moneyWriteFormat);
									} else {
										if (formatList != null
												&& formatList.size() > 0) {
											if (formatList.get(i) != null) {
												if (statDateList != null
														&& statDateList.size() > 0) {
													try {
														String statDate = statDateList
																.get(c
																		- distance);
														if (statDate
																.contains("至")) {
															statDate = statDate
																	.substring(
																			0,
																			statDate.indexOf("至"));
														}
														long d2 = sdf.parse(
																statDate)
																.getTime();
														if (d2 >= d1) {
															labelN = new jxl.write.Number(
																	i,
																	c,
																	Long.parseLong(result
																			.toString()),
																	formatList
																			.get(i));
														}
													} catch (Exception e1) {
														// TODO: handle
														// exception
													}
												}
											}
										}
									}

									wsheet.addCell(labelN);
								} else if (result instanceof Float) {
									double result1 = Function.truncF(Double
											.parseDouble(result.toString()), 2);
									jxl.write.Number labelN = new jxl.write.Number(
											i, c, result1, doubleWriteFormat);

									if (isMoney[i]) {
										labelN = new jxl.write.Number(i, c,
												result1, moneyWriteFormat);
									} else {
										if (formatList != null
												&& formatList.size() > 0) {
											if (formatList.get(i) != null) {
												if (statDateList != null
														&& statDateList.size() > 0) {
													try {
														String statDate = statDateList
																.get(c
																		- distance);
														if (statDate
																.contains("至")) {
															statDate = statDate
																	.substring(
																			0,
																			statDate.indexOf("至"));
														}
														long d2 = sdf.parse(
																statDate)
																.getTime();
														if (d2 >= d1) {
															labelN = new jxl.write.Number(
																	i,
																	c,
																	result1,
																	formatList
																			.get(i));
														}
													} catch (Exception e1) {
														// TODO: handle
														// exception
													}
												}
											}
										}
									}

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
			}
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
