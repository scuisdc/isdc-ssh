package dao;

import entity.CTFProblem;

import java.util.List;

/**
 * Created by WaterMelon on 2017/11/8.
 */
public interface CTFProblemDAO {
        List<CTFProblem> getAllCTFProblems();
        List<CTFProblem> getCTFProblemsByName(String name);
        CTFProblem getCTFProblemByID(int id);
        void deleteCTFProblem(int id);
        void updateCTFProblem(CTFProblem ctfProblem);
        void addCTFProblem(CTFProblem ctfProblem);
}
