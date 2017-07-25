package Service;

import Entity.Category;
import Entity.Comment;
import Entity.Post;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
public interface BlogService {
    List<Post> listPost(int page, int pageSize);

    void newPost(Post post);

    Post getPostById(int postId);

    boolean deletePost(int postId);

    List<Comment> getCommentByPost(int postId);

    void newComment(Comment comment);

    boolean deleteComment(int commentId);

    List<Category> listCategory();

    Category getCategoryById(int categoryId);

    List<Post> listPostByCategory(int page, int pageSize, int categoryId);
}
