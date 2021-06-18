package com.nicole.urlshortener.controller;

import com.nicole.urlshortener.auth.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

  private final Sessions sessions;

  @Autowired
  public AuthController(Sessions sessions) {
    this.sessions = sessions;
  }

  @GetMapping(value = "")
  public Map<String, String> check(
      @CookieValue(value = "sid", defaultValue = "") String sid,
      HttpServletResponse responseHead) {
    if (!sessions.validate(sid)) {
      responseHead.setStatus(HttpServletResponse.SC_FORBIDDEN);
      Cookie cookie = new Cookie("sid", null);
      cookie.setMaxAge(0);
      responseHead.addCookie(cookie);
    }
    return new HashMap<>();
  }

  @PostMapping(value = "")
  public Map<String, String> login(
      @RequestBody Map<String, String> body,
      HttpServletResponse responseHead) {
    Map<String, String> responseBody = new HashMap<>();

    String sid = sessions.login(body.get("username"), body.get("password"));
    if (sid == null) {
      responseHead.setStatus(HttpServletResponse.SC_FORBIDDEN);
      responseBody.put("error", "login-failed");
    } else {
      responseHead.addCookie(new Cookie("sid", sid));
    }
    return responseBody;
  }

  @DeleteMapping(value = "")
  public Map<String, String> logout(
      @CookieValue(value = "sid", defaultValue = "") String sid,
      HttpServletResponse responseHead) {
    sessions.logout(sid);
    Cookie cookie = new Cookie("sid", null);
    cookie.setMaxAge(0);
    responseHead.addCookie(cookie);
    return new HashMap<>();
  }
}
