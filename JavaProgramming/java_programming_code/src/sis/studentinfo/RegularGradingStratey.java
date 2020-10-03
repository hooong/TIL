package sis.studentinfo;

public class RegularGradingStratey
        extends BasicGradingStrategy
        implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        return basicGradePointsFor(grade);
    }
}
