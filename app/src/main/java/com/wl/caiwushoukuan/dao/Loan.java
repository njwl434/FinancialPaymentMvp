package com.wl.caiwushoukuan.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @describe: FinancialPaymentMvp
 * @author: 武梁
 * @date: 2017/12/31 19:16
 * @mailbox: 1034905058@qq.com
 */
@Entity
public class Loan {
    //不能用int
    @Id(autoincrement = true)
    private Long id;
    //贷款人姓名
    @Unique
    @Property(nameInDb = "name")
    private String name;
    //贷款金额
    @Property(nameInDb = "loanprice")
    private String loanprice;
    //每日利息
    @Property(nameInDb = "interest")
    private String interest;
    //贷款日期
    @Property(nameInDb = "loandate")
    private String loandate;
    //还款日期
    @Property(nameInDb = "unloandate")
    private String unloandate;
    //手机号码
    @Property(nameInDb = "phone")
    private String phone;
    //家庭住址
    @Property(nameInDb = "address")
    private String address;
    //家属姓名
    @Property(nameInDb = "familyname")
    private String familyname;
    //还款额
    @Property(nameInDb = "payments")
    private String payments;
    //第一月到手
    @Property(nameInDb = "monthone")
    private String monthone;
    //欠条额
    @Property(nameInDb = "lousfrontal")
    private String lousfrontal;
    //业务员
    @Property(nameInDb = "salesman")
    private String salesman;
    //违约次数
    @Property(nameInDb = "defaultnumber")
    private int defaultnumber;
    //商品列表类型
    private int type;
    @Generated(hash = 2142753170)
    public Loan(Long id, String name, String loanprice, String interest,
            String loandate, String unloandate, String phone, String address,
            String familyname, String payments, String monthone, String lousfrontal,
            String salesman, int defaultnumber, int type) {
        this.id = id;
        this.name = name;
        this.loanprice = loanprice;
        this.interest = interest;
        this.loandate = loandate;
        this.unloandate = unloandate;
        this.phone = phone;
        this.address = address;
        this.familyname = familyname;
        this.payments = payments;
        this.monthone = monthone;
        this.lousfrontal = lousfrontal;
        this.salesman = salesman;
        this.defaultnumber = defaultnumber;
        this.type = type;
    }
    @Generated(hash = 1342082873)
    public Loan() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLoanprice() {
        return this.loanprice;
    }
    public void setLoanprice(String loanprice) {
        this.loanprice = loanprice;
    }
    public String getInterest() {
        return this.interest;
    }
    public void setInterest(String interest) {
        this.interest = interest;
    }
    public String getLoandate() {
        return this.loandate;
    }
    public void setLoandate(String loandate) {
        this.loandate = loandate;
    }
    public String getUnloandate() {
        return this.unloandate;
    }
    public void setUnloandate(String unloandate) {
        this.unloandate = unloandate;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getFamilyname() {
        return this.familyname;
    }
    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }
    public String getPayments() {
        return this.payments;
    }
    public void setPayments(String payments) {
        this.payments = payments;
    }
    public String getMonthone() {
        return this.monthone;
    }
    public void setMonthone(String monthone) {
        this.monthone = monthone;
    }
    public String getLousfrontal() {
        return this.lousfrontal;
    }
    public void setLousfrontal(String lousfrontal) {
        this.lousfrontal = lousfrontal;
    }
    public String getSalesman() {
        return this.salesman;
    }
    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }
    public int getDefaultnumber() {
        return this.defaultnumber;
    }
    public void setDefaultnumber(int defaultnumber) {
        this.defaultnumber = defaultnumber;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
