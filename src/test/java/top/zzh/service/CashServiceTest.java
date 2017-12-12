package top.zzh.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.zzh.common.Pager;
import top.zzh.query.CashQuery;
import top.zzh.vo.CashVO;

import java.math.BigDecimal;


public class CashServiceTest extends BaseTest {

    @Autowired
    private CashService cashService;

    @Test
    public void testListPagerCriteria() {
        CashQuery query = new CashQuery();
        query.setMoney(new BigDecimal(1000));
        query.setCashTime(" 2017-12-08 10:54:07");
        Pager pager = cashService.listPagerCriteria(1, 2, query);
        System.out.println(((CashVO)pager.getRows().get(0)).getId());
    }
}
