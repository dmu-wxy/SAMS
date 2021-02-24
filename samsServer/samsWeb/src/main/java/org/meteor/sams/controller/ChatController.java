package org.meteor.sams.controller;

import org.meteor.sams.model.Manager;
import org.meteor.sams.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ManagerService managerService;

    @GetMapping("/managers")
    public List<Manager> getAllManagers(){
        return managerService.getAllManagersExceptCurrentManager();
    }
}
