package cn.adwalker.core.spring;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import cn.adwalker.core.util.DateUtil;

public class BindingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				DateUtil.DAFAULT_DATE_FORMAT); // 设置默认的日期格式带日分秒
		dateFormat.setLenient(false);
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				DateUtil.PARTTERN_DATE_TIME);
		datetimeFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(Timestamp.class, new CustomTimestampEditor(
				datetimeFormat, true));
		binder.registerCustomEditor(String.class,
				new StringTrimmerEditor(false));

	}

}
