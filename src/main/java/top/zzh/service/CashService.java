package top.zzh.service;


import org.apache.poi.ss.usermodel.Workbook;
import top.zzh.query.CashQuery;

public interface CashService extends BaseService {

    Workbook export(CashQuery cashQuery);
}
