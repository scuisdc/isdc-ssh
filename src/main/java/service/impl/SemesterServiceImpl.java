package service.impl;

import dao.SemesterDAO;
import entity.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SemesterService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
@Service("SemesterService")
@Transactional
public class SemesterServiceImpl implements SemesterService {

    private final SemesterDAO semesterDAO;

    @Autowired
    public SemesterServiceImpl(SemesterDAO semesterDAO) {
        this.semesterDAO = semesterDAO;
    }

    @Override
    public List<Semester> listAll() {
        return semesterDAO.findAll();
    }

    @Override
    public void addSemester(Semester semester) {
        semesterDAO.save(semester);
    }

    @Override
    public void updateSemester(Semester semester) {
        semesterDAO.update(semester);
    }

    @Override
    public void deleteSemester(Semester semester) {
        semesterDAO.delete(semester);
    }

    @Override
    public Semester findSemesterByDate(Date date) {
        return semesterDAO.findSemesterByDate(date);
    }
}
