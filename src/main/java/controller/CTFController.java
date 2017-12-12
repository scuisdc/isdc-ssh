package controller;

import dto.CTFAnswerRequest;
import dto.CTFProblemRequest;
import dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CTFProblemService;
import service.UserService;

import java.text.ParseException;

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

    @PutMapping(value = "form")
    public Response addCTFProblem(@CookieValue("accessToken") String accessToken, @RequestBody CTFProblemRequest ctfProblemRequest) throws ParseException {
//        User questionMaker = userService.auth(accessToken);
//        if (questionMaker != null) {
//            CTFProblem ctfProblem = new CTFProblem(ctfProblemRequest.getTitle(), ctfProblemRequest.getData(), formatDate(new Date()), questionMaker.getEmail(), ctfProblemRequest.getFlag(), ctfProblemRequest.getMagnet());
//            ctfProblem.setUserName(questionMaker.getUserName());
//            ctfProblemService.addCTFProblem(ctfProblem);
//            return new Response<>(200, "添加成功");
//        }
        return new Response<>(500, "添加失败，请登录后重试");
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public Response getCTFProblems() {
        return new Response<>(200, ctfProblemService.getAllCTFProblems());
    }

    @DeleteMapping(value = "form/{id}")
    public Response delCTFProblem(@CookieValue("accessToken") String accessToken, @PathVariable("id") int ctfProblemRequestId) {
//        User questionMaker = userService.auth(accessToken);
//        CTFProblem ctfProblem = ctfProblemService.getCTFProblemByID(ctfProblemRequestId);
//        if (questionMaker.getEmail().equals(ctfProblem.getUserEmail())) {
//
//            if (ctfProblemService.deleteCTFProblem(ctfProblem.getId()) == true) {
//
//                return new Response<>(200, "删除成功");
//
//            } else return new Response<>(500, "删除失败");
//
//
//        } else
        return new Response<>(500, "删除失败");
    }

    @PostMapping(value = "form")
    public Response updateCTFProblem(@CookieValue("accessToken") String accessToken, @RequestBody CTFProblemRequest ctfProblemRequest) {
//        User questionMaker = userService.auth(accessToken);
//        CTFProblem ctfProblem = ctfProblemService.getCTFProblemByID(ctfProblemRequest.getId());
//        if (questionMaker.getEmail().equals(ctfProblem.getUserEmail())) {
//            ctfProblem.setTitle(ctfProblemRequest.getTitle());
//            ctfProblem.setData(ctfProblemRequest.getData());
//            ctfProblem.setFlag(ctfProblemRequest.getFlag());
//            ctfProblem.setMagnet(ctfProblemRequest.getMagnet());
//            ctfProblemService.updateCTFProblem(ctfProblem);
//            return new Response<>(200, "更新成功");
//        } else
        return new Response<>(403);
    }

    @PostMapping(value = "answer")
    public Response submitAnswer(@CookieValue("accessToken") String accessToken, @RequestBody CTFAnswerRequest ctfAnswerRequest) throws ParseException {
//        CTFProblem ctfProblem = ctfProblemService.getCTFProblemByID(ctfAnswerRequest.getProblemID());
//        User user = userService.auth(accessToken);
//        if (user != null && ctfProblem.getFlag().equals(ctfAnswerRequest.getFlag())) {
//
//            CTFFlagGetter ctfFlagGetter = new CTFFlagGetter(ctfAnswerRequest.getProblemID(), ctfAnswerRequest.getUserEmail(), formatDate(new Date()));
//            ctfProblemService.addCTFFlagGetter(ctfFlagGetter);
//            return new Response(200, "答案正确");
//        } else
        return new Response(500, "答案错误");
    }

}

