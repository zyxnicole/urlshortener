package com.nicole.urlshortener.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Sessions {

  private static final Map<String, String> ADMINS = initAdmin();

  Map<UUID, String> activeSessions = new HashMap<>();

  public boolean validate(String sid) {
    UUID uuid;
    try {
      uuid = UUID.fromString(sid);
    } catch (Exception e) {
      return false;
    }

    return activeSessions.containsKey(uuid);
  }

  public String login(String username, String password) {
    if (!ADMINS.containsKey(username)) {
      return null;
    }
    if (!ADMINS.get(username).equals(password)) {
      return null;
    }
    UUID uuid = UUID.randomUUID();
    activeSessions.put(uuid, username);
    return uuid.toString();
  }

  public void logout(String sid) {
    activeSessions.remove(UUID.fromString(sid));
  }

  private static Map<String, String> initAdmin() {
    Map<String, String> map = new HashMap<>();
    map.put("admin", "password");
    map.put("nicole", "123456");
    return map;
  }
}
