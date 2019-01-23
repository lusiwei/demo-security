package com.lusiwei.security.core.social.qq.api;

import com.lusiwei.security.core.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 21:04
 * @Description:
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    private String appId;
    private String openId;

    //    private ObjectMapper objectMapper=new ObjectMapper();
    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        System.out.println("---------------------");
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        try {
//            QQUserInfo qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            QQUserInfo qqUserInfo = JsonUtil.string2object(result, new TypeReference<QQUserInfo>() { });
            qqUserInfo.setOpenId(openId);
            System.out.println(qqUserInfo);
            return qqUserInfo;
        } catch (Exception e) {
            throw new RuntimeException("用户信息获取失败", e);
        }
    }
}
