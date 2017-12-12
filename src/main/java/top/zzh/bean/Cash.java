package top.zzh.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class Cash {
    private Long id;
    private BigDecimal money;
    private Timestamp cashTime;
    private String cashCustomer;
    private Long cashType;
    private Long payType;
    private Long userId;
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

    public Timestamp getCashTime() {
        return cashTime;
    }

    public void setCashTime(Timestamp cashTime) {
        this.cashTime = cashTime;
    }

    public String getCashCustomer() {
        return cashCustomer;
    }

    public void setCashCustomer(String cashCustomer) {
        this.cashCustomer = cashCustomer;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
