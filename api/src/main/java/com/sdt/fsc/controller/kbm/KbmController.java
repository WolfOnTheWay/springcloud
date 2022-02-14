package com.sdt.fsc.controller.kbm;

import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.service.contract.kbm.IKbmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangxinhao
 * 知识库model controller
 */
@RestController
@RequestMapping("/api/v1/kbm")
//@Api(value="KBM-API",description = "知识库模型API")
public class KbmController {

    @Resource
    private IKbmService kbmServiceImpl;

    /**
     * 阈值异常检测模型 api
     * @param key
     * @param beginTime
     * @param endTime
     * @param page
     * @param pageSize
     * @param order
     * @param upperLimit
     * @param lowerLimit
     * @return
     */
    @GetMapping("/AnomalyDetectionThreshold")
//    @ApiOperation(value = "阈值异常检测模型")
    public BaseResponse AnomalyDetectionModelApi(String key, Long beginTime, Long endTime, Integer page, Integer pageSize, String order,
                         Integer upperLimit, Integer lowerLimit) throws Exception {
        return kbmServiceImpl.AnomalyDetectionModelApi(key, beginTime, endTime, page, pageSize, order, upperLimit, lowerLimit);
    }


}
