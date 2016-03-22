<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url var="viewProfileUrl" value="/user/view">
    <s:param name="id" value="%{userId}"/>
</s:url>
<s:url var="viewTrainingsUrl" value="/user/list-trainings">
    <s:param name="id" value="%{userId}"/>
</s:url>
<div id="tabs">
   <ul>
      <li><a href="${viewProfileUrl}">Edit Profile</a></li>
      <li><a href="${viewTrainingsUrl}">User Trainings</a></li>
   </ul>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $("#tabs").tabs();
    });
</script>
