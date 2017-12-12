package top.zzh.service;

import org.apache.poi.ss.usermodel.Workbook;
import top.zzh.query.LoginLogQuery;

public interface LoginLogService extends BaseService {

    void updateByUserId(Long userId);
    Workbook export(LoginLogQuery loginLogQuery);
}
