<%--
  Created by IntelliJ IDEA.
  User: lance
  Date: 31.01.2021
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FileUpload</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/upload_file_servlet" method="post">
    <div><input type="file" value="file" multiple></div>
    <input type="submit" value="submit">
</form>
</body>
</html>
