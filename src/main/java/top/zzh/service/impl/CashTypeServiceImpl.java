package top.zzh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zzh.common.Pager;
import top.zzh.dao.CashTypeDAO;
import top.zzh.service.CashTypeService;
import top.zzh.vo.CashTypeVO;

import java.util.List;

@Service
public class CashTypeServiceImpl implements CashTypeService {

    @Autowired
    private CashTypeDAO cashTypeDAO;

    @Override
    public void save(Object obj) {
        cashTypeDAO.save(obj);
    }

    @Override
    public void remove(Object obj) {
        cashTypeDAO.remove(obj);
    }

    @Override
    public void removeById(Long id) {
        cashTypeDAO.removeById(id);
    }

    @Override
    public void update(Object obj) {
        cashTypeDAO.update(obj);
    }

    @Override
    public Object getById(Long id) {
        return cashTypeDAO.getById(id);
    }

    @Override
    public List<Object> listAll() {
        return cashTypeDAO.listAll();
    }

    @Override
    public Pager listPager(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Pager listPagerCriteria(int pageNo, int pageSize, Object obj) {
        return null;
    }

    @Override
    public List<CashTypeVO> getByPid(Long pid) {
        return cashTypeDAO.getByPid(pid);
    }
}
