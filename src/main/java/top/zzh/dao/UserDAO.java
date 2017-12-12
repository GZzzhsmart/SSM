package top.zzh.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zzh.bean.User;
import top.zzh.common.Pager;

import java.util.List;

@Repository
public interface UserDAO extends BaseDAO {

    User getByPhonePwd(@Param("phone") String phone, @Param("pwd") String pwd);

    int updatePwd(@Param("user") User user);

    @Override
    List<Object> listPagerCriteria(@Param("pager") Pager pager, @Param("query") Object obj);

    @Override
    Long countCriteria(@Param("query") Object obj);
}
