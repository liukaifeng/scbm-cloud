##### spring cloud gateway
在Spring Cloud Gateway中，主要有两种类型的过滤器：GlobalFilter 和 GatewayFilter

GlobalFilter： 全局过滤器，对所有的路由均起作用

GatewayFilter： 对指定的路由起作用

## GatewayFilter 

### 实现GatewayFilter接口
首先从header头信息中获取uid和token信息，如果token或者uid为null，则从请求参数中尝试再次获取，如果依然不存在token或者uid，则直接返回401状态吗，同时结束请求；如果两者都存在，则根据uid从redis中读取保存的authToken，并和请求中传输的token进行比对，比对一样则继续通过过滤器链，否则直接结束请求，返回401.

#### 应用方式
~~~
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes().route(r ->
                r.path("/user/list")
                .uri("http://localhost:8077/api/user/list")
                .filters(new AuthorizeGatewayFilter())
                .id("user-service"))
            .build();
    }
}
~~~

### 继承抽象类AbstractGatewayFilterFactory


## GlobalFilter
只需要添加 @Component 注解，不需要进行任何额外的配置，实现GlobalFilter接口，自动会对所有的路由起作用
