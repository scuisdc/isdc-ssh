package service.impl;

import dao.CTFProblemDAO;
import dto.CTFProblemResponse;
import entity.CTFProblem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CTFProblemService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WaterMelon on 2017/11/8.
 */
@Service(value = "CTFProblemService")
@Transactional
public class CTFProblemServiceImpl implements CTFProblemService {
    private final CTFProblemDAO ctfProblemDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public CTFProblemServiceImpl(CTFProblemDAO ctfProblemDAO, ModelMapper modelMapper) {
        this.ctfProblemDAO = ctfProblemDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CTFProblemResponse> getAllCTFProblems() {
        return ctfProblemDAO.findAll().stream().map(p -> modelMapper.map(p, CTFProblemResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CTFProblemResponse getCTFProblemByID(int id) {
        return modelMapper.map(ctfProblemDAO.findOne(id), CTFProblemResponse.class);
    }

    @Override
    public void addCTFProblem(CTFProblem ctfProblem) {
        ctfProblemDAO.save(ctfProblem);
    }

    @Override
    public void deleteCTFProblem(int id) {
        ctfProblemDAO.deleteById(id);
    }

    @Override
    public void updateCTFProblem(CTFProblem ctfProblem) {
        ctfProblemDAO.update(ctfProblem);
    }
}
