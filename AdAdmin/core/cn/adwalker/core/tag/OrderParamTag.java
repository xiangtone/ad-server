package cn.adwalker.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class OrderParamTag extends TagSupport {


	 /**
	 * 
	 */
	private static final long serialVersionUID = 5910720474549443253L;
	 
	String name;                        //Holder for the id attribute
	String value;                     //Holder for value attribute

	 public void setName(String name){
		 this.name = name;
	 }
	 public void setValue(String value){
		 this.value = value;
	 }
	 @SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
	     OrderTag mapDef = (OrderTag) this.getParent();
	     mapDef.getMap().put(name,value);
	     return EVAL_PAGE;
	 }
}