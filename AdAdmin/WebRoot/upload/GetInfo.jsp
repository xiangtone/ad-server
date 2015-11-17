<%
out.println(session.getAttribute("FileUpload.path"+request.getSession().getId().toString().trim()));
 %>