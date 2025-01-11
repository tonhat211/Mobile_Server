package com.example.nlu.dto;

import com.example.nlu.model.LearningFee;
import com.example.nlu.model.Semester;

import java.util.List;

public class LearningFeeAllResponse {
   private List<LearningFee> learningFees;
   private Semester semester;
   private double discountMoney;
   private double money;
   private double tempMoney;
   private int isPay;

    public LearningFeeAllResponse(Semester semester,List<LearningFee> learningFees) {
        this.semester = semester;
        this.learningFees = learningFees;
        this.discountMoney = 0.0;
        this.money = 0.0;
        this.tempMoney=0.0;
        boolean isPayBoolean = true;
        System.out.println("Semester: " + semester.getId());
        System.out.println("learningFees: " + learningFees.size());
        for(LearningFee learningFee : learningFees) {
            System.out.println("subjectID: " + learningFee.getSubject().getId());
            discountMoney +=learningFee.getDiscountMoney();
            tempMoney+= learningFee.getSubject().getCredit() * learningFee.getPriceUnit();
            if(learningFee.getIsPay()==0) isPayBoolean=false;
        }
        System.out.println("temp:" + tempMoney);
        System.out.println("discountMoney:" + discountMoney);
        money = tempMoney- discountMoney;
        System.out.println("money:" + money);
        if(isPayBoolean) this.isPay=1;
        else this.isPay=0;
    }

    public double getTempMoney() {
        return tempMoney;
    }

    public void setTempMoney(double tempMoney) {
        this.tempMoney = tempMoney;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public List<LearningFee> getLearningFees() {
        return learningFees;
    }

    public void setLearningFees(List<LearningFee> learningFees) {
        this.learningFees = learningFees;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}