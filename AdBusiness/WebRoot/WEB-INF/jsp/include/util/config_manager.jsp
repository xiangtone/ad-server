<%@page import="cn.adwalker.ad.config.util.ConfigManager"%>
<% 
String contact_us_phone=ConfigManager.getConfigData("CONTACT_US_PHONE");
String contact_us_add=ConfigManager.getConfigData("CONTACT_US_ADD");
String company=ConfigManager.getConfigData("COMPANY");
String zip_code=ConfigManager.getConfigData("ZIP_CODE");
String join_us_email=ConfigManager.getConfigData("JOIN_US_EMAIL");

String menu_head_logo=ConfigManager.getConfigData("MENU_HEAD_LOGO");
String web_url=ConfigManager.getConfigData("WEB_URL");
String join_us_email_url=ConfigManager.getConfigData("JOIN_US_EMAIL_URL");
String developer_help_android=ConfigManager.getConfigData("DEVELOPER_HELP_ANDROID");
String developer_help_ios=ConfigManager.getConfigData("DEVELOPER_HELP_IOS");

String service_qq=ConfigManager.getConfigData("SERVICE_QQ");
String service_phone=ConfigManager.getConfigData("SERVICE_PHONE");
String service_email=ConfigManager.getConfigData("SERVICE_EMAIL");

String service_notice_qq=ConfigManager.getConfigData("SERVICE_NOTICE_QQ");
String service_notice_phone=ConfigManager.getConfigData("SERVICE_NOTICE_PHONE");
String service_notice_email=ConfigManager.getConfigData("SERVICE_NOTICE_EMAIL");
%>