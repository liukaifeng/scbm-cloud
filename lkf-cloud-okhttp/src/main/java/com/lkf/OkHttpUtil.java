//package com.lkf;
//
//import lombok.Builder;
//import lombok.ToString;
//import okhttp3.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
///**
// * todo 一句话描述该类的用途
// *
// * @author 刘凯峰
// * @date 2018-12-12 17-53
// */
//public class OkHttpUtil {
//
//    public final static String GET = "GET";
//
//    public final static String POST = "POST";
//
//    public final static String PUT = "PUT";
//
//    public final static String DELETE = "DELETE";
//
//    public final static String PATCH = "PATCH";
//
//    private final static String UTF8 = "UTF-8";
//
//    private final static String GBK = "GBK";
//
//    private final static String DEFAULT_CHARSET = UTF8;
//
//    private final static String DEFAULT_METHOD = GET;
//
//    private final static String DEFAULT_MEDIA_TYPE = "application/json";
//
//    private final static boolean DEFAULT_LOG = false;
//
//    private final static OkHttpClient client = new OkHttpClient.Builder().connectionPool(
//            new ConnectionPool(20, 5, TimeUnit.MINUTES)).readTimeout(20, TimeUnit.SECONDS).connectTimeout(
//            20, TimeUnit.SECONDS).build();
//
//    //测试一波
//    public static void main( String[] args ) {
//        Map<String, String> map = new HashMap<>();
//        map.put("k", "v");
//        try {
//            String s = execute(OkHttp.builder().url("http://www.baidu.com").method(GET).requestLog(
//                    true).responseLog(true).build());
//            System.out.println(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * GET请求
//     *
//     * @param url URL地址
//     * @return
//     */
//    public static String get( String url )
//            throws Exception {
//        return execute(OkHttp.builder().url(url).build());
//    }
//
//    /**
//     * GET请求
//     *
//     * @param url URL地址
//     * @return
//     */
//    public static String get( String url, String charset )
//            throws Exception {
//        return execute(OkHttp.builder().url(url).responseCharset(charset).build());
//    }
//
//    /**
//     * 带查询参数的GET查询
//     *
//     * @param url      URL地址
//     * @param queryMap 查询参数
//     * @return
//     */
//    public static String get( String url, Map<String, String> queryMap )
//            throws Exception {
//        return execute(OkHttp.builder().url(url).queryMap(queryMap).build());
//    }
//
//    /**
//     * 带查询参数的GET查询
//     *
//     * @param url      URL地址
//     * @param queryMap 查询参数
//     * @return
//     */
//    public static String get( String url, Map<String, String> queryMap, String charset )
//            throws Exception {
//        return execute(OkHttp.builder().url(url).queryMap(queryMap).responseCharset(charset).build());
//    }
//
//    /**
//     * POST application/json
//     *
//     * @param url
//     * @param obj
//     * @return
//     */
//    public static String postJson( String url, Object obj )
//            throws Exception {
//        return execute(OkHttp.builder().url(url).method(POST).data(JSON.toJSONString(obj)).mediaType(
//                "application/json").build());
//    }
//
//    /**
//     * POST application/x-www-form-urlencoded
//     *
//     * @param url
//     * @param formMap
//     * @return
//     */
//    public static String postForm( String url, Map<String, String> formMap )
//            throws Exception {
//        String data = "";
//        if (!formMap.isEmpty()) {
//            data = formMap.entrySet().stream().map(
//                    entry -> String.format("%s=%s", entry.getKey(), entry.getValue())).collect(
//                    Collectors.joining("&"));
//        }
//        return execute(OkHttp.builder().url(url).method(POST).data(data).mediaType(
//                "application/x-www-form-urlencoded").build());
//    }
//
//    private static String post( String url, String data, String mediaType, String charset )
//            throws Exception {
//        return execute(OkHttp.builder().url(url).method(POST).data(data).mediaType(mediaType).responseCharset(
//                charset).build());
//    }
//
//    /**
//     * 通用执行方法
//     */
//    private static String execute( OkHttp okHttp )
//            throws Exception {
//
//        if (String.isEmpty(okHttp.requestCharset)) {
//            okHttp.requestCharset = DEFAULT_CHARSET;
//        }
//        if (StringUtils.isEmpty(okHttp.responseCharset)) {
//            okHttp.responseCharset = DEFAULT_CHARSET;
//        }
//        if (StringUtils.isEmpty(okHttp.method)) {
//            okHttp.method = DEFAULT_METHOD;
//        }
//        if (StringUtils.isEmpty(okHttp.mediaType)) {
//            okHttp.mediaType = DEFAULT_MEDIA_TYPE;
//        }
//        if (okHttp.requestLog) {// 记录请求日志
//            LoggerUtils.info(OkHttpUtil.class, okHttp.toString());
//        }
//
//        // 获取请求URL
//        String url = okHttp.url;
//        // 创建请求
//        Request.Builder builder = new Request.Builder();
//
//        if (MapUtils.isNotEmpty(okHttp.queryMap)) {
//            String queryParams = okHttp.queryMap.entrySet().stream().map(
//                    entry -> String.format("%s=%s", entry.getKey(), entry.getValue())).collect(
//                    Collectors.joining("&"));
//            url = String.format("%s%s%s", url, url.contains("?") ? "&" : "?", queryParams);
//        }
//        builder.url(url);
//
//        // 设置请求头
//        if (MapUtils.isNotEmpty(okHttp.headerMap)) {
//            okHttp.headerMap.forEach(builder::addHeader);
//        }
//
//        // 设置请求类型
//        String method = okHttp.method.toUpperCase();
//        String mediaType = String.format("%s;charset=%s", okHttp.mediaType, okHttp.requestCharset);
//
//        if (method.equals(GET)) {
//            builder.get();
//        } else if (ArrayUtils.contains(new String[]{POST, PUT, DELETE, PATCH}, method)) {
//            RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), okHttp.data);
//            builder.method(method, requestBody);
//        } else {
//            throw new GlobalException("未设置请求method");
//        }
//
//        // 返回值
//        String result = "";
//        try {
//            Response response = client.newCall(builder.build()).execute();
//            byte[] bytes = response.body().bytes();
//            result = new String(bytes, okHttp.responseCharset);
//            if (okHttp.responseLog) {// 记录返回日志
//                LoggerUtils.info(OkHttpUtil.class, result);
//            }
//        } catch (Exception e) {
//            LoggerUtils.error(OkHttpUtil.class, e.getMessage(), e);
//        }
//        return result;
//    }
//
//    /**
//     * 一个内部类
//     *
//     * @author gogym
//     * @version 2018年7月30日
//     * @see OkHttp
//     */
//
//    @Builder
//    @ToString(exclude = {"requestCharset", "responseCharset", "requestLog", "responseLog"})
//    static class OkHttp {
//        private String url;
//
//        private String method = DEFAULT_METHOD;
//
//        private String data;
//
//        private String mediaType = DEFAULT_MEDIA_TYPE;
//
//        private Map<String, String> queryMap;
//
//        private Map<String, String> headerMap;
//
//        private String requestCharset = DEFAULT_CHARSET;
//
//        private boolean requestLog = DEFAULT_LOG;
//
//        private String responseCharset = DEFAULT_CHARSET;
//
//        private boolean responseLog = DEFAULT_LOG;
//    }
//}
