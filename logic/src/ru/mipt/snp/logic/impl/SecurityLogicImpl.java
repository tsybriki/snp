package ru.mipt.snp.logic.impl;

import ru.mipt.snp.logic.SecurityLogic;
import ru.mipt.snp.domain.UserDetailsImpl;
import org.springframework.security.context.SecurityContextHolder;

/**
 * <p></p>
     *
     * @author Maxim Galushka
     * @since 03/17/2009  16:19
     */
    public class SecurityLogicImpl implements SecurityLogic {

        public UserDetailsImpl getUserDetailsFromContext() {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (obj instanceof UserDetailsImpl) {
                return (UserDetailsImpl) obj;
            }
            return null;
        }
    }
