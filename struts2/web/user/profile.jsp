<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="message">&#32;</div>
<s:form id="preferencesFormId" action="save">
    <table cellpadding="0" cellspacing="2" border="0">
        <tr>
            <td>First name:</td>
            <td><s:textfield id="firstNameId" name="user.firstName" tabindex="1"/></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><s:textfield id="lastNameId" name="user.lastName" tabindex="2"/></td>
        </tr>
        <tr>
            <td>Birth date:</td>
            <td><s:textfield id="birthDateId" name="user.birthDate" tabindex="3"/></td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td>
                <s:radio name="user.gender" list="user.gendersList" tabindex="4"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><s:submit value="Save" id="submitButtonId" tabindex="5"/></td>
        </tr>
    </table>
</s:form>
<script type="text/javascript">
    $("#submitButtonId").bind("click", function(e){
        $.post("<c:url value="/user/save"/>", $("#preferencesFormId").serialize(),
                function(data){
                    //alert(data.substr(0, 200));
                    var resultJson = $.json.decode(data);
                    var messageDiv = $("#message");

                    if(resultJson.saveResult.errorMessage != undefined){
                        // TODO: implement shared component for such purposes???
                        var errorDialog = messageDiv.dialog({
                                autoOpen: false, title: "Exception occured", modal: true, width: "90%",
                                onClose: function(){
                                        messageDiv.html("");
                                    }});
                        messageDiv.html(resultJson.saveResult.errorMessage);
                        errorDialog.dialog('open');
                    }
                    else{
                        messageDiv.html(resultJson.saveResult.message)
                            .fadeIn('slow')
                            .animate({opacity: 1.0}, 1000)
                            .fadeOut('slow', function() {
                            messageDiv.html("");
                            //$("#submitButtonId").removeAttr("disabled");
                        });
                    }
              });
        //$("#submitButtonId").attr("disabled", true);
        e.preventDefault();
    });
    $("#birthDateId").datepicker({changeMonth: true, changeYear: true, yearRange: '-70:0', firstDay: 1});

</script>

