package service;

import dto.CommentResponse;
import dto.PostPreviewResponse;
import dto.PostResponse;
import entity.Comment;
import entity.Post;

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

    void delete(int postId);

    List<CommentResponse> getCommentByPost(int postId);

    void newComment(Comment comment);

    void deleteComment(int commentId);

    void updatePost(Post post);

    CommentResponse getCommentById(int commentId);

    void save(String email, String preview, String content, String title, Date created, Date lastModified);
}
