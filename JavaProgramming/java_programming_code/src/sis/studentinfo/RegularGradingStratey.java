package sis.studentinfo;

public class RegularGradingStratey implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        if (grade == Student.Grade.A) return 4;
        if (grade == Student.Grade.B) return 3;
        if (grade == Student.Grade.C) return 2;
        if (grade == Student.Grade.D) return 1;
        return 0;
    }
}
