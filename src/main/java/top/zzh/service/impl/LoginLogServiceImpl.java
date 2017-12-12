package top.zzh.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zzh.common.Pager;
import top.zzh.dao.LoginLogDAO;
import top.zzh.query.CashQuery;
import top.zzh.query.LoginLogQuery;
import top.zzh.service.LoginLogService;
import top.zzh.vo.CashVO;
import top.zzh.vo.LoginLogVO;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDAO loginLogDAO;

    @Override
    public void save(Object obj) {
        loginLogDAO.save(obj);
    }

    @Override
    public void remove(Object obj) {
        loginLogDAO.remove(obj);
    }

    @Override
    public void removeById(Long id) {
        loginLogDAO.removeById(id);
    }

    @Override
    public void update(Object obj) {
        loginLogDAO.update(obj);
    }

    @Override
    public Object getById(Long id) {
        return loginLogDAO.getById(id);
    }

    @Override
    public List<Object> listAll() {
        return loginLogDAO.listAll();
    }

    @Override
    public Pager listPager(int pageNo, int pageSize) {

        return null;
    }

    @Override
    public Pager listPagerCriteria(int pageNo, int pageSize, Object obj) {
        Pager pager = new Pager(pageNo, pageSize);
        pager.setRows(loginLogDAO.listPagerCriteria(pager, obj));
        pager.setTotal(loginLogDAO.countCriteria(obj));
        return pager;
    }

    @Override
    public void updateByUserId(Long userId) {
        loginLogDAO.updateByUserId(userId);
    }

    @Override
    public Workbook export(LoginLogQuery loginLogQuery) {
        Pager pager = new Pager(1, loginLogDAO.countCriteria(loginLogQuery).intValue());
        List<Object> objectList = loginLogDAO.listPagerCriteria(pager, loginLogQuery);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("login_log");
        createHeadRow(sheet);
        createContentRows(sheet, objectList);
        return workbook;
    }

    private void createHeadRow(Sheet sheet) {
        Row headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("编号");
        headRow.createCell(1).setCellValue("用户姓名");
        headRow.createCell(2).setCellValue("手机号码");
        headRow.createCell(3).setCellValue("登录时间");
        headRow.createCell(4).setCellValue("登录IP");
        headRow.createCell(5).setCellValue("登录状态");
        headRow.createCell(6).setCellValue("退出时间");
    }

    private void createContentRows(Sheet sheet, List<Object> objectList) {
        int i = 1;
        for (Object obj : objectList) {
            LoginLogVO loginLogVO = (LoginLogVO) obj;
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(loginLogVO.getId());
            row.createCell(1).setCellValue(loginLogVO.getRealname());
            row.createCell(2).setCellValue(loginLogVO.getPhone());
            row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(loginLogVO.getLoginTime()));
            row.createCell(4).setCellValue(loginLogVO.getLoginIp());
            row.createCell(5).setCellValue(loginLogVO.getIsOnline());
            row.createCell(6).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(loginLogVO.getLogoutTime()));
            i++;
        }
    }
}
