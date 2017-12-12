package service;

import dto.CTFProblemResponse;
import entity.CTFProblem;

import java.util.List;

/**
 * Created by WaterMelon on 2017/11/8.
 */
public interface CTFProblemService {
    List<CTFProblemResponse> getAllCTFProblems();

    CTFProblemResponse getCTFProblemByID(int id);

    void addCTFProblem(CTFProblem ctfProblem);

    void deleteCTFProblem(int id);

    void updateCTFProblem(CTFProblem ctfProblem);
}
