package dao;

import entity.Comment;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
public interface CommentDAO extends IGenericDao<Comment> {

    List<Comment> getCommentByPost(int postId);

}
