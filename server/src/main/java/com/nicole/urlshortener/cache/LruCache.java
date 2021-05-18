package com.nicole.urlshortener.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class LruCache {
  Map<String, UrlNodeDoubleLinkedList.UrlNode> longUrlMap = new HashMap<>();
  Map<String, UrlNodeDoubleLinkedList.UrlNode> shortUrlMap = new HashMap<>();
  UrlNodeDoubleLinkedList urlNodeDoubleLinkedList = new UrlNodeDoubleLinkedList();

  public String removeLast() {
    UrlNodeDoubleLinkedList.UrlNode node = urlNodeDoubleLinkedList.removeLast();
    longUrlMap.remove(node.getLongUrl());
    shortUrlMap.remove(node.getShortUrl());
    return node.getShortUrl();
  }

  public void put(String longUrl, String shortUrl) {
    UrlNodeDoubleLinkedList.UrlNode node = urlNodeDoubleLinkedList.push(longUrl, shortUrl);
    longUrlMap.put(longUrl, node);
    shortUrlMap.put(shortUrl, node);
  }

  public String getShortUrl(String longUrl) {
    UrlNodeDoubleLinkedList.UrlNode node = longUrlMap.get(longUrl);
    if (node == null) {
      return null;
    }
    return node.getShortUrl();
  }

  public String getLongUrl(String shortUrl) {
    UrlNodeDoubleLinkedList.UrlNode node = shortUrlMap.get(shortUrl);
    if (node == null) {
      return null;
    }
    node.incrementCount();
    node.updateLastCallTime();
    urlNodeDoubleLinkedList.moveToHead(node);
    return node.getLongUrl();
  }

  public List<UrlSnapshot> getSnapshot() {
    return urlNodeDoubleLinkedList.getAllNodes().stream()
        .map(node ->
            UrlSnapshot.create(
                node.getLongUrl(),
                node.getShortUrl(),
                node.getCount(),
                node.getLastCallTime()))
        .collect(Collectors.toList());
  }
}
