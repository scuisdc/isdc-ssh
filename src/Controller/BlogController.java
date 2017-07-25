package Controller;

import DTO.Response;
import Service.BlogService;
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

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @RequestMapping(value = "post", method = RequestMethod.GET)
    public Response listPost(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "page", defaultValue = "5") int pageSize) {
        return new Response<>(200, blogService.listPost(page, pageSize));
    }

    @RequestMapping(value = "category/{category}", method = RequestMethod.GET)
    public Response listPostByCategory(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @PathVariable("category") int categoryId) {
        return new Response<>(200, blogService.listPostByCategory(page, pageSize, categoryId));
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public Response listCategory() {
        return new Response<>(200, blogService.listCategory());
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
