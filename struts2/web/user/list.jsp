<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table id="users-list">
</table>

<script type="text/javascript">
    $('#users-list').jqGrid({
        url: '<c:url value="/user-json/list"/>',
        mtype: 'POST',
        datatype: 'json',
        jsonReader: {
            root: 'users',
            repeatitems: false,
            cell: ''
        },
        colNames:['First Name','Last Name', 'Gender', 'Birth Date'],
        colModel:[
            {name:'firstName', index:'firstName', width:100, edittype:'select',
             formatter:editUserLinkFormatter, formatoptions:{url:'<c:url value="/user/tabs"/>'}, sorttype:"text"},
            {name:'lastName', index:'lastName', width:100, align:"right", sorttype:"text"},
            {name:'gender', index:'gender', width:80, align:"right", sorttype:"text"},
            {name:'birthDate', index:'birthDate', width:100, align:"right", sorttype:"text"}
        ],
        multiselect: true,
        loadonce: true,
        caption: "List of Users"
    });

</script>