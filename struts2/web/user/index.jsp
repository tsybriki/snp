<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>SNP - Together to a healthy future</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/menu.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/smoothness/jquery-ui-1.7.2.custom.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/ui.jqgrid.css"/>"/>    
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.windows-engine.css"/>"/>
    <script type="text/javascript" src="<c:url value="/jquery/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/jquery/jquery.json.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/jquery/jquery-ui-1.7.2.custom.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/jquery/jquery.jqGrid.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/jquery/jquery-grid-helpers.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/jquery/jquery.windows-engine.js"/>"></script>
</head>
<body>

<table id="layout-table" cellspacing="0">
    <tr>
        <td id="menu-cell">
            <div id="menu">
                <ul>
                    <li><a href="#" onclick="displayContents('<c:url value="/user/profile-tabs.jsp"/>');">Profile</a></li>
                    <li><a href="#" onclick="displayContents('<c:url value="/user/list"/>');">List Users</a></li>
                    <li><a href="<c:url value="/logout"/>">Log Out</a></li>
                </ul>
            </div>
        </td>
        <td id="content-cell">
            <div id="content-body"></div>
        </td>
    </tr>
</table>

<script type="text/javascript">
    $(document).ready(function() {
        $('#content-body').load('<c:url value="/user/profile-tabs.jsp"/>');
    });
    function displayContents(page) {
        $('#content-body').load(page);
    }
</script>
</body>
</html>