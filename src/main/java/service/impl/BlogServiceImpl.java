package service.impl;

import dao.CommentDAO;
import dao.PostDAO;
import entity.Comment;
import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BlogService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Service("BlogService")
@Transactional
public class BlogServiceImpl implements BlogService {


    private final CommentDAO commentDAO;
    private final PostDAO postDAO;

    @Autowired
    public BlogServiceImpl(CommentDAO commentDAO, PostDAO postDAO) {
        this.commentDAO = commentDAO;
        this.postDAO = postDAO;
    }

    @Override
    public List<Map> listPost(int page, int pageSize) {
        return postDAO.getAllPost(page, pageSize);
    }

    @Override
    public void newPost(Post post) {
        postDAO.addPost(post);
    }

    @Override
    public Map getPostById(int postId) {
        return postDAO.getPostById(postId);
    }

    @Override
    public Post getFullPostById(int postId) {
        return postDAO.getFullPostById(postId);
    }

    @Override
    public boolean deletePost(int postId) {
        return postDAO.delPost(postId);
    }

    @Override
    public List<Map> getCommentByPost(int postId) {
        return commentDAO.getCommentByPost(postId);
    }


    @Override
    public void newComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    @Override
    public boolean deleteComment(int commentId) {
        return commentDAO.delComment(commentId);
    }


}
