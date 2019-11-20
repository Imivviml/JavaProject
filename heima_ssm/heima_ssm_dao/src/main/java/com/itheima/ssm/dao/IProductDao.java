package com.itheima.ssm.dao;

import java.util.List;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("iProductDao")
public interface IProductDao {

    @Select("select * from product where id = #{id}")
    Product findById(@Param("id") String id) throws Exception;

    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
