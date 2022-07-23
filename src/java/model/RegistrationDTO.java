/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Account;
import model.PricePackage;
import model.Subject;

@Builder
@Getter
@Setter
@ToString
/**
 *
 * @author yentt
 */
public class RegistrationDTO implements Serializable {

    private int regisId;
    private String regisDate;
    private boolean status;
    private Subject subject;
    private int subjectId;
    private PricePackage pricePackage;
    private int pricePackageId;
    private Account account;
    private int accountId;

    public RegistrationDTO() {
    }

    public RegistrationDTO(int regisId, String regisDate, boolean status, Subject subject, int subjectId, PricePackage pricePackage, int pricePackageId, Account account, int accountId) {
        this.regisId = regisId;
        this.regisDate = regisDate;
        this.status = status;
        this.subject = subject;
        this.subjectId = subjectId;
        this.pricePackage = pricePackage;
        this.pricePackageId = pricePackageId;
        this.account = account;
        this.accountId = accountId;
    }

    public int getRegisId() {
        return regisId;
    }

    public void setRegisId(int regisId) {
        this.regisId = regisId;
    }

    public String getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(String regisDate) {
        this.regisDate = regisDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public PricePackage getPricePackage() {
        return pricePackage;
    }

    public void setPricePackage(PricePackage pricePackage) {
        this.pricePackage = pricePackage;
    }

    public int getPricePackageId() {
        return pricePackageId;
    }

    public void setPricePackageId(int pricePackageId) {
        this.pricePackageId = pricePackageId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}
