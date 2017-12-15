package controller;

import dto.Response;
import entity.Mail;
import entity.Mailbox;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.MailService;
import support.Authorization;
import support.CurrentUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("mail/")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }


    @GetMapping(value = "")
    @Authorization
    public Response listAccount(@CurrentUser User user) {
        return new Response<>(200, mailService.listAccount(user));
    }

    @PutMapping(value = "")
    @Authorization
    public Response addAccount(@CurrentUser User user, @RequestBody Mailbox mailbox) {
        mailbox.setUser(user);
        mailbox.setFolders(new ArrayList<>());
        return new Response<>(mailService.addAccount(mailbox) ? 200 : 500);
    }

    @PostMapping(value = "{boxId}")
    @Authorization
    public Response updateAccount(@PathVariable("boxId") Integer boxId, @CurrentUser User user, @RequestBody Mailbox mailbox) {
        return new Response<>(mailService.updateAccount(mailbox, boxId, user) ? 200 : 500);
    }

    @DeleteMapping(value = "{boxId}")
    @Authorization
    public Response deleteAccount(@PathVariable("boxId") Integer boxId, @CurrentUser User user) {
        return new Response<>(mailService.deleteAccount(boxId, user) ? 200 : 500);
    }

    @DeleteMapping(value = "")
    @Authorization
    public Response deleteAccounts(@RequestBody List<Integer> boxIds, @CurrentUser User user) {
        return new Response<>(mailService.deleteAccounts(boxIds, user) ? 200 : 500);
    }

    @GetMapping(value = "{boxId}")
    @Authorization
    public Response listFolderInBox(@PathVariable("boxId") Integer boxId, @CurrentUser User user) {
        return new Response<>(200, mailService.listFolder(boxId));
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