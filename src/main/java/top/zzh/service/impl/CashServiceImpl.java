package top.zzh.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zzh.common.Pager;
import top.zzh.dao.CashDAO;
import top.zzh.query.CashQuery;
import top.zzh.service.CashService;
import top.zzh.vo.CashVO;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CashServiceImpl implements CashService {

    @Autowired
    private CashDAO cashDAO;

    @Override
    public void save(Object obj) {
        cashDAO.save(obj);
    }

    @Override
    public void remove(Object obj) {
        cashDAO.remove(obj);
    }

    @Override
    public void removeById(Long id) {
        cashDAO.removeById(id);
    }

    @Override
    public void update(Object obj) {
        cashDAO.update(obj);
    }

    @Override
    public Object getById(Long id) {
        return cashDAO.getById(id);
    }

    @Override
    public List<Object> listAll() {
        return cashDAO.listAll();
    }

    @Override
    public Pager listPager(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Pager listPagerCriteria(int pageNo, int pageSize, Object obj) {
        Pager pager = new Pager(pageNo, pageSize);
        pager.setRows(cashDAO.listPagerCriteria(pager, obj));
        pager.setTotal(cashDAO.countCriteria(obj));
        return pager;
    }

    @Override
    public Workbook export(CashQuery cashQuery) {
        Pager pager = new Pager(1, cashDAO.countCriteria(cashQuery).intValue());
        List<Object> objectList = cashDAO.listPagerCriteria(pager, cashQuery);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("cash");
        createHeadRow(sheet);
        createContentRows(sheet, objectList);
        return workbook;
    }

    private void createHeadRow(Sheet sheet) {
        Row headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("编号");
        headRow.createCell(1).setCellValue("金额");
        headRow.createCell(2).setCellValue("客户名称");
        headRow.createCell(3).setCellValue("收支类型");
        headRow.createCell(4).setCellValue("支付方式");
        headRow.createCell(5).setCellValue("收支时间");
        headRow.createCell(6).setCellValue("创建时间");
    }

    private void createContentRows(Sheet sheet, List<Object> objectList) {
        int i = 1;
        for (Object obj : objectList) {
            CashVO cashVO = (CashVO) obj;
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(cashVO.getId());
            row.createCell(1).setCellValue(cashVO.getMoney().doubleValue());
            row.createCell(2).setCellValue(cashVO.getCashCustomer());
            row.createCell(3).setCellValue(cashVO.getCashType());
            row.createCell(4).setCellValue(cashVO.getPayType());
            row.createCell(5).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(cashVO.getCashTime()));
            row.createCell(6).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(cashVO.getCreateTime()));
            i++;
        }
    }
}
