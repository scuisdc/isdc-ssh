package DAO;

import Entity.Post;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
public interface PostDAO {
    Post getPostById(int postId);

    List<Post> getAllPost(int page, int pageSize);

    void addPost(Post post);

    boolean delPost(int postId);

    void updatePost(Post post);
}
