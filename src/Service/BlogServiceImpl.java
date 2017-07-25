package Service;

import DAO.CategoryDAO;
import DAO.CommentDAO;
import DAO.PostDAO;
import Entity.Category;
import Entity.Comment;
import Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Service("BlogService")
@Transactional
public class BlogServiceImpl implements BlogService {


    private final CommentDAO commentDAO;
    private final PostDAO postDAO;
    private final CategoryDAO categoryDAO;

    @Autowired
    public BlogServiceImpl(CommentDAO commentDAO, PostDAO postDAO, CategoryDAO categoryDAO) {
        this.commentDAO = commentDAO;
        this.postDAO = postDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Post> listPost(int page, int pageSize) {
        return postDAO.getAllPost(page, pageSize);
    }

    @Override
    public void newPost(Post post) {
        postDAO.addPost(post);
    }

    @Override
    public Post getPostById(int postId) {
        return postDAO.getPostById(postId);
    }

    @Override
    public boolean deletePost(int postId) {
        return postDAO.delPost(postId);
    }

    @Override
    public List<Comment> getCommentByPost(int postId) {
        return postDAO.getPostById(postId).getComments();
    }

    @Override
    public void newComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    @Override
    public boolean deleteComment(int commentId) {
        return commentDAO.delComment(commentId);
    }

    @Override
    public List<Category> listCategory() {
        return categoryDAO.getAllCategory();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    @Override
    public List<Post> listPostByCategory(int page, int pageSize, int categoryId) {
        Category categoryById = getCategoryById(categoryId);
        if (categoryById != null) {
            return categoryById.getPost().subList((page - 1) * pageSize, page * pageSize);
        } else {
            return new ArrayList<>();
        }
    }


}
