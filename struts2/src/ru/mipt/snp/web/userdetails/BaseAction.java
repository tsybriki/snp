package ru.mipt.snp.web.userdetails;

import ru.mipt.snp.web.common.WebConstants;

/**
 * <p></p>
 *
 * @author Kirill Tsibriy
 * @since 11.05.2009
 */
public class BaseAction {
    private final String name = "Action";

    public String execute() {
        return WebConstants.SUCCESS;
    }
    
    public String getName() {
        return name;
    }
}
