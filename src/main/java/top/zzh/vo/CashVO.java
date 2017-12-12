package top.zzh.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class CashVO {

    private Long id;
    private BigDecimal money;
    private String cashCustomer;
    private Timestamp cashTime;
    private Long cashTypePid;
    private Long cashTypeId;
    private String cashType;
    private Long payTypeId;

    public Long getCashTypePid() {
        return cashTypePid;
    }

    public void setCashTypePid(Long cashTypePid) {
        this.cashTypePid = cashTypePid;
    }

    public Long getCashTypeId() {
        return cashTypeId;
    }

    public void setCashTypeId(Long cashTypeId) {
        this.cashTypeId = cashTypeId;
    }

    public Long getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Long payTypeId) {
        this.payTypeId = payTypeId;
    }

    private String payType;
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCashCustomer() {
        return cashCustomer;
    }

    public void setCashCustomer(String cashCustomer) {
        this.cashCustomer = cashCustomer;
    }

    public Timestamp getCashTime() {
        return cashTime;
    }

    public void setCashTime(Timestamp cashTime) {
        this.cashTime = cashTime;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
