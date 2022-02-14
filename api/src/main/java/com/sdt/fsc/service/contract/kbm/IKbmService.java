package com.sdt.fsc.service.contract.kbm;

import com.sdt.fsc.common.BaseResponse;

public interface IKbmService {
    public BaseResponse AnomalyDetectionModelApi(String key, Long beginTime, Long endTime, Integer page, Integer pageSize, String order,
                                                 Integer upperLimit, Integer lowerLimit) throws Exception;
}
