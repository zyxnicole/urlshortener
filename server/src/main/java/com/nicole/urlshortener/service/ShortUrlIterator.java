package com.nicole.urlshortener.service;

@Component
public class ShortUrlIterator {
  private boolean hasNext = true;
  private char[] next = {'0', '0'};

  public boolean hasNext() {
    return hasNext;
  }

  public String next() {
    if (!hasNext) {
      throw new IllegalStateException("No available short url");
    }
    String shortUrl = next[0] + "" + next[1];
    increment();
    return shortUrl;
  }

  private void increment() {
    if (next[0] == 'Z' && next[1] == 'Z') {
      hasNext = false;
      return;
    }

    if (next[1] == 'Z') {
      next[0] = incrementDigit(next[0]);
    }
    next[1] = incrementDigit(next[1]);
  }

  private char incrementDigit(char digit) {
    if (digit == '9') {
      return 'a';
    }
    if (digit == 'z') {
      return 'A';
    }
    if (digit == 'Z') {
      return '0';
    }
    return ++digit;
  }
}
