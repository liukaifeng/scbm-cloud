package com.scbm.server.config.example;

import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;

import java.util.List;

/**
 * feign 官方示例
 *
 * @author 刘凯峰
 * @version V1.0
 * update-logs:方法变更说明
 * ****************************************************
 * name:
 * date:
 * description:
 * *****************************************************
 * @date 2020-06-28 14:08
 */
public class GitHubApp {

    /**
     * 定义声明式接口
     */
    interface GitHub {
        /**
         * 声明调用方式 GET 和 调用地址，获取指定仓库贡献者列表
         */
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repository);

        class Contributor {
            String login;
            int contributions;
        }
    }

    public static void main(String[] args) {
        //Feign客户端初始化
        GitHub github = Feign.builder()
                .decoder(new GsonDecoder())
                .logLevel(Logger.Level.FULL)
                .target(GitHub.class, "https://api.github.com");
        //获取并打印feign的贡献者列表。
        List<GitHub.Contributor> contributors = github.contributors("OpenFeign", "feign");
        contributors.forEach(contributor -> System.out.println(contributor.login + " (" + contributor.contributions + ")"));
    }
}
