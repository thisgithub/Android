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
package com.test.android.push.xmpp.handler;

import gnu.inet.encoding.StringprepException;

import java.util.Date;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;
import org.xmpp.packet.PacketError;

import com.test.android.push.pojo.ApnUser;
import com.test.android.push.service.AndroidService;
import com.test.android.push.service.ServiceLocator;
import com.test.android.push.xmpp.UnauthorizedException;
import com.test.android.push.xmpp.UserExistsException;
import com.test.android.push.xmpp.UserNotFoundException;
import com.test.android.push.xmpp.session.ClientSession;
import com.test.android.push.xmpp.session.Session;

/** 
 * This class is to handle the TYPE_IQ jabber:iq:register protocol.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class IQRegisterHandler extends IQHandler {

    private static final String NAMESPACE = "jabber:iq:register";

    private AndroidService androidService;
    private Element probeResponse;

    /**
     * Constructor.
     */
    public IQRegisterHandler() {
    	androidService = ServiceLocator.getUserService();
        probeResponse = DocumentHelper.createElement(QName.get("query",
                NAMESPACE));
        probeResponse.addElement("username");
        probeResponse.addElement("password");
        probeResponse.addElement("userId");
    }

    /**
     * Handles the received IQ packet.
     * 
     * @param packet the packet
     * @return the response to send back
     * @throws UnauthorizedException if the user is not authorized
     */
    public IQ handleIQ(IQ packet) throws UnauthorizedException {
    	log.debug("----handleIQ start-----------------");
        IQ reply = null;

        ClientSession session = sessionManager.getSession(packet.getFrom());
        
        Element query = packet.getChildElement();
        if (query.element("logoutUserId") != null) {
        	 String logoutUserId = query.elementText("logoutUserId");
        	 log.debug("--下线后的logoutUserId--" + logoutUserId);
        	 try {
        		 ApnUser user = new ApnUser();
        		 user.setId(Long.parseLong(logoutUserId));
        		 user.setOnline(0);
				androidService.updateAndroidPns(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        if (session == null) {
            log.error("Session not found for key " + packet.getFrom());
            reply = IQ.createResultIQ(packet);
            reply.setChildElement(packet.getChildElement().createCopy());
            reply.setError(PacketError.Condition.internal_server_error);
            return reply;
        }

        if (IQ.Type.get.equals(packet.getType())) {
            reply = IQ.createResultIQ(packet);
            if (session.getStatus() == Session.STATUS_AUTHENTICATED) {
                // TODO
            } else {
                reply.setTo((JID) null);
                reply.setChildElement(probeResponse.createCopy());
            }
        } else if (IQ.Type.set.equals(packet.getType())) {
            try {
//                Element query = packet.getChildElement();
                if (query.element("remove") != null) {
                    if (session.getStatus() == Session.STATUS_AUTHENTICATED) {
                        // TODO
                    } else {
                        throw new UnauthorizedException();
                    }
                } else {
                    String username = query.elementText("username");
                    String password = query.elementText("password");
                    String userId = query.elementText("userId");

                    ApnUser user = new ApnUser();

                    log.debug("----cs android推送----");
                    log.debug("username = "+username);
                    log.debug("userId = "+userId);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setCreateDate(new Date());
                    user.setId(Long.parseLong(userId));
                    user.setOnline(1);
                    androidService.updateAndroidPns(user);
                    reply = IQ.createResultIQ(packet);
                }
            } catch (Exception ex) {
                log.error(ex);
                reply = IQ.createResultIQ(packet);
                reply.setChildElement(packet.getChildElement().createCopy());
                if (ex instanceof UserExistsException) {
                    reply.setError(PacketError.Condition.conflict);
                } else if (ex instanceof UserNotFoundException) {
                    reply.setError(PacketError.Condition.bad_request);
                } else if (ex instanceof StringprepException) {
                    reply.setError(PacketError.Condition.jid_malformed);
                } else if (ex instanceof IllegalArgumentException) {
                    reply.setError(PacketError.Condition.not_acceptable);
                } else {
                    reply.setError(PacketError.Condition.internal_server_error);
                }
            }
        }

        // Send the response directly to the session
        if (reply != null) {
            session.process(reply);
        }
        log.debug("----handleIQ end-----------------");
        return null;
    }

    /**
     * Returns the namespace of the handler.
     * 
     * @return the namespace
     */
    public String getNamespace() {
        return NAMESPACE;
    }

}
