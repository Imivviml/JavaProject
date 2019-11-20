/**
 * FileName: ISysLogDao
 * Author:   #include
 * Date:     2019/10/26 13:18
 * Description:
 */
package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 保存日志信息的持久层接口
 */
@Repository("iSysLogDao")
public interface ISysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;


    @Select("select * from sysLog")
    List<SysLog> findAll() throws Exception;
}
