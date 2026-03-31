package com.mycompany.assignment2.listener;

import com.mycompany.assignment2.dao.StatsDAO;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    private StatsDAO statsDAO = new StatsDAO();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        // Since we don't have request object here to get IP/User-Agent, 
        // we might need to store it in session later or capture it via a Filter/Servlet on first request.
        // However, requirement says "records all session ID of the users in the database using Listeners"
        // So we record what we can.
        
        // Note: IP/User-Agent are request specific, not session specific in creation event.
        // We will log the session creation here with placeholders or wait for attribute binding.
        // Or simpler: The requirements usually imply just tracking the existence.
        // But I put IP/User-Agent in the table. I will defer logging to the first request in a Filter if possible,
        // OR just log session ID here.
        
        // Workaround: We will log just the session ID here or use 'Unknown' for others.
        // Actually, a better approach for "tracking every body who visits" is a filter that logs on first visit per session.
        // But requirement specifically mentions "Listeners".
        
        statsDAO.logSession(sessionId, "Unknown", "Unknown");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
