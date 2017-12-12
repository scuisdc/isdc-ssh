package controller;

import dto.*;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BlogService;
import service.UserService;
import support.Authorization;
import support.Constants;
import support.CurrentUser;

import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("blog/")
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;

    @Autowired
    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }


    @RequestMapping(value = "post", method = RequestMethod.GET)
    public Response listPost(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "page", defaultValue = "5") int pageSize) {
        return new Response<>(200, blogService.listPost());
    }

    @RequestMapping(value = "{userName}", method = RequestMethod.GET)
    public Response listMyPost(@PathVariable("userName") String userName) {
        List<PostPreviewResponse> myPostList = blogService.getPostsByUserName(userName);
        return new Response<>(200, myPostList);
    }

    @RequestMapping(value = "post/{post_id}", method = RequestMethod.GET)
    public Response getPostById(@PathVariable("post_id") int postId) {
        PostResponse post = blogService.getFullPostById(postId);
        if (post == null)
            return new Response<>(500, "Wrong post Id");
        return new Response<>(200, post);
    }


    @RequestMapping(value = "post/{post_id}/comment", method = RequestMethod.GET)
    public Response getCommentByPostId(@PathVariable("post_id") int postId) {
        return new Response<>(200, blogService.getCommentByPost(postId));
    }

    @PutMapping(value = "post")
    @Authorization
    public Response newPost(@CurrentUser User user, @RequestBody NewPostRequest request) {
        blogService.save(user.getEmail(), request.getPreview(), request.getContent(), request.getTitle(), new Date(), new Date());
        return new Response<>(200);
    }

    @PostMapping(value = "post/{post_id}")
    @Authorization
    public Response updatePost(@RequestAttribute(Constants.HEADER_USER_ID) Integer userId, @RequestBody UpdatePostRequest request) {

        boolean flag = blogService.updatePost(userId, request.getId(), request.getContent(), request.getPreview(), request.getTitle(), new Date());
        return new Response<>(flag ? 200 : 403);
    }

    @PutMapping(value = "post/{post_id}/comment")
    @Authorization
    public Response newComment(@CurrentUser User user, @PathVariable("post_id") int postId, @RequestBody() NewCommentRequest request) {
        blogService.newComment(user, postId, request.getCommentDate(), request.getContent());
        return new Response<>(200);
    }

    @DeleteMapping(value = "post/{post_id}")
    @Authorization
    public Response delPost(@RequestAttribute(Constants.HEADER_USER_ID) Integer userId, @PathVariable("post_id") int postId) {

        boolean flag = blogService.delete(userId, postId);
        return new Response<>(flag ? 200 : 403);
    }

    @DeleteMapping(value = "post/{post_id}/comment/{comment_id}")
    @Authorization
    public Response delComment(@RequestAttribute(Constants.HEADER_USER_ID) Integer userId, @PathVariable("post_id") int postId, @PathVariable("comment_id") int commentId) {
        boolean flag = blogService.deleteComment(userId, postId, commentId);
        return new Response<>(flag ? 200 : 403);
    }
}