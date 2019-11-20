/**
 * FileName: IOrdersDao
 * Author:   #include
 * Date:     2019/10/10 17:02
 * Description:
 */
package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository("iOrdersDao")
public interface IOrdersDao {


    /*
        多对一关系查询，一个订单只能属于一个产品，而一个产品可以有多个订单
        在Java中处理复杂的关系映射的时候，多对一关系可以拆分成一对一关系
     */
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),//主键
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itheima.ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;


    /**
     * 多表操作
     * 查询出查询订单的详细信息
     * @param ordersId
     * @return
     */
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),//主键
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(@Param("ordersId") String ordersId) throws Exception;
}
