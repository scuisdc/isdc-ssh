package controller;

import dto.CTFAnswerRequest;
import dto.CTFProblemRequest;
import dto.Response;
import entity.CTFProblem;
import entity.User;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CTFProblemService;
import service.UserService;

import java.util.Date;

/**
 * Created by WaterMelon on 2017/11/8.
 */
@RestController
@RequestMapping("ctf/")
public class CTFController {
    private final CTFProblemService ctfProblemService;
    private final UserService userService;
    @Autowired
    public CTFController(CTFProblemService ctfProblemService, UserService userService) {
        this.ctfProblemService = ctfProblemService;
        this.userService = userService;
    }

    @PutMapping(value = "List")
    public Response addCTFProblem(@RequestBody CTFProblemRequest ctfProblemRequest){
        User questionMaker = userService.auth(ctfProblemRequest.getUserAccessToken());
        if (questionMaker!=null) {
            CTFProblem ctfProblem = new CTFProblem(ctfProblemRequest.getTitle(), ctfProblemRequest.getData(), new Date(), questionMaker.getUserName(),ctfProblemRequest.getFlag());
            ctfProblemService.addCTFProblem(ctfProblem);
            return new Response<>(200,"添加成功");
        }
       return new Response<>(200,"添加失败，请登录后重试");
    }
    @GetMapping(value = "List")
    public Response getCTFProblems(){
        return new Response<>(200,ctfProblemService.getAllCTFProblems());
    }

    @DeleteMapping(value = "List")
    public Response delCTFProblem(@RequestBody CTFProblemRequest ctfProblemRequest){
        User questionMaker = userService.auth(ctfProblemRequest.getUserAccessToken());
        CTFProblem ctfProblem= ctfProblemService.getCTFProblemByID(ctfProblemRequest.getId());
        if (questionMaker.getEmail().equals(ctfProblem.getUserEmail())){
            ctfProblemService.deleteCTFProblem(ctfProblem.getId());
            return new Response<>(200,"删除成功");
        }
        else return new Response<>(200,"删除失败");
    }
    @PostMapping(value = "List")
    public Response updateCTFProblem(@RequestBody CTFProblemRequest ctfProblemRequest){
        User questionMaker = userService.auth(ctfProblemRequest.getUserAccessToken());
        CTFProblem ctfProblem= ctfProblemService.getCTFProblemByID(ctfProblemRequest.getId());
        if (questionMaker.getEmail().equals(ctfProblem.getUserEmail())){
             ctfProblem.setTitle(ctfProblemRequest.getTitle());
             ctfProblem.setData(ctfProblemRequest.getData());
             ctfProblem.setFlag(ctfProblemRequest.getFlag());
             ctfProblemService.updateCTFProblem(ctfProblem);
            return new Response<>(200,"更新成功");
        }
        else return new Response<>(200,"更新失败");
    }
    @GetMapping(value = "answer")
    public Response submitAnswer(@RequestBody CTFAnswerRequest ctfAnswerRequest){
        CTFProblem ctfProblem = ctfProblemService.getCTFProblemByID(ctfAnswerRequest.getProblemID());
        if(ctfProblem.getFlag().equals(ctfAnswerRequest.getFlag())){
            ctfProblem.getFlagGetters().add(ctfAnswerRequest.getUserEmail());
            ctfProblemService.updateCTFProblem(ctfProblem);
            return new Response(200,true);
        }
        else return new Response(200,"答案错误");
    }


}

