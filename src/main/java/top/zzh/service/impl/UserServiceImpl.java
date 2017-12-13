package top.zzh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zzh.bean.User;
import top.zzh.common.Pager;
import top.zzh.dao.UserDAO;
import top.zzh.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getByPhonePwd(String phone, String pwd) {
        return userDAO.getByPhonePwd(phone, pwd);
    }

    @Override
    public void save(Object obj) {
        userDAO.save(obj);
    }

    @Override
    public void remove(Object obj) {
        userDAO.remove(obj);
    }

    @Override
    public void removeById(Long id) {
        userDAO.removeById(id);
    }

    @Override
    public void update(Object obj) {
        userDAO.update(obj);
    }

    @Override
    public Object getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public List<Object> listAll() {
        return userDAO.listAll();
    }

    @Override
    public Pager listPager(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Pager listPagerCriteria(int pageNo, int pageSize, Object obj) {
        Pager pager = new Pager(pageNo, pageSize);
        pager.setRows(userDAO.listPagerCriteria(pager, obj));
        pager.setTotal(userDAO.countCriteria(obj));
        return pager;
    }

    @Override
    public String checkPwd(String phone) {
        return userDAO.checkPwd(phone);
    }

    @Override
    public void updatePwd(Long id, String pwd) {
        userDAO.updatePwd(id,pwd);
    }
}
