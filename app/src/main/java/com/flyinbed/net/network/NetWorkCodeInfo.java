package com.flyinbed.net.network;

/**
 * 作者：Administrator on 2017/6/26 14:24
 * 邮箱：zhanghuaiha@gmail.com
 */

public interface NetWorkCodeInfo {
    String VERSION = "/app/v1/version/release"; //获取新版本
    String FIRMWARE = "/app/v1/version/firmware"; //获取新版本
    String CODE = "/app/v1/sms/send"; //获取验证码
    String LOGIN = "/app/v1/sms/check_code_login"; //登录
    String TEL = "/app/v1/employee/check_identity"; //判断该手机号是否可用
    String FUNCTIONS = "/app/v1/employee/functions"; //9宫格数据
    String PARSE = "/app/v1/qrcode/parse"; //扫一扫数据解析
    String PARSE_v2 = "/app/v2/qrcode/parse"; //扫一扫数据解析
    String START_CHARGING = "/app/v1/charging/start_charging"; //扫一扫数据解析
    String ACTIVATION = "/app/v1/charging/activation"; //激活充电宝
    String STORE = "/app/v1/store/page"; //获取商家信息
    String CHARGING_PAGE = "/app/v1/store/charging_page"; //所属充电宝
    String WITHDRAW = "/app/v1/charging/withdraw"; //设备撤回
    String DELETE = "/app/v1/store/delete"; //删除
    String ALREADY_LAID = "/app/v1/store/already_laid"; //将店铺设为已铺设
    String ALREADY_STAY = "/app/v1/store/already_stay"; //将店铺设为待铺设
    String INSERT = "/app/v1/store/insert"; //添加门店
    String UPLOAD_IMAGE = "/app/v1/file/upload"; //上传图片
    String UPDATE = "/app/v1/store/update"; //更新门店
    String STORE_LIST = "/app/v1/store/page"; //获取所有商家
    String HARDWARE_FAULT = "/app/v1/charging/hardware_fault"; //硬件故障
    String STATISTICS = "/app/v1/employee/statistics"; //首页数据统计
    String SEND_FACTORY = "/app/v1/charging/send_factory"; //寄回工厂
    String PAGE = "/app/v1/charging/page"; //已安装充电宝分页

    String MESSAGE_STATUS = "/app/v1/charging/message_status"; //指令：查询指令链路情况
    String QUERY_SV = "/app/v1/charging/query_sv"; //指令：查询充电宝固件版本
    String UPDATE_SV = "/app/v1/charging/update_sv"; //指令：更新充电宝固件版本
    String CHECK_STATUS = "/app/v1/charging/check_status"; //指令：检查状态（在线）
    String GET_PASSWORD = "/app/v1/xdddxh/get_password"; //获取密码
    String AGAIN_PASSWORD = "/app/v1/xdddxh/again_password"; //再次获取密码

    String SIMPLE_PAGE = "/app/v1/store/simple_page"; //地图展现、统计店铺 位置可以使用

    String STATISTICS_STORE = "/app/v1/statistics/store"; //单个店铺按日期查询
    String GETCDB_INFO = "/app/v1/charging/get"; //获取充电宝和所属店铺的信息
    String GETCDB_INFO_INPUT = "/app/v1/charging/get_input"; //获取充电宝和所属店铺的信息

    String GREEN_LIGHTING = "/app/v1/charging/green_lighting"; //改变灯光状态
    String UPDATE_CDB = "/app/v1/charging/update"; //变更充电宝
    String DATE_INFO = "/app/v1/statistics/employee_realtime"; //进入 统计数据里面的页面要的数据
    String ME_HISTORY = "/app/v1/statistics/employee_history"; //个人历史数据
    String ME_HISTORY_TC = "/app/v1/statistics/commistion_history"; //个人历史提成记录
    String ME_HISTORY_TC_v2 = "/app/v2/statistics/commistion_history"; //个人历史提成记录 V2
    String ALL_INFO = "/app/v1/statistics/employee_all"; //统计界面数据
    String ALL_INFO_v2 = "/app/v2/statistics/employee_all"; //统计界面数据 V2

    String DAY_SHOP = "/app/v1/employee/store_realtime_ok"; //店铺今日达标情况
    String COMMISTION_HISTORY = "/app/v1/statistics/commistion_history"; //市场人员的历史提成 记录，按天计算
    String GET_HARDWARE_REPORT = "/app/v1/charging/get_hardware_report"; //获取充电宝硬件故障
    String MAINTENANCE_PAGE = "/app/v1/store/maintenance_page"; //待维护的分页
    String MAINTENANCE_PAGE_V2 = "/app/v2/store/maintenance_page"; //待维护的分页 V2
    String MAINTENANCE_CHARGING = "/app/v2/store/maintenance_charging"; //待维护店铺的 充电宝详情 v2
    String ALREADY_MAINTENANCE_CHARGING = "/app/v2/store/already_maintenance_charging"; //待维护店铺的 充电宝详情 v2 当日维护充电宝信息
    String ALL = "/app/v1/employee/all "; //待维护店铺的 充电宝详情 v2

}
