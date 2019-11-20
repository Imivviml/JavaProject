/**
 * FileName: OrdersServiceImpl
 * Author:   #include
 * Date:     2019/10/10 17:01
 * Description:
 */
package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service("ordersServiceImpl")
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {

        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    /**
     * 查询出每一个orders对象，里面包含多表操作
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Override
    public Orders findAById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
