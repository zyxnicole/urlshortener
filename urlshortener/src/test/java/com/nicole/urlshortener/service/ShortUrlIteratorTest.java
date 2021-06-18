package com.nicole.urlshortener.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortUrlIteratorTest {
    private ShortUrlIterator shortUrlIterator;

    @Before
    public void setup() {
        shortUrlIterator = new ShortUrlIterator();
    }

    @Test
    public void iterateToZZ_thenThrow() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            sb.append(i);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            sb.append(i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            sb.append(i);
        }
        char[] pattern = sb.toString().toCharArray();
        for (char d1 : pattern) {
            for (char d0 : pattern) {
                Assertions.assertThat(shortUrlIterator.next()).isEqualTo(d1 + "" + d0);
            }
        }
        Assertions.assertThat(shortUrlIterator.hasNext()).isFalse();
        try {
            shortUrlIterator.next();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("No available short url");
            return;
        }
        fail();
    }
}