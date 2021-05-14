package com.nicole.urlshortener.controller;

import com.nicole.urlshortener.auth.Sessions;
import com.nicole.urlshortener.service.UrlShortener;
import com.nicole.urlshortener.service.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/url")
public class UrlShortenerController {

  private final Sessions sessions;
  private final UrlShortener urlShortener;

  @Autowired
  public UrlShortenerController(Sessions sessions, UrlShortener urlShortener) {
    this.sessions = sessions;
    this.urlShortener = urlShortener;
  }

  @PostMapping(value = "")
  public Map<String, String> shorten(
          @RequestBody Map<String, String> body,
          HttpServletResponse responseHead) {
    Map<String, String> response = new HashMap<>();
    String longUrl = body.getOrDefault("longUrl", "");
    if (!UrlValidator.validateLongUrl(longUrl)) {
      responseHead.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.put("error", "invalid-long-url");
      return response;
    }

    response.put("shortUrl", urlShortener.shorten(longUrl));
    return response;
  }

  @GetMapping(value = "")
  public Map<String, String> retrieve(
          @RequestParam(name = "shortUrl") String shortUrl,
          HttpServletResponse responseHead) {
    Map<String, String> response = new HashMap<>();
    if (!UrlValidator.validateShortUrl(shortUrl)) {
      responseHead.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.put("error", "invalid-short-url");
      return response;
    }

    response.put("longUrl", urlShortener.retrieve(shortUrl));
    return response;
  }

  @GetMapping(value = "/dump")
  public Map<String, List<Map<String, String>>> dump(
      @CookieValue(value = "sid", defaultValue = "") String sid, HttpServletResponse responseHead) { ;
    Map<String, List<Map<String, String>>> response = new HashMap<>();
    if (!sessions.validate(sid)) {
      responseHead.setStatus(HttpServletResponse.SC_FORBIDDEN);
      return response;
    }

    response.put("data", urlShortener.dump().stream()
        .map(snapshot -> {
            Map<String, String> map = new HashMap<>();
            map.put("longUrl", snapshot.longUrl());
            map.put("shortUrl", snapshot.shortUrl());
            map.put("count", Long.toString(snapshot.count()));
            map.put("lastCallTime", snapshot.lastCallTime().toString());
            return map;
          })
        .collect(Collectors.toList()));
    return response;
  }
}
