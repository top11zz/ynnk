package com.tgem.ynnk.controller;

import com.tgem.ynnk.response.Result;
import com.tgem.ynnk.response.ResultCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ErrorController {

    //公共错误跳转
    @RequestMapping(value="autherror")
    public Result autherror(int code) {
        return code ==1?new Result(ResultCode.UNAUTHENTICATED):new Result(ResultCode.UNAUTHORISE);
    }

}
