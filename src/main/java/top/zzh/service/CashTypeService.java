package top.zzh.service;


import top.zzh.vo.CashTypeVO;

import java.util.List;


public interface CashTypeService extends BaseService {

    List<CashTypeVO> getByPid(Long pid);

}
