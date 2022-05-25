package Data;

import java.io.Serializable;

public class Assignments implements Serializable {
    private String name, type, description, commentE, commentP, state, professor;
    private String subject, assignmentDate, tosubmitDate, submittedDate;
    private double score;
    
    public Assignments(String name, String type, String description, String commentE, String commentP, String state, String professor, String subject,
            String assignmentDate, String tosubmitDate, String submittedDate, double score) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.commentE = commentE;
        this.commentP = commentP;
        this.state = state;
        this.professor = professor;
        this.subject = subject;
        this.assignmentDate = assignmentDate;
        this.tosubmitDate = tosubmitDate;
        this.submittedDate = submittedDate;
        this.score = score;
    }
    public Assignments(){ }

    public String getName() {
        return name;
    }
    
    public String getAssignmentDate() {
        return assignmentDate;
    }
    public void setAssignmentDate(String assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCommentE() {
        return commentE;
    }
    
    public void setCommentE(String commentE) {
        this.commentE = commentE;
    }
    public String getCommentP() {
        return commentP;
    }
    public void setCommentP(String commentP) {
        this.commentP = commentP;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTosubmitDate() {
        return tosubmitDate;
    }

    public void setTosubmitDate(String tosubmitDate) {
        this.tosubmitDate = tosubmitDate;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
