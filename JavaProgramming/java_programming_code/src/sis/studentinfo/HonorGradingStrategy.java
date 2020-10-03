package sis.studentinfo;

public class HonorGradingStrategy
        extends BasicGradingStrategy
        implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointsFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }
}

