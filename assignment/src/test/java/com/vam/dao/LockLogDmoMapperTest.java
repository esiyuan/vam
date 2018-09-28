package com.vam.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vam.DaoConfig;
import com.vam.dmo.LockLogDmo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class LockLogDmoMapperTest {

    @Autowired
    private LockLogMapper lockLogMapper;

    @Test
    public void testInsert() {
        LockLogDmo lockLog = new LockLogDmo();
        lockLog.setCreateTime(new Date());
        lockLog.setLockType("abc");
        lockLog.setStatus(1);
        lockLog.setTimeoutTime(new Date());
        lockLogMapper.insert(lockLog);
    }


    @Test
    public void batchDeleteByPrimaryKeys() {
        List<LockLogDmo> lockLogs = new ArrayList<LockLogDmo>();
        for (int i = 0; i < 10; i++) {
            LockLogDmo lockLog = new LockLogDmo();
            lockLog.setId(Long.valueOf(i));
            lockLogs.add(lockLog);
        }
        lockLogMapper.batchDeleteByPrimaryKey(lockLogs);
    }


    @Test
    public void selectLockCount() {
        PageHelper.startPage(1, 500);
        System.out.println(lockLogMapper.selectByLockType("abc"));
        ;
//        sqlSession.selectList(LockLogMapper.class.getName() + ".selectByLockType", "abc", new RowBounds(1, 10));
    }

    @Test
    public void selectByLockTypeTest() {
        lockLogMapper.selectByLockType("abc");
    }

}
