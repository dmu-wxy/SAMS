package com.wxy.sams.util;

import com.wxy.sams.model.Manager;
import org.springframework.security.core.context.SecurityContextHolder;

public class ManagerUtils {
    public static Manager getCurrentManager(){
        return (Manager)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
