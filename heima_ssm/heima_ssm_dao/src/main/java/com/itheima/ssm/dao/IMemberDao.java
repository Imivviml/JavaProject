/**
 * FileName: IMemberDao
 * Author:   #include
 * Date:     2019/10/17 18:21
 * Description:
 */
package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 */
@Repository("iMemberDao")
public interface IMemberDao {

    @Select("select * from member where id = #{id}")
    Member findById(@Param("id") String id) throws Exception;
}
