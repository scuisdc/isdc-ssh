package DAO;

import Entity.Semester;

import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
public interface SemesterDAO {

    List<Semester> listAllSemester();

    void addSemester(Semester semester);

    void updateSemester(Semester semester);

    boolean deleteSemester(Semester semester);

    Semester findSemesterByDate(Date date);
}
