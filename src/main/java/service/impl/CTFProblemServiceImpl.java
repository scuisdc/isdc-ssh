package service.impl;

import entity.CTFProblem;
import org.springframework.stereotype.Service;
import service.CTFProblemService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by WaterMelon on 2017/11/8.
 */
@Service(value = "CTFProblemService")
@Transactional
public class CTFProblemServiceImpl implements CTFProblemService {
    @Override
    public List<CTFProblem> getAllCTFProblems() {
        return null;
    }

    @Override
    public List<CTFProblem> getCTFProblemsByName(String name) {
        return null;
    }

    @Override
    public void addCTFProblem(CTFProblem ctfProblem) {

    }

    @Override
    public void deleteCTFProblem(CTFProblem ctfProblem) {

    }

    @Override
    public void updateCTFProblem(CTFProblem ctfProblem) {

    }
}
