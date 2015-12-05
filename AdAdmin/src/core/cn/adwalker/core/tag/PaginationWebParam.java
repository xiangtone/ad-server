package cn.adwalker.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class PaginationWebParam extends TagSupport {


	private static final long serialVersionUID = 4269198089654718893L;

	 String name;                        //Holder for the id attribute
	 String value;                     //Holder for value attribute

	 public void setName(String name){
		 this.name = name;
	 }
	 public void setValue(String value){
		 this.value = value;
	 }
	 public int doEndTag() throws JspException {

	     PaginationWeb mapDef = (PaginationWeb) this.getParent();
	   	     
	     
	     mapDef.getMap().put(name,value);
	     return EVAL_PAGE;
	     
	 }
}