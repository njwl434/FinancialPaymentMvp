package com.wl.caiwushoukuan.dao;

import com.wl.caiwushoukuan.WlApplication;

import java.util.List;

/**
 * @describe: FinancialPaymentMvp
 * @author: 武梁
 * @date: 2017/12/31 19:34
 * @mailbox: 1034905058@qq.com
 */
public class LoanDaoUtil {
    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param
     */
    public static void insertLoan(Loan Loan) {
        WlApplication.getDaoInstant().getLoanDao().insertOrReplace(Loan);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteLoan(long id) {
        WlApplication.getDaoInstant().getLoanDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param Loan
     */
    public static void updateLoan(Loan Loan) {
        WlApplication.getDaoInstant().getLoanDao().update(Loan);
    }

    /**
     * 查询条件为Type=TYPE_Loan的数据
     *
     * @return
     */
    public static List<Loan> query(String name) {
        return WlApplication.getDaoInstant().getLoanDao().queryBuilder().where(LoanDao.Properties.Name.eq(name)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<Loan> queryAll() {
        return WlApplication.getDaoInstant().getLoanDao().loadAll();
    }

}
