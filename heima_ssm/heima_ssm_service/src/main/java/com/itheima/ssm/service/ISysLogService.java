package com.itheima.ssm.service;


import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    /**
     * 用来保存页面访问信息的操作
     * @param sysLog
     * @throws Exception
     */
    public void save(SysLog sysLog) throws Exception;


    /**
     * 用来查询页面访问的操作
     * @param page
     * @param size
     * @return
     */
    List<SysLog> findAll(Integer page, Integer size) throws Exception;
}
