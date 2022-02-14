package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 获取监控点回放取流URL消息体
 * @Datetime 2020/8/10 13:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraPlaybackUrlDTO {
    /**
     * 录像片段信息
     */
    private listContent[] list;
    /**
     * 分页标记 标记本次查询的全部标识符，用于查询分片时的多次查询
     */
    private String uuid;
    /**
     * 取流短url，注：rtsp的回放url后面要指定?playBackMode=1 在vlc上才能播放
     */
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class listContent{
        /**
         * 开始时间 录像片段的开始时间（IOS8601格式yyyy-MM-dd'T'HH:mm:ss.SSSzzz）
         */
        String beginTime;
        /**
         * 结束时间 录像片段的开始时间（IOS8601格式yyyy-MM-dd'T'HH:mm:ss.SSSzzz）
         */
        String endTime;
        /**
         * 录像片段大小 录像片段大小（单位：Byte）
         */
        Integer size;
    }
}
