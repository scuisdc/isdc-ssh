package service;

import entity.Comment;
import entity.Post;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
public interface BlogService {
    List<Map> listPost(int page, int pageSize);

    void newPost(Post post);

    Map getPostById(int postId);

    Post getFullPostById(int postId);

    List<Map> getPostByUserAccessToken(String accessToken);

    List<Map> getPostByUserName(String userName);

    boolean deletePost(int postId);

    List<Map> getCommentByPost(int postId);

    void newComment(Comment comment);

    boolean deleteComment(int commentId);

    void updatePost(Post post);

    Comment getCommentById(int commentId);
}
