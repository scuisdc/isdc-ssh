package Controller;

import DTO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("blog/")
@CrossOrigin
public class BlogController {

    @Autowired
    public BlogController() {
    }


    @RequestMapping(value = "post", method = RequestMethod.GET)
    public Response listPost(@RequestParam(value = "page", defaultValue = "0") int page) {
        return new Response<>(200, "listPost");
    }

    @RequestMapping(value = "tag/{tag}", method = RequestMethod.GET)
    public Response listPostByTag(@RequestParam(value = "page", defaultValue = "0") int page, @PathVariable("tag") String tag) {
        return new Response<>(200, "listPostByTag");
    }

    @RequestMapping(value = "tag", method = RequestMethod.GET)
    public Response listTag() {
        return new Response<>(200, "listTag");
    }

    @RequestMapping(value = "post/{post_id}", method = RequestMethod.GET)
    public Response getPostById(@PathVariable("post_id") int postId) {
        return new Response<>(200, "getBlogById");
    }


    @RequestMapping(value = "post/{post_id}/comment", method = RequestMethod.GET)
    public Response getCommentByPostId(@PathVariable("post_id") int postId) {
        return new Response<>(200, "getCommentByPostId");
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public Response newPost(@CookieValue("accessToken") String accessToken) {
        return new Response<>(200, "new Post");
    }

    @RequestMapping(value = "post/{post_id}/comment", method = RequestMethod.POST)
    public Response newComment(@CookieValue("accessToken") String accessToken, @PathVariable("post_id") int id) {
        return new Response<>(200, "new comment");
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
