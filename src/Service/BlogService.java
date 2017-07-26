package Service;

import Entity.Comment;
import Entity.Post;

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

    boolean deletePost(int postId);

    List<Map> getCommentByPost(int postId);

    void newComment(Comment comment);

    boolean deleteComment(int commentId);
}
