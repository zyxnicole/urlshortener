package com.nicole.urlshortener.cache;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class UrlNodeDoubleLinkedList {

  private final UrlNode head;
  private final UrlNode tail;

  UrlNodeDoubleLinkedList() {
    this.head = new UrlNode("", "");
    this.tail = new UrlNode("", "");
    head.next = tail;
    tail.previous = head;
  }

  boolean isEmpty() {
    return head.next == tail;
  }

  UrlNode push(String longUrl, String shortUrl) {
    UrlNode node = new UrlNode(longUrl, shortUrl);
    pushToHead(node);
    return node;
  }

  void moveToHead(UrlNode node) {
    if (node.previous != null) {
      node.previous.next = node.next;
    }
    if (node.next != null) {
      node.next.previous = node.previous;
    }
    pushToHead(node);
  }

  UrlNode removeLast() {
    if (isEmpty()) {
      return null;
    }

    UrlNode remove = tail.previous;
    tail.previous = remove.previous;
    tail.previous.next =tail;

    remove.previous = null;
    remove.next = null;

    return remove;
  }

  List<UrlNode> getAllNodes() {
    List<UrlNode> list = new ArrayList<>();
    UrlNode node = head.next;
    while (node != tail) {
      list.add(node);
      node = node.next;
    }
    return list;
  }

  private void pushToHead(UrlNode node) {
    node.previous = head;
    node.next = head.next;
    head.next.previous = node;
    head.next = node;
  }

  static class UrlNode {
    private UrlNode next;
    private UrlNode previous;

    private final String longUrl;
    private final String shortUrl;
    private long count;
    private Instant lastCallTime;

    private UrlNode(String longUrl, String shortUrl) {
      this.longUrl = longUrl;
      this.shortUrl = shortUrl;
      this.count = 0;
      this.lastCallTime = Instant.now();
    }

    String getShortUrl() {
      return shortUrl;
    }

    String getLongUrl() {
      return longUrl;
    }

    long getCount() {
      return count;
    }

    Instant getLastCallTime() {
      return lastCallTime;
    }

    void updateLastCallTime() {
      lastCallTime = Instant.now();
    }

    void incrementCount() {
      count += 1;
    }
  }
}
