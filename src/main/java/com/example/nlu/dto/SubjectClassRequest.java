package com.example.nlu.dto;

import com.example.nlu.model.Subject;
import com.example.nlu.model.SubjectClass;

public class SubjectClassRequest {
    private long subjectClassID;
    private int qty;
    private SubjectClass subjectClass;
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectClass getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(SubjectClass subjectClass) {
        this.subjectClass = subjectClass;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getSubjectClassID() {
        return subjectClassID;
    }

    public void setSubjectClassID(long subjectClassID) {
        this.subjectClassID = subjectClassID;
    }
}
