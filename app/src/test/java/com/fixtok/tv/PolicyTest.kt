package com.fixtok.tv

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PolicyTest {

    @Test
    fun tiktokAndSubdomainsAreInApp() {
        assertTrue(Policy.isInAppHost("archiveofourown.org"))
        assertTrue(Policy.isInAppHost("archiveofourown.org"))
        assertTrue(Policy.isInAppHost("WWW.TikTok.com."))
    }

    @Test
    fun lookalikeHostsAreRejected() {
        assertFalse(Policy.isInAppHost("evilarchiveofourown.org"))
        assertFalse(Policy.isInAppHost("archiveofourown.org.evil.example"))
        assertFalse(Policy.isInAppHost("example.com/archiveofourown.org"))
        assertFalse(Policy.isInAppHost(null))
        assertFalse(Policy.isInAppHost(""))
    }

    @Test
    fun signInProvidersAreInApp() {
        assertTrue(Policy.isInAppHost("accounts.google.com"))
        assertTrue(Policy.isInAppHost("appleid.apple.com"))
        assertTrue(Policy.isInAppHost("m.facebook.com"))
        assertFalse(Policy.isInAppHost("google.com"))
    }

    @Test
    fun loginPathMatchesOnlyTheLoginRoute() {
        assertTrue(Policy.isLoginPath("/login"))
        assertTrue(Policy.isLoginPath("/login/"))
        assertFalse(Policy.isLoginPath("/login-help"))
        assertFalse(Policy.isLoginPath("/"))
        assertFalse(Policy.isLoginPath(null))
    }

    @Test
    fun httpSchemesOnly() {
        assertTrue(Policy.isHttpScheme("https"))
        assertTrue(Policy.isHttpScheme("HTTP"))
        assertFalse(Policy.isHttpScheme("intent"))
        assertFalse(Policy.isHttpScheme("javascript"))
        assertFalse(Policy.isHttpScheme(null))
    }

    @Test
    fun scaleFillsDesktopLayoutOnCommonTvSizes() {
        // 1080p TV at density 2.0 -> 960 dp wide -> 75 %
        assertEquals(75, Policy.scalePercent(1920, 2.0f))
        // 4K TV at density 2.0 -> 1920 dp wide -> 150 %
        assertEquals(150, Policy.scalePercent(3840, 2.0f))
        // 720p TV at density 1.0 -> 1280 dp wide -> 100 %
        assertEquals(100, Policy.scalePercent(1280, 1.0f))
    }

    @Test
    fun scaleIsClampedAndSafeForBadInput() {
        assertEquals(Policy.MIN_SCALE_PERCENT, Policy.scalePercent(100, 1.0f))
        assertEquals(Policy.MAX_SCALE_PERCENT, Policy.scalePercent(10000, 1.0f))
        assertEquals(100, Policy.scalePercent(0, 1.0f))
        assertEquals(100, Policy.scalePercent(1920, 0f))
    }

    @Test
    fun sessionCookieDetection() {
        assertTrue(Policy.hasSessionCookie("foo=1; sessionid=abc123; bar=2"))
        assertTrue(Policy.hasSessionCookie("sid_tt=xyz"))
        assertFalse(Policy.hasSessionCookie("sessionid=; other=1"))
        assertFalse(Policy.hasSessionCookie("mysessionid=abc"))
        assertFalse(Policy.hasSessionCookie(null))
        assertFalse(Policy.hasSessionCookie(""))
    }
}
