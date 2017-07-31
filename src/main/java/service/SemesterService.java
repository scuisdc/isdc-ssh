package service;

import entity.Semester;

import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
public interface SemesterService {


    List<Semester> listAll();

    void addSemester(Semester semester);

    void updateSemester(Semester semester);

    boolean deleteSemester(Semester semester);

    Semester findSemesterByDate(Date date);
}
