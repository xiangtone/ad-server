<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="manage!addPurview.do" method="post">
		<tr>
			<td>权限类型：</td>
			<td>
				<select>
					<option></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>权限名称：</td>
			<td>
				<input name="resName" /> 
			</td>
		</tr>
		<tr>
			<td>权限URL：</td>
			<td>
				<input name="resUrl" /> 
			</td>
		</tr>
	</form>

</body>
</html>