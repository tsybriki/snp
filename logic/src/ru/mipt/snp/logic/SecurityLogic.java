package ru.mipt.snp.logic;

import ru.mipt.snp.domain.UserDetailsImpl;

/**
 * <p>Logic interface used for operating with application security context</p>
 *
 * @author Maxim Galushka
 * @since 03/17/2009  16:18
 */
public interface SecurityLogic {

    /**
     * Retrieves user deatils implementation from application security context
     * @return user details or null if details don't exist in security context
     */
    UserDetailsImpl getUserDetailsFromContext();
}
