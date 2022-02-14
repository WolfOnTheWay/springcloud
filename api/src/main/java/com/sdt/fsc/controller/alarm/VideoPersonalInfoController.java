package com.sdt.fsc.controller.alarm;


import com.sdt.fsc.entity.alarm.VideoPersonalInfo;
import com.sdt.fsc.service.contract.alarm.IVideoPersonalInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aubin
 * @since 2021-06-21
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/video/person")
//@Api(description = "人员信息同步API")
public class VideoPersonalInfoController {

    @Resource
    private IVideoPersonalInfoService videoPersonalInfoService;

//    @ApiOperation(value = "查询所有人员信息")
    @GetMapping("/all")
    public Map<String, Object> getAllInfo() throws Exception {
        return videoPersonalInfoService.getAllInfo();
    }

//    @ApiOperation(value = "保存人员信息")
    @PostMapping("/save")
    public Boolean save(@RequestBody VideoPersonalInfo videoPersonalInfo) throws Exception {
        return videoPersonalInfoService.savePersonInfo(videoPersonalInfo);
    }
}

