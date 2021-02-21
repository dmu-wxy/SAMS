package org.meteor.sams.util;

import org.meteor.sams.model.Manager;
import org.springframework.security.core.context.SecurityContextHolder;

public class ManagerUtils {
    public static Manager getCurrentManager(){
        return (Manager)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
