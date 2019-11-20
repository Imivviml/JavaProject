/**
 * FileName: IProductService
 * Author:   #include
 * Date:     2019/10/7 20:29
 * Description:
 */
package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 */
public interface IProductService {


     List<Product> findAll(int page,int size) throws Exception;

    void save(Product product) throws Exception;
}
