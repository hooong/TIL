package sis.report;

import sis.studentinfo.CourseSession;

import java.util.ArrayList;
import java.util.Collections;

import static sis.report.RosterReporter.NEWLINE;

public class CourseReport {
    private ArrayList<CourseSession> sessions = new ArrayList<>();

    public void add(CourseSession session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder stringBuilder = new StringBuilder();
        for (CourseSession session: sessions) {
            stringBuilder.append(
                    session.getDepartment() + " " +
                            session.getNumber() + NEWLINE);
        }
        return stringBuilder.toString();
    }
}