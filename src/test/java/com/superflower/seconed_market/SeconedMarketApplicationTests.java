package com.superflower.seconed_market;

import com.superflower.market.util.JwtUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeconedMarketApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(JwtUtils.checkToken("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODk0NTM2MTgsInVzZXJuYW1lIjoi5byg6K-PIn0.dg8PVb1gHWSizB5FpxANZKS4IDhnB_DCeFjyTqbKzi4"));
    }


}