<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<input type="button" id="bbb" value="New training"/>

<table id="trainings-list">
</table>

<s:url var="viewTrainingsUrl" value="/user-json/list-trainings">
    <s:param name="id" value="%{userId}" />
</s:url>
<script type="text/javascript">
    $('#trainings-list').jqGrid({
        url: '${viewTrainingsUrl}',
        mtype: 'POST',
        datatype: 'json',
        jsonReader: {
            root: 'trainings',
            repeatitems: false,
            cell: ''
        },
        colNames:['ID', 'Title','Description'],
        colModel:[
            {name:'id', index:'id', width:40, edittype:'select', align: "right", sorttype:"int"},
            {name:'name', index:'name', width:100, edittype:'select', align: "right"},
            {name:'description', index:'description', width:200, align:"right"}
        ],
        multiselect: false,
        loadonce: true,
        caption: "Trainings"
    });

    $('#bbb').click(function() {
        $.newWindow({
            title: 'Test',
            height: 200,
            width: 260,
            content: '',
            type: 'normal',
            modal: true
        });
    });
</script>