/**
 * FileName: ProductServiceImpl
 * Author:   #include
 * Date:     2019/10/7 21:25
 * Description:
 */
package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service("IProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception{
        productDao.save(product);
    }
}
