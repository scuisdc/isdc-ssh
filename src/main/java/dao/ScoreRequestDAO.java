package dao;

import entity.ScoreRequest;

import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
public interface ScoreRequestDAO extends IGenericDao<ScoreRequest> {
    ScoreRequest findRequest(String zjh, String mm, Date date);
}
