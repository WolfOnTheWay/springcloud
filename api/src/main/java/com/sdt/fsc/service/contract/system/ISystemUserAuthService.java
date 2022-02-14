package com.sdt.fsc.service.contract.system;



import java.util.List;

/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/10/15 8:42
 */
public interface ISystemUserAuthService {
    /**
     * 获取权限列表
     *
     * @return
     *//*
    List<SystemAuthDictDO> getAuths();
*/
    /**
     * 添加用户权限，全量添加。添加时先将该用户原有权限删除。
     *
     * @param systemUserAuthParamDTO
     * @return
     * @throws Exception
     */
  /*  boolean addUserAuth(SystemUserAuthParamDTO systemUserAuthParamDTO) throws Exception;*/

    /**
     * 根据用户id删除用户权限
     *
     * @param userIds
     * @return
     * @throws Exception
     */
    int delUserAuth(List<Integer> userIds) throws Exception;
}
