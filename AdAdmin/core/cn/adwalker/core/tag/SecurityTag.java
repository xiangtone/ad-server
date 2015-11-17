package cn.adwalker.core.tag;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.adwalker.core.context.Context;

/**
 * 功能:拼接表头排序图片链接
 * 
 * @author winnie
 * 
 */
public class SecurityTag extends BodyTagSupport {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5734438530693324271L;

	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(SecurityTag.class);

	String code;
	String type;

	public int doStartTag() throws JspTagException {
		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() throws JspTagException {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		StringBuilder sb = new StringBuilder("");
		if (StringUtils.isEmpty(type) || StringUtils.isEmpty(code)) {
			try {
				pageContext.getOut().write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.toUpperCase().equals("BUTTON")) {
			Context context = Context.getInstance();
			HttpSession session = context.getSession();
			Set<String> list = (Set<String>) session
					.getAttribute("myButtonList");
			if (list != null && list.size() > 0 && list.contains(code)) {

			} else {
				sb.append("disabled=\"disabled\" title=\"没有权限\"");
			}

		}

		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	public void doInitBody() throws JspTagException {

	}

	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}