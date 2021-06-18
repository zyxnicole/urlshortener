package com.nicole.urlshortener.cache;

import com.google.auto.value.AutoValue;

import java.time.Instant;

@AutoValue
public abstract class UrlSnapshot {

  public static UrlSnapshot create(
      String longUrl,
      String shortUrl,
      long count,
      Instant lastCallTime) {
    return new AutoValue_UrlSnapshot(longUrl, shortUrl, count, lastCallTime);
  }

  public abstract String longUrl();
  public abstract String shortUrl();
  public abstract long count();
  public abstract Instant lastCallTime();
}
