<%--
  Created by IntelliJ IDEA.
  User: sukaixiang
  Date: 2017/11/7
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Soul</title>
</head>
<body>
        <form id="login" action="/doLogin"  name="loginForm" method="post">
            <input type="hidden" name="_unq_tfmk_id" value=""/>
            <input name="username" value="" placeholder="example@domain.com"/>
            <input name="password" value=""/>
            <button type="submit"/>
        </form>

</body>
</html>
