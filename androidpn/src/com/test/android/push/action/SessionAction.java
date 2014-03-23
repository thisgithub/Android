package com.test.android.push.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.xmpp.packet.Presence;

import com.sun.net.httpserver.Authenticator.Success;
import com.test.android.push.xmpp.session.ClientSession;
import com.test.android.push.xmpp.session.Session;
import com.test.android.push.xmpp.session.SessionManager;

@SuppressWarnings("unchecked")
@Namespace("/manage")
@ParentPackage("manage")
public class SessionAction extends BaseAction{

	private static final long serialVersionUID = 2400157355554510699L;
	private List<SessionVO> sessionList;

	@Action(value="list",results = { @Result(name = "success", location = "/android/session/list.jsp")})
	public String list() throws Exception {
		
		ClientSession[] sessions = new ClientSession[0];
        sessions = SessionManager.getInstance().getSessions().toArray(sessions);
        sessionList = new ArrayList<SessionVO>();
        for (ClientSession sess : sessions) {
            SessionVO vo = new SessionVO();
            vo.setUsername(sess.getUsername());
            vo.setResource(sess.getAddress().getResource());
            // Status
            if (sess.getStatus() == Session.STATUS_CONNECTED) {
                vo.setStatus("CONNECTED");
            } else if (sess.getStatus() == Session.STATUS_AUTHENTICATED) {
                vo.setStatus("AUTHENTICATED");
            } else if (sess.getStatus() == Session.STATUS_CLOSED) {
                vo.setStatus("CLOSED");
            } else {
                vo.setStatus("UNKNOWN");
            }
            // Presence
            if (!sess.getPresence().isAvailable()) {
                vo.setPresence("Offline");
            } else {
                Presence.Show show = sess.getPresence().getShow();
                if (show == null) {
                    vo.setPresence("Online");
                } else if (show == Presence.Show.away) {
                    vo.setPresence("Away");
                } else if (show == Presence.Show.chat) {
                    vo.setPresence("Chat");
                } else if (show == Presence.Show.dnd) {
                    vo.setPresence("Do Not Disturb");
                } else if (show == Presence.Show.xa) {
                    vo.setPresence("eXtended Away");
                } else {
                    vo.setPresence("Unknown");
                }
            }
            vo.setClientIP(sess.getHostAddress());
            vo.setCreatedDate(sess.getCreationDate());
            sessionList.add(vo);
        }

		return SUCCESS;
	}

	public List<SessionVO> getSessionList() {
		return sessionList;
	}
}
