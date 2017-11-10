package service;

import entity.CTFProblem;

import java.util.List;

/**
 * Created by WaterMelon on 2017/11/8.
 */
public interface CTFProblemService {
    List<CTFProblem> getAllCTFProblems();
    List<CTFProblem> getCTFProblemsByName(String name);
    CTFProblem getCTFProblemByID(int id);
    void addCTFProblem(CTFProblem ctfProblem);
    void deleteCTFProblem(int id);
    void updateCTFProblem(CTFProblem ctfProblem);

}
