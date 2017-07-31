package controller;

import dto.NewCommentRequest;
import dto.Response;
import entity.Comment;
import entity.Post;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BlogService;
import service.UserService;

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
        return new Response<>(200, blogService.listPost(page, pageSize));
    }

    @RequestMapping(value = "post/{post_id}", method = RequestMethod.GET)
    public Response getPostById(@PathVariable("post_id") int postId) {
        return new Response<>(200, blogService.getPostById(postId));
    }


    @RequestMapping(value = "post/{post_id}/comment", method = RequestMethod.GET)
    public Response getCommentByPostId(@PathVariable("post_id") int postId) {
        return new Response<>(200, blogService.getCommentByPost(postId));
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public Response newPost(@CookieValue("accessToken") String accessToken) {
        return new Response<>(200, "new Post");
    }

    @RequestMapping(value = "post/{post_id}/comment", method = RequestMethod.POST)
    public Response newComment(@CookieValue(value = "accessToken") String accessToken, @PathVariable("post_id") int postId, @RequestBody() NewCommentRequest request) {
        User user = userService.auth(accessToken);
        Post post = blogService.getFullPostById(postId);
        if (user != null && post != null) {
            Comment comment = new Comment();
            comment.setSender(user);
            comment.setCommentDate(request.getCommentDate());
            comment.setPost(post);
            comment.setContent(request.getContent());
            blogService.newComment(comment);
            return new Response<>(200);
        }
        return new Response<>(500, "评论失败");
    }

    @RequestMapping(value = "post/{post_id}", method = RequestMethod.DELETE)
    public Response delPost(@CookieValue("accessToken") String accessToken, @PathVariable("post_id") int postId) {
        return new Response<>(200, "delete post");
    }

    @RequestMapping(value = "post/{post_id}/comment/{comment_id}", method = RequestMethod.DELETE)
    public Response delComment(@CookieValue("accessToken") String accessToken, @PathVariable("post_id") int postId, @PathVariable("comment_id") int commentId) {
        return new Response<>(200, "delete comment");
    }
}
