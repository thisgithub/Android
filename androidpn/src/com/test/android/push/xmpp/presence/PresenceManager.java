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
package com.test.android.push.xmpp.presence;

import org.xmpp.packet.Presence;

import com.test.android.push.pojo.ApnUser;
import com.test.android.push.xmpp.session.ClientSession;
import com.test.android.push.xmpp.session.SessionManager;

/** 
 * This class is to manage the presences of users. 
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class PresenceManager {

    private SessionManager sessionManager;

    /**
     * Constructor.
     */
    public PresenceManager() {
        sessionManager = SessionManager.getInstance();
    }

    /**
     * Returns the availability of the user.
     * 
     * @param user the user
     * @return true if the user is available
     */
    public boolean isAvailable(ApnUser user) {
        return sessionManager.getSession(user.getUsername()) != null;
    }

    /**
     * Returns the current presence of the user.
     * 
     * @param user the user
     * @return the current presence of the user.
     */
    public Presence getPresence(ApnUser user) {
        if (user == null) {
            return null;
        }
        Presence presence = null;
        ClientSession session = sessionManager.getSession(user.getUsername());
        if (session != null) {
            presence = session.getPresence();
        }
        return presence;
    }

}
