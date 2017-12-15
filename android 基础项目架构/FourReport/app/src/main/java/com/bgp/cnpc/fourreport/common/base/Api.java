package com.bgp.cnpc.fourreport.common.base;

/**
 * Created by liuheng on 17-2-15.
 */

public interface Api {
    static String KEY = "";
    /**
     * swp 本地
     * http://10.88.53.59:8080/gms4/WSDLEngine/HseOperationSrv?wsdl
     */
//    final String IMG_URL = "";
    String BASE_URL = "http://10.88.53.59:8080";
    /**
     * 测试
     * http://10.88.248.94:8080/gms4/WSDLEngine/HseOperationSrv?wsdl
     */
    final String IMG_URL = "";
//    String BASE_URL = "http://10.88.248.94:8080";
    /**
     * 生产
     */
//    String IMG_URL = "";
//    String BASE_URL = "";

    String NAME_SPACE_URL = BASE_URL + "/gms4/WSDLEngine/HseOperationSrv?wsdl";

    String UPDATEAPK_METHOD = "downloadAppIsUpdatedData"; // 更新apk接口
}
