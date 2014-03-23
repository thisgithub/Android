/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.test.android.push.xmpp.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.android.push.xmpp.UnauthenticatedException;
import com.test.android.push.xmpp.XmppServer;

/** 
 * This class is to provide the methods associated with user authentication. 
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class AuthManager {

    private static final Log log = LogFactory.getLog(AuthManager.class);

    private static final Object DIGEST_LOCK = new Object();

    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            log.error("Internal server error", e);
        }
    }

    /**
     * Returns the user's password. 
     * 
     * @param username the username
     * @return the user's password
     * @throws UserNotFoundException if the your was not found
     */
//    public static String getPassword(String username)
//            throws UserNotFoundException {
//    	log.debug("getPassword(..) start...");
//    	ApnUser user = ServiceLocator.getUserService().getUserByUsername(username);
//    	if (user == null) {
//    		return "";
//    	} else {
//    		return user.getPassword();
//    	}
//    }

    /**
     * Authenticates a user with a username and plain text password, and
     * returns an AuthToken.
     * 
     * @param username the username
     * @param password the password
     * @return an AuthToken
     * @throws UnauthenticatedException if the username and password do not match
     */
    public static AuthToken authenticate(String username, String password)
            throws UnauthenticatedException {
    	log.debug("authenticate(..) start...");
        if (username == null || password == null) {
            throw new UnauthenticatedException();
        }
        username = username.trim().toLowerCase();
        if (username.contains("@")) {
            int index = username.indexOf("@");
            String domain = username.substring(index + 1);
            if (domain.equals(XmppServer.getInstance().getServerName())) {
                username = username.substring(0, index);
            } else {
                throw new UnauthenticatedException();
            }
        }
//        try {
//            if (!password.equals(getPassword(username))) {
//                throw new UnauthenticatedException();
//            }
//        } catch (UserNotFoundException unfe) {
//            throw new UnauthenticatedException();
//        }
        log.debug("authenticate(..) end...");
        return new AuthToken(username);
    }

    /**
     * Authenticates a user with a username, token, and digest, and returns
     * an AuthToken.
     * 
     * @param username the username
     * @param token the token
     * @param digest the digest
     * @return an AuthToken
     * @throws UnauthenticatedException if the username and password do not match 
     */
    public static AuthToken authenticate(String username, String token,
            String digest) throws UnauthenticatedException {
    	log.debug("authenticate() start...");
        if (username == null || token == null || digest == null) {
            throw new UnauthenticatedException();
        }
        username = username.trim().toLowerCase();
        if (username.contains("@")) {
            int index = username.indexOf("@");
            String domain = username.substring(index + 1);
            if (domain.equals(XmppServer.getInstance().getServerName())) {
                username = username.substring(0, index);
            } else {
                throw new UnauthenticatedException();
            }
        }
//        try {
//            String password = getPassword(username);
//            String anticipatedDigest = createDigest(token, password);
//            if (!digest.equalsIgnoreCase(anticipatedDigest)) {
//                throw new UnauthenticatedException();
//            }
//        } catch (UserNotFoundException unfe) {
//            throw new UnauthenticatedException();
//        }
        log.debug("authenticate() end...");
        return new AuthToken(username);
    }

    /**
     * Returns true if plain text password authentication is supported according to JEP-0078.
     * 
     * @return true if plain text password authentication is supported
     */
    public static boolean isPlainSupported() {
    	log.debug("isPlainSupported() ...");
        return true;
    }

    /**
     * Returns true if digest authentication is supported according to JEP-0078.
     * 
     * @return true if digest authentication is supported
     */
    public static boolean isDigestSupported() {
    	log.debug("isDigestSupported() ...");
        return true;
    }

//    private static String createDigest(String token, String password) {
//        synchronized (DIGEST_LOCK) {
//        	log.debug("createDigest() ...");
//            digest.update(token.getBytes());
//            return Hex.encodeHexString(digest.digest(password.getBytes()));
//        }
//    }

}
