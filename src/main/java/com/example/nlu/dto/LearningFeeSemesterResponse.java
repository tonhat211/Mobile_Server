package com.example.nlu.dto;

import com.example.nlu.model.LearningFee;
import com.example.nlu.model.SemesterScore;
import com.example.nlu.model.SubjectScore;
import com.example.nlu.model.TotalScore;

import java.util.List;

public class LearningFeeSemesterResponse {
   private LearningFee learningFee;
   private double money;

    public LearningFeeSemesterResponse(LearningFee learningFee) {
        this.learningFee = learningFee;
        this.money = learningFee.getSubject().getCredit() * learningFee.getPriceUnit() - learningFee.getDiscountMoney();
    }

    public LearningFee getLearningFee() {
        return learningFee;
    }

    public void setLearningFee(LearningFee learningFee) {
        this.learningFee = learningFee;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}