package com.wl.caiwushoukuan.util;

/**
 * @author wangjijun
 * @desc 函数通用类
 * @create 2017-11-09 22:21
 **/
public class Func {

    /**
     * 业务处理代码
     */
    public class Process {
        /**
         * 处理成功
         */
        public static final String PRCESS_SUCCESS = "001";
        /**
         * 处理失败
         */
        public static final String PRCESS_FAILURE = "000";
    }


    /**
     * 订单代码
     */
    public class Order {
        /**
         * 市价委托-买单
         */
        public static final String BUY_MARKET = "110";
        /**
         * 限价委托-买单
         */
        public static final String BUY_PENDING = "120";
        /**
         * 市价委托-卖单
         */
        public static final String SELL_MARKET = "210";
        /**
         * 限价委托-卖单
         */
        public static final String SELL_PENDING = "220";
        /**
         * 撤单
         */
        public static final String CANCEL = "150";
    }

    /**
     * 实时发布
     */
    public class Publish {
        /**
         * 实时价格
         */
        public static final String REAL_PRICE = "301";
        /**
         * 实时盘口
         */
        public static final String REAL_POSITION = "302";
    }

    /**
     * 错误代码
     */
    public class Error {
        /**
         * 报文错误
         */
        public static final String DATA_FRAME_ERROR = "400";
        /**
         * 系统不支持此函数
         */
        public static final String NONSUPPORT_FUNC = "401";
        /**
         * 参数错误
         */
        public static final String PARAMS_ERROR = "402";
        /**
         * Json 解析错误
         */
        public static final String JSON_PARSE_ERROR = "403";
        /**
         * 系统内部错误
         */
        public static final String SYSTEM_ERROR = "500";
    }

    /**
     * 用户授权
     */
    public class Auth {
        /**
         * 请求授权
         */
        public static final String AUTH_REQUEST = "";
        /**
         * 授权失败
         */
        public static final String AUTH_FAILURE = "";
        /**
         * 授权成功
         */
        public static final String AUTH_SUCCESS = "";
    }

    /**
     * 用户通知操作
     */
    public class NotifyType {
        /**
         * 不干扰用户操作
         */
        public static final String SHOW_TOAST = "601";
    }
}
