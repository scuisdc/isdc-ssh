package dao;

import entity.Semester;

import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
public interface SemesterDAO extends IGenericDao<Semester> {
    Semester findSemesterByDate(Date date);
}
