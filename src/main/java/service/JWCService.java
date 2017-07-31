package service;

import entity.ScoreRequest;

import java.io.IOException;
import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
public interface JWCService {
    void addRequest(ScoreRequest request);

    void getScore(ScoreRequest request) throws IOException;

    ScoreRequest findRequest(String zjh, String mm, Date date);
}
