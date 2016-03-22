package ru.mipt.snp.web.userdetails;

import ru.mipt.snp.logic.ProfileLogic;
import ru.mipt.snp.logic.SecurityLogic;
import ru.mipt.snp.web.common.WebConstants;
import ru.mipt.snp.web.common.ResponseStatus;
import ru.mipt.snp.web.forms.UserPreferencesForm;
import ru.mipt.snp.web.forms.SaveProfileResult;
import ru.mipt.snp.domain.UserDetailsImpl;
import ru.mipt.snp.domain.User;
import org.apache.log4j.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.io.StringWriter;
import java.io.PrintWriter;

import com.opensymphony.xwork2.Action;


public class UserProfileAction {

    private static final Logger log = Logger.getLogger(UserProfileAction.class);

    private ResourceBundleMessageSource resourceBundle;

    private ProfileLogic logic;
    private SecurityLogic securityLogic;

    private UserPreferencesForm user;

    private SaveProfileResult saveResult;

    private int userId;

    public String view() {
        if(userId == 0){
            UserDetailsImpl details = securityLogic.getUserDetailsFromContext();
            user = new UserPreferencesForm(details.getUser());
        }
        else{
            User domainUserObject = logic.getUserById(userId);
            if(domainUserObject == null){
                userId = 0;
                // TODO: send error message here
            }
            else{
                user = new UserPreferencesForm(domainUserObject);
            }
        }
        return WebConstants.SUCCESS;
    }

    public String tabs() {
        return WebConstants.SUCCESS;
    }

    /**
     * @return stream result for ajax request
     */
    public String save() {
        try{
            logic.updateUser(user.getDomainUserObject());
            buildSaveProfileResult(ResponseStatus.SUCCESS, getProperty(WebConstants.SUCCESS_MESSAGE_KEY), null);
            return Action.SUCCESS;
        } catch(Exception ex){
            log.error("Exception occured during user saving profile: ", ex);
            buildSaveProfileResult(ResponseStatus.FAILED, getProperty(WebConstants.FAILED_MESSAGE_KEY), ex);
            return Action.SUCCESS; // TODO: error???
        }
    }

    public UserPreferencesForm getUser() {
        return user;
    }

    public void setUser(UserPreferencesForm user) {
        this.user = user;
    }

    public int getUserId() {
        // display current user profile
        if(userId == 0){
            UserDetailsImpl details = securityLogic.getUserDetailsFromContext();
            userId = details.getUser().getId();
        }
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public SaveProfileResult getSaveResult() {
        return saveResult;
    }

    public void setSaveResult(SaveProfileResult saveResult) {
        this.saveResult = saveResult;
    }

    public void setLogic(ProfileLogic logic) {
        this.logic = logic;
    }

    public void setSecurityLogic(SecurityLogic securityLogic) {
        this.securityLogic = securityLogic;
    }

    public void setResourceBundle(ResourceBundleMessageSource resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    private String getProperty(String key){
        return resourceBundle.getMessage(key, null, "", Locale.US);
    }

    /**
     * TODO: create common error message displaying on the page
     * @param status
     * @param message
     * @param error
     */
    private void buildSaveProfileResult(ResponseStatus status, String message, Throwable error){
        if(error != null){
            StringWriter stackTraceWriter = new StringWriter();
            stackTraceWriter.write(error.toString());
            error.printStackTrace(new PrintWriter(stackTraceWriter));
            this.saveResult = new SaveProfileResult(status, message, stackTraceWriter.toString().replaceAll("\\\\", "\\\\\\\\"));
        }
        else{
            this.saveResult = new SaveProfileResult(status, message, null);
        }
    }

}
