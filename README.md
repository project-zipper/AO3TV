# FixTok Rework

FixTok is an unofficial TikTok client for Android TV / Google TV, built around a
single full-screen WebView that shows TikTok's desktop website.

## Features

- 🖥️ Desktop version of TikTok (desktop Chrome user agent), scaled to fill the screen
- 🎮 D-pad, OK, Back, Menu and Play/Pause handling
- 🖱️ Optional virtual pointer for pages that need a mouse (toggle in the menu)
- 🔐 Sign-in happens on TikTok's own pages; FixTok never sees or stores passwords
- 🍪 Session is kept between launches; "Sign out and clear data" removes it
- 🎬 HTML5 fullscreen video support
- 🚀 Splash screen
- 👤 by Manas

## Remote control

| Remote button | Action |
|---|---|
| D-pad / OK | Move focus and click inside TikTok (or move and click the pointer when it is on) |
| Back | Close the menu or fullscreen video, go back a page, press twice on the first page to exit |
| Menu | Open or close the control panel |
| Play / Pause | Play or pause the most visible video |
| Volume | Handled by the system |

## Control panel (Menu)

- TikTok home, Refresh, Back, Forward
- Turn pointer on / off
- Play / pause video
- Sign in on TikTok (opens TikTok's login page)
- Sign out and clear data (removes all TikTok cookies and site data after confirmation)

## Security

- Only TikTok and the sign-in providers it uses (Google, Apple, Facebook) load
  inside the app. Other web links open in the device's browser. Host checks use
  the parsed URI host, never substring matching.
- TLS certificate errors are never bypassed.
- Camera, microphone and location requests from web pages are denied.
- Safe Browsing stays enabled.
- No JavaScript bridge; no secrets in URLs or logs.

## Technology

- Kotlin · Android TV / Google TV · WebView · Gradle

## Architecture

```text
FixTok
  ├── MainActivity     — screen, remote keys, menu, error card, splash
  ├── TikTokClients    — WebView client (navigation policy, errors) and chrome client (fullscreen video, permissions)
  ├── VirtualPointer   — optional on-screen pointer driven by the D-pad
  ├── SiteData         — clears cookies, storage and cache on sign-out
  └── Policy           — pure rules (domains, user agent, scale, session check), unit-tested
```

## Support

Donations (USDT): `0xf3582caCE9450d8A9d9291D359Af75c89FC86C75`

Thank you for supporting FixTok! ❤️
