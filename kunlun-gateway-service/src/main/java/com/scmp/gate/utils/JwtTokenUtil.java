package com.scmp.gate.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * 签名和效验Token
 *
 * JWT，基于散列的消息认证码，输入密钥和消息，生成消息摘要。
 * 该密钥只存储在服务端。
 * 用户访问时携带该消息摘要进行传播，服务端对该消息摘要进行验证
 */
public class JwtTokenUtil {

    private static Logger log = LogManager.getLogger();

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String userName, String password, String secret) {
        try {
            // 根据userName、password及密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userName", userName).withClaim("password", password).build();

            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info("Token expire time ===>>> " + jwt.getExpiresAt() + "， Token ===>>> " + jwt.getToken());
            return true;
        } catch (Exception e) {
            log.error("JwtTokenUtil verify Error: ", e);
            return false;
        }
    }

    /**
     * 获得token中的信息(无需secret解密也能获得)
     *
     * @return token中包含的用户名
     */
    public static String getTokenInfo(String token, String name) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(name).asString();
        } catch (JWTDecodeException e) {
            log.error("JwtTokenUtil getTokenInfo Error: ", e);
            return null;
        }
    }

    /**
     * 生成jwt token，签名，30min后过期
     *
     * @param userName 用户名
     * @return 加密的token
     */
    public static String sign(String userName, String password, String secret, Integer expireTime) {
        Date date = new Date(System.currentTimeMillis() + expireTime);
        Algorithm algorithm = Algorithm.HMAC256(secret);

        // 附带username、password及过期时长信息
        return JWT.create().withClaim("userName", userName).withClaim("password", password).withExpiresAt(date).sign(algorithm);
    }
}
