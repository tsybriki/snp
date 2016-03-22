package ru.mipt.snp.domain;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>Represents gender enumeration</p>
 *
 * @author Maxim Galushka
 * @since 19.11.2009
 */
public enum GenderEnum {

    MALE("M"),
    FEMALE("F");

    private static Map<String, GenderEnum> genderMap = new HashMap<String, GenderEnum>();

    static{
        for(GenderEnum gender : values()){
            genderMap.put(gender.getGenderString(), gender);
        }
    }

    private String genderString;

    GenderEnum(String genderString) {
        this.genderString = genderString;
    }

    public String getGenderString() {
        return genderString;
    }

    public static GenderEnum getGenderByString(String genderString){
        return genderMap.get(genderString);
    }
}
