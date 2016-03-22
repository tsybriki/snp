package ru.mipt.snp.dao.jdbc.mapping;

/**
 * <p>Column names for user table</p>
 *
 * Created by Kirill Tsibriy on 01.03.2009
 */
public final class UserMapping {
    private UserMapping() {}

    public static final String ID = "ID";
    public static final String LOGIN_ID = "login_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String PASSWORD = "password";
    public static final String BIRTH_DATE = "birth_date";
    public static final String GENDER = "gender";

    public static final String ROLE_ID = "role_id";
}
