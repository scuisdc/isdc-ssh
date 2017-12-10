package service;

import dto.CommentResponse;
import dto.PostPreviewResponse;
import dto.PostResponse;
import entity.Post;
import entity.User;

import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
public interface BlogService {
    List<PostPreviewResponse> listPost();

    PostPreviewResponse getPostById(int postId);

    PostResponse getFullPostById(int postId);

    List<PostPreviewResponse> getPostsByEmail(String userName);

    boolean delete(Integer userId, Integer postId);

    List<CommentResponse> getCommentByPost(int postId);

    boolean deleteComment(Integer userId, Integer postId, Integer commentId);

    void updatePost(Post post);

    CommentResponse getCommentById(int commentId);

    void save(String email, String preview, String content, String title, Date created, Date lastModified);

    boolean updatePost(Integer userId, Integer postId, String content, String preview, String title, Date lastModified);

    void newComment(User user, int postId, Date commentDate, String content);
}
