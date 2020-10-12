package sis.report;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Session;

import java.util.ArrayList;
import java.util.Collections;

import static sis.report.RosterReporter.NEWLINE;

public class CourseReport {
    private ArrayList<Session> sessions = new ArrayList<>();

    public void add(Session session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder stringBuilder = new StringBuilder();
        for (Session session: sessions) {
            stringBuilder.append(
                    session.getDepartment() + " " +
                            session.getNumber() + NEWLINE);
        }
        return stringBuilder.toString();
    }
}