package service;

import entity.CTFFlagGetter;
import entity.CTFProblem;

import java.util.List;

/**
 * Created by WaterMelon on 2017/11/8.
 */
public interface CTFProblemService {
    List<CTFProblem> getAllCTFProblems();

    List<CTFProblem> getCTFProblemsByName(String name);

    List<CTFFlagGetter> getAllCTFFlagGetter();

    List<CTFFlagGetter> getCTFFlagGetterByID(int id);

    CTFProblem getCTFProblemByID(int id);

    void addCTFProblem(CTFProblem ctfProblem);

    boolean deleteCTFProblem(int id);

    void updateCTFProblem(CTFProblem ctfProblem);

    void addCTFFlagGetter(CTFFlagGetter ctfFlagGetter);
}
