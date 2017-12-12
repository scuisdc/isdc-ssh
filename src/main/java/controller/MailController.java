package controller;

import dto.Response;
import entity.Mail;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import support.Authorization;
import support.CurrentUser;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("mail/")
public class MailController {

    @Autowired
    public MailController() {
    }


    @GetMapping(value = "")
    @Authorization
    public Response listAccount(@CurrentUser User user) {
        return new Response<>(200);
    }

    @PutMapping(value = "")
    @Authorization
    public Response addAccount(@CurrentUser User user) {
        return new Response<>(200);
    }

    @PostMapping(value = "{boxId}")
    @Authorization
    public Response updateAccount(@PathVariable("boxId") Integer boxId, @CurrentUser User user) {
        return new Response<>(200);
    }

    @DeleteMapping(value = "{boxId}")
    @Authorization
    public Response deleteAccount(@PathVariable("boxId") Integer boxId, @CurrentUser User user) {
        return new Response<>(200);
    }

    @GetMapping(value = "{boxId}")
    @Authorization
    public Response listFolderInBox(@PathVariable("boxId") Integer boxId, @CurrentUser User user) {
        return new Response<>(200);
    }

    @GetMapping(value = "{boxId}/{folderId}")
    @Authorization
    public Response listMailsInFolder(@PathVariable("boxId") Integer boxId, @PathVariable("folderId") Integer folderId, @CurrentUser User user) {
        return new Response<>(200);
    }


    @GetMapping(value = "{boxId}/{folderId}/{mailId}")
    @Authorization
    public Response getMail(@PathVariable("mailId") Integer mailId, @CurrentUser User user) {
        return new Response<>(200);
    }


    @PutMapping(value = "{boxId}")
    @Authorization
    public Response sendMail(@PathVariable("boxId") Integer boxId, @CurrentUser User user, @RequestBody Mail mail) {
        return new Response<>(200);
    }


    @DeleteMapping(value = "{boxId}/{folderId}/{mailId}")
    @Authorization
    public Response deleteMail(@PathVariable("mailId") Integer mailId, @CurrentUser User user, @RequestBody Mail mail) {
        return new Response<>(200);
    }


}