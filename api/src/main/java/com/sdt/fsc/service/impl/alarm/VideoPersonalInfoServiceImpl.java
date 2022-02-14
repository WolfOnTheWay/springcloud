package com.sdt.fsc.service.impl.alarm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdt.fsc.entity.alarm.VideoPersonalInfo;
import com.sdt.fsc.entity.search.Bool;
import com.sdt.fsc.mapper.dm.VideoPersonalInfoMapper;
import com.sdt.fsc.service.contract.alarm.IVideoPersonalInfoService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aubin
 * @since 2021-06-21
 */
@Service
@Primary
public class VideoPersonalInfoServiceImpl extends ServiceImpl<VideoPersonalInfoMapper, VideoPersonalInfo> implements IVideoPersonalInfoService {

    @Override
    public Map<String, Object> getAllInfo() throws Exception {
       Map<String,Object> map = new HashMap<>();
        QueryWrapper<VideoPersonalInfo> queryWrapper = new QueryWrapper<>();
        List<VideoPersonalInfo> personalInfos = this.baseMapper.selectList(queryWrapper);
        map.put("persons", personalInfos);
        return map;
    }

    @Override
    public Boolean savePersonInfo(VideoPersonalInfo videoPersonalInfo) throws Exception {
        List<VideoPersonalInfo> videoPersonalInfos = (List<VideoPersonalInfo>) this.getAllInfo().get("persons");
        for (VideoPersonalInfo v : videoPersonalInfos) {
            if (v.getUserId().equals(videoPersonalInfo.getUserId())) {
                return this.baseMapper.updateById(videoPersonalInfo)==1;
            }
        }
        return this.baseMapper.insert(videoPersonalInfo)==1;
    }
}
