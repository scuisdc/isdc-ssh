package DAO;

import Entity.Post;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
public interface PostDAO {
    Map getPostById(int postId);

    List<Map> getAllPost(int page, int pageSize);

    void addPost(Post post);

    boolean delPost(int postId);

    void updatePost(Post post);

    Post getFullPostById(int postId);
}
