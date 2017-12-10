package service.impl;

import dao.CommentDAO;
import dao.PostDAO;
import dao.UserDAO;
import dto.CommentResponse;
import dto.PostPreviewResponse;
import dto.PostResponse;
import entity.Comment;
import entity.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BlogService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Service("BlogService")
@Transactional
public class BlogServiceImpl implements BlogService {
    private final CommentDAO commentDAO;
    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public BlogServiceImpl(CommentDAO commentDAO, PostDAO postDAO, UserDAO userDAO, ModelMapper modelMapper) {
        this.commentDAO = commentDAO;
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostPreviewResponse> listPost() {
        return postDAO.findAll().stream().map(p -> modelMapper.map(p, PostPreviewResponse.class)).collect(Collectors.toList());
    }

    @Override
    public PostPreviewResponse getPostById(int postId) {
        return modelMapper.map(postDAO.findOne(postId), PostPreviewResponse.class);
    }

    @Override
    public PostResponse getFullPostById(int postId) {
        return modelMapper.map(postDAO.findOne(postId), PostResponse.class);
    }


    @Override
    public List<PostPreviewResponse> getPostsByEmail(String email) {
        return postDAO.getPostByEmail(email).stream().map(p -> modelMapper.map(p, PostPreviewResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(int postId) {
        postDAO.deleteById(postId);
    }

    @Override
    public List<CommentResponse> getCommentByPost(int postId) {
        return commentDAO.getCommentByPost(postId).stream().map(c -> modelMapper.map(c, CommentResponse.class)).collect(Collectors.toList());
    }


    @Override
    public void newComment(Comment comment) {
        commentDAO.save(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDAO.deleteById(commentId);
    }

    @Override
    public void updatePost(Post post) {
        postDAO.update(post);
    }

    @Override
    public CommentResponse getCommentById(int commentId) {
        return modelMapper.map(commentDAO.findOne(commentId), CommentResponse.class);
    }

    @Override
    public void save(String email, String preview, String content, String title, Date created, Date lastModified) {
        Post post = new Post();
        post.setContent(content);
        post.setLastModified(lastModified);
        post.setPreview(preview);
        post.setCreateDate(created);
        post.setAuthor(userDAO.getUserByEmail(email));
        post.setTitle(title);
        postDAO.save(post);
    }
}
