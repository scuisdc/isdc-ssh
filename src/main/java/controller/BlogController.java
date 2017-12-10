package controller;

import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BlogService;
import service.UserService;

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

    @RequestMapping(value = "post/my-post", method = RequestMethod.GET)
    public Response listMyPost(@CookieValue("accessToken") String accessToken) {
        UserResponse user = userService.auth(accessToken);
        if (user == null)
            return new Response(500, "获取个人博文失败");
        List<PostPreviewResponse> myPostList = blogService.getPostsByEmail(user.getEmail());
        return new Response<>(200, myPostList);
    }

    @RequestMapping(value = "post/his-post/{user_name}", method = RequestMethod.GET)
    public Response listHisPost(@PathVariable("user_name") String userName) {
        System.out.println(userName);
        List<PostPreviewResponse> hisPosts = blogService.getPostsByEmail(userName);
        if (hisPosts != null)
            return new Response<>(200, hisPosts);
        else
            return new Response<>(400, "获取指定用户博文失败");
    }

    @RequestMapping(value = "post/{post_id}", method = RequestMethod.GET)
    public Response getPostById(@PathVariable("post_id") int postId) {
        PostResponse post = blogService.getFullPostById(postId);
        if (post == null)
            return new Response<>(500, "没有这篇博文");
        return new Response<>(200, post);
    }


    @RequestMapping(value = "post/{post_id}/comment", method = RequestMethod.GET)
    public Response getCommentByPostId(@PathVariable("post_id") int postId) {
        return new Response<>(200, blogService.getCommentByPost(postId));
    }

    @RequestMapping(value = "post/new-post", method = RequestMethod.POST)
    public Response newPost(@CookieValue("accessToken") String accessToken, @RequestBody() NewPostRequest request) {
        UserResponse user = userService.auth(accessToken);
        if (user == null)
            return new Response<>(500, "发送博文失败");
        blogService.save(user.getEmail(), request.getPreview(), request.getContent(), request.getTitle(), new Date(), new Date());
        return new Response<>(200);
    }

    @RequestMapping(value = "post/update-post", method = RequestMethod.POST)
    public Response updatePost(@CookieValue("accessToken") String accessToken, @RequestBody() UpdatePostRequest request) {
//        UserResponse user = userService.auth(accessToken);
//        PostResponse post = blogService.getFullPostById(request.getId());
//        if (post == null || !user.equals(post.getAuthor()))
//            return new Response<>(500, "更改博文失败");
//        post.setContent(request.getContent());
//        post.setPreview(request.getPreview());
//        post.setTitle(request.getTitle());
//        post.setLastModified(new Date());
//        blogService.updatePost(post);
        return new Response<>(200);
    }

    @RequestMapping(value = "post/{post_id}/comment", method = RequestMethod.POST)
    public Response newComment(@CookieValue(value = "accessToken") String accessToken, @PathVariable("post_id") int postId, @RequestBody() NewCommentRequest request) {
//        User user = userService.auth(accessToken);
//        Post post = blogService.getFullPostById(postId);
//        if (user != null && post != null) {
//            Comment comment = new Comment();
//            comment.setSender(user);
//            comment.setCommentDate(request.getCommentDate());
//            comment.setPost(post);
//            comment.setContent(request.getContent());
//            blogService.newComment(comment);
//            return new Response<>(200);
//        }
        return new Response<>(500, "评论失败");
    }

    @RequestMapping(value = "post/{post_id}", method = RequestMethod.DELETE)
    public Response delPost(@CookieValue("accessToken") String accessToken, @PathVariable("post_id") int postId) {
//        if (userService.auth(accessToken).equals(blogService.getFullPostById(postId).getAuthor())) {
//            blogService.delete(postId);
//            return new Response<>(200);
//        } else
        return new Response<>(500, "你不是这篇博文的作者，删除博文失败");
    }

    @RequestMapping(value = "post/{post_id}/comment/{comment_id}", method = RequestMethod.DELETE)
    public Response delComment(@CookieValue("accessToken") String accessToken, @PathVariable("post_id") int postId, @PathVariable("comment_id") int commentId) {
//        User user = userService.auth(accessToken);
//        User postAuthor = blogService.getFullPostById(postId).getAuthor();
//        User commentSender = blogService.getCommentById(commentId).getSender();
//        if ((user.equals(postAuthor) || user.equals(commentSender)) && blogService.deleteComment(commentId))
//            return new Response<>(200);
//        else
        return new Response<>(500, "删除评论失败");
    }
}