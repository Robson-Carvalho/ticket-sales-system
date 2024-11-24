package com.uefs.system.mock;

import com.uefs.system.model.Event;
import com.uefs.system.model.User;
import com.uefs.system.service.EventService;
import com.uefs.system.service.UserService;

import java.util.Date;
import java.util.UUID;

public class Mock {
    public Mock(){
        UserService userService = new UserService();
        User u = userService.getByLogin("robsparta");

        if(u == null){
            User user = new User(UUID.fromString("e8b3ad30-fb22-46db-92a6-b8aeeefb239f"),
                    "robsparta",
                    "1234",
                    "Robson Carvalho",
                    "987654321",
                    "lia@example.com",
                    false);
            try {
                userService.create(user);
            } catch (Exception e) {
                System.err.println("Erro ao criar usuário: " + e.getMessage());
            }
        }

        EventService eventService = new EventService();
        int quantityEvents = eventService.getAll().size();

        if(quantityEvents == 0){
            Event event = new Event(
                    "Show de Rock",
                    "Ainda assim, existem dúvidas a respeito de como o surgimento do comércio virtual é uma das consequências das condições inegavelmente apropriadas.",
                    new Date()
            );

            try {
                eventService.create(event);
            } catch (Exception e) {
                System.err.println("Erro ao criar evento: " + e.getMessage());
            }
        }
    }
}
