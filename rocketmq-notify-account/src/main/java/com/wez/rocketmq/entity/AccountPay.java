package com.wez.rocketmq.entity;

/**
 * AccountPay
 *
 * @Author wez
 * @Time 2021/10/2 23:19
 */
public class AccountPay {

    /**
     *  事务号
     */
    private String id;

    /**
     * 账号
     */
    private String accountNo;
    /**
     * 变动金额
     */
    private double payAmount;
    /**
     * 充值结果
     */
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AccountPay{" +
                "id='" + id + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", payAmount=" + payAmount +
                ", result='" + result + '\'' +
                '}';
    }
}
