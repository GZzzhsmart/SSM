package top.zzh.dao;

import org.springframework.stereotype.Repository;
import top.zzh.vo.CashTypeVO;

import java.util.List;

@Repository
public interface CashTypeDAO extends BaseDAO {

    List<CashTypeVO> getByPid(Long pid);

}
