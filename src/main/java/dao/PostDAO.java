package dao;

import entity.Post;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
public interface PostDAO extends IGenericDao<Post> {
    List<Post> getPostByEmail(String userName);
}
