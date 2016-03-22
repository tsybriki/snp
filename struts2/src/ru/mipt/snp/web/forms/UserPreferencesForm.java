package ru.mipt.snp.web.forms;

import ru.mipt.snp.domain.User;
import ru.mipt.snp.domain.GenderEnum;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * <p>User preferences form object</p>
 *
 * @author Maxim Galushka
 * @since 19.11.2009
 */
public class UserPreferencesForm implements Serializable{

    private static final long serialVersionUID = 2377999540810508262L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    // editable fields
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;

    // domain object
    private User user;

    // genders list required for building "gender" options list
    private static Map<String,String> gendersList = new HashMap<String,String>(2);
    static {
        gendersList.put(GenderEnum.MALE.getGenderString(), "Male");
        gendersList.put(GenderEnum.FEMALE.getGenderString(), "Female");
    }

    public UserPreferencesForm(User user) {
        this.user = user;
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthDate = formatValidDate(user.getBirthDate());
        this.gender = (user.getGender() != null) ? user.getGender().getGenderString() : null;
    }

    /**
     * @return user domain object build based on current form object state
     */
    public User getDomainUserObject(){
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(parseValidDate(birthDate));
        user.setGender(GenderEnum.getGenderByString(gender));
        return user;
    }

    public String getUserId() {
        return Integer.toString(user.getId());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<String,String> getGendersList() {
        return gendersList;
    }

    private static String formatValidDate(Date date){
        if(date == null){
            return "";
        }
        else{
            return dateFormat.format(date);
        }
    }

    private static Date parseValidDate(String dateString){
        Date result = null;
        try {
            result = dateFormat.parse(dateString);
        } catch (ParseException ex){
            //
        }
        return result;
    }
}
