package com.baizhi.service;

import com.baizhi.dao.MasterDao;
import com.baizhi.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MasterServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/23  8:40
 */
@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterDao masterDao;

    @Override
    public List<Master> showAllMaster() {
        List<Master> masters = masterDao.selectAll();
        return masters;
    }
}
