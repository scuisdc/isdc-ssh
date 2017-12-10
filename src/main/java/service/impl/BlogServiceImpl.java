package service.impl;

import dao.CommentDAO;
import dao.PostDAO;
import dao.UserDAO;
import dto.CommentResponse;
import dto.PostPreviewResponse;
import dto.PostResponse;
import entity.Comment;
import entity.Post;
import entity.User;
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
    public boolean delete(Integer userId, Integer postId) {
        Post one = postDAO.findOne(postId);
        if (one.getAuthor().getId().equals(userId)) {
            postDAO.delete(one);
            return true;
        }
        return false;
    }

    @Override
    public List<CommentResponse> getCommentByPost(int postId) {
        return commentDAO.getCommentByPost(postId).stream().map(c -> modelMapper.map(c, CommentResponse.class)).collect(Collectors.toList());
    }

    @Override
    public boolean deleteComment(Integer userId, Integer postId, Integer commentId) {
        Comment comment = commentDAO.findOne(commentId);
        Post post = postDAO.findOne(postId);
        if (comment != null && comment.getSender().getId().equals(userId) || post != null && post.getAuthor().getId().equals(userId)) {
            commentDAO.delete(comment);
            return true;
        }
        return false;
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

    @Override
    public boolean updatePost(Integer userId, Integer postId, String content, String preview, String title, Date lastModified) {
        Post post = postDAO.findOne(postId);
        if (post != null && post.getAuthor().getId().equals(userId)) {
            post.setContent(content);
            post.setPreview(preview);
            post.setTitle(title);
            post.setLastModified(lastModified);
            updatePost(post);
            return true;
        }
        return false;
    }

    @Override
    public void newComment(User user, int postId, Date commentDate, String content) {
        Comment comment = new Comment();
        comment.setCommentDate(commentDate);
        comment.setContent(content);
        comment.setSender(user);
        comment.setPost(postDAO.findOne(postId));
        commentDAO.save(comment);
    }
}
