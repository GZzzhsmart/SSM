package top.zzh.query;

import java.math.BigDecimal;

public class CashQuery {

    private BigDecimal money;
    private String cashCustomer;
    private String cashTime;
    private Long pid;
    private Long cashType;
    private Long payType;
    private String createTime;

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

    public String getCashTime() {
        return cashTime;
    }

    public void setCashTime(String cashTime) {
        this.cashTime = cashTime;
    }

    public Long getCashType() {
        return cashType;
    }

    public void setCashType(Long cashType) {
        this.cashType = cashType;
    }

    public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
