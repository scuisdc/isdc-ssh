package dao;

import entity.CTFFlagGetter;
import entity.CTFProblem;

import java.util.List;

/**
 * Created by WaterMelon on 2017/11/8.
 */
public interface CTFProblemDAO {
        List<CTFProblem> getAllCTFProblems();
        List<CTFProblem> getCTFProblemsByName(String name);
        List<CTFFlagGetter> getAllCTFFlagGetter();
        List<CTFFlagGetter> getCTFFlagGetterByID(int id);

        CTFProblem getCTFProblemByID(int id);
        boolean deleteCTFProblem(int id);
        void updateCTFProblem(CTFProblem ctfProblem);
        void addCTFProblem(CTFProblem ctfProblem);
        void addCTFFlagGetter(CTFFlagGetter ctfFlagGetter);
        void deleteCTFFlagGetter(int id,String userEmail);

}
