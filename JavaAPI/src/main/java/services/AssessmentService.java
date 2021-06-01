package services;

import dao.AssessmentDAO;
import dao.AssessmentDAOImpl;
import models.Assessment;
import models.Type;

import java.sql.SQLException;
import java.util.List;

public class AssessmentService {
    AssessmentDAO assessmentDAO = new AssessmentDAOImpl();

    public List<Assessment> getAssessments() throws SQLException {
        List<Assessment> assessments = assessmentDAO.getAssessments();
        return assessments;
    }

    public List<Assessment> getWeekAssessments(String weekId, int batchId) throws SQLException {
        List<Assessment> weekAssessments = assessmentDAO.getWeekAssessments(weekId, batchId);
        return weekAssessments;

    }

    public Assessment createAssessment(Assessment assessment) throws SQLException {
        return assessmentDAO.createAssessment(assessment.getWeekId(), assessment.getBatchId());
    }

    public boolean adjustWeight(int assessmentId, int weight) throws SQLException {
        return assessmentDAO.adjustWeight(assessmentId, weight);
    }

    public Type createAssessmentType(Type type) throws SQLException {
        return assessmentDAO.createAssessmentType(type.getName(), type.getDefaultWeight());
    }

}
