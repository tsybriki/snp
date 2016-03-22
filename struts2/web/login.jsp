<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="ru.mipt.snp.web.ApplicationMessages">
    <html>
    <head>
        <title><fmt:message key="login.title"/></title>
        <link rel="stylesheet" type="text/css" href="<c:url value="css/styles.css"/>">
    </head>
    <body>
    <table class="login-outer" cellpadding="0" cellspacing="0">
        <tr>
            <td class="middleCenter">
                <div class="middleCenterInner">
                    <form action="j_security_check" method="post">
                        <table class="login-form-table">
                            <tr>
                                    <%--colspan="2" bgcolor="#ff6666"--%>
                                <td colspan="2" id="header-cell">
                                    <fmt:message key="login.signIn"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="40%"><fmt:message key="login.username"/>:</td>
                                <td width="60%"><input type="text" name="j_username" size="20"/></td>
                            </tr>
                            <tr>
                                <td width="40%"><fmt:message key="login.password"/>:</td>
                                <td width="60%"><input type="password" name="j_password" size="20"/></td>
                            </tr>
                            <tr>
                                <td align="left" colspan="2" id="remember-me-cell">
                                    <input type="checkbox" name="_spring_security_remember_me"/><fmt:message
                                        key="login.rememberMe"/>
                                </td>
                            </tr>
                            <tr id="buttons-row">
                                <td colspan="2">
                                    <input type="submit" value="<fmt:message key="login.logIn"/>"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </td>
        </tr>
    </table>
    </body>
    </html>
</fmt:bundle>