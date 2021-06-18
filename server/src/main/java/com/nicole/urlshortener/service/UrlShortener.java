package com.nicole.urlshortener.service;

import com.nicole.urlshortener.cache.LruCache;
import com.nicole.urlshortener.cache.UrlSnapshot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UrlShortener {

  private static final String SHORT_URL_PREFIX = "http://nicole.z/";

  private final ShortUrlIterator shortUrlIterator;
  private final LruCache lruCache;

  @Autowired
  public UrlShortener(ShortUrlIterator shortUrlIterator, LruCache lruCache) {
    this.shortUrlIterator = shortUrlIterator;
    this.lruCache = lruCache;
  }


  public String shorten(String longUrl) {
    String shortUrl = lruCache.getShortUrl(longUrl);
    if (shortUrl != null) {
      return shortUrl;
    }
    if (shortUrlIterator.hasNext()) {
      shortUrl = SHORT_URL_PREFIX + shortUrlIterator.next();
    } else {
      shortUrl = lruCache.removeLast();
    }
    lruCache.put(longUrl, shortUrl);
    return shortUrl;
  }

  public String retrieve(String shortUrl) {
    return lruCache.getLongUrl(shortUrl);
  }

  public List<UrlSnapshot> dump() {
    return lruCache.getSnapshot();
  }
}
