package dao;

import entity.CTFProblem;

import java.util.List;

/**
 * Created by WaterMelon on 2017/11/8.
 */
public interface CTFProblemDAO {
        List<CTFProblem> getAllCTFProblems();
        List<CTFProblem> getCTFProblemsByName(String name);
        List<CTFProblem> getCTFProblemByID(int id);
        void deleteCTFProblem();
        void UpdateCTFProblem(CTFProblem ctfProblem);
        void addCTFProblem(CTFProblem ctfProblem);
}
