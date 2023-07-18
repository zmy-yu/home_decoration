package com.example.home_decoration.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.home_decoration.pojo.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class JwtUtil {
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    private static final String TOKEN_SECRET = "jwt_123*.,";

    public static String sign(User user) {
        Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //创建token
        String token = JWT.create()
                //存放数据
                .withClaim("u_id",user.getU_id())
                .withClaim("u_role",user.getU_role())
                //过期时间
                .withExpiresAt(expireAt)
                //签名 token密钥
                .sign(algorithm);
        return token;
    }
    public static void verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        jwtVerifier.verify(token);
    }

    public static Integer getU_id(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        String token = authToken.substring("Bearer".length() + 1).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer u_id = decodedJWT.getClaim("u_id").asInt();
        return u_id;
    }

    public static Integer getU_role(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        String token = authToken.substring("Bearer".length() + 1).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer u_role = decodedJWT.getClaim("u_role").asInt();
        return u_role;
    }

    public static Integer getW_id(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        String token = authToken.substring("Bearer".length() + 1).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        Integer w_id = decodedJWT.getClaim("w_id").asInt();
        return w_id;
    }
}
