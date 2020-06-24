//package com.scbm;
//
//import com.netflix.hystrix.contrib.javanica.exception.ExceptionUtils;
//import okhttp3.*;
//
///**
// * todo 一句话描述该类的用途
// *
// * @author 刘凯峰
// * @date 2018-12-13 09-05
// */
//@
//public class OkHttp3Util {
//    /**
//     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
//     * 参数一：请求Url
//     * 参数二：请求的JSON
//     * 参数三：请求回调
//     */
//    public static String postJsonParams(String url, String jsonParams) {
//        String responseBody = "";
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        Response response = null;
//        try {
//            OkHttpClient okHttpClient = new OkHttpClient();
//            response = okHttpClient.newCall(request).execute();
//            int status = response.code();
//            if (response.isSuccessful()) {
//                return response.body().string();
//            }
//        } catch (Exception e) {
//            logger.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
//        } finally {
//            if (response != null) {
//                response.close();
//            }
//        }
//        return responseBody;
//    }
//}
