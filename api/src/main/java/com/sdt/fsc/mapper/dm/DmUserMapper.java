package com.sdt.fsc.mapper.dm;

import com.sdt.fsc.entity.User;
import org.apache.ibatis.annotations.Param;


public interface DmUserMapper {

        /**
         * 增加用户
         * @return
         */
        User queryDmUser(@Param("id") Integer id);

}
