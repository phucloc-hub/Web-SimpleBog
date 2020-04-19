/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loctp.simpleblog;

import java.io.Serializable;

/**
 *
 * @author Loc
 */
public class AccountCreateErrors implements Serializable{
    private String userNameLength;
    private String passwordLength;
    private String emailLength;
    private String confirmNotMatchPassword;
    private String emailDup;
    private String emailFormat;

    public String getEmailFormat() {
        return emailFormat;
    }

    public void setEmailFormat(String emailFormat) {
        this.emailFormat = emailFormat;
    }
    

    public AccountCreateErrors() {
    }

    public String getUserNameLength() {
        return userNameLength;
    }

    public void setUserNameLength(String userNameLength) {
        this.userNameLength = userNameLength;
    }

    public String getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(String passwordLength) {
        this.passwordLength = passwordLength;
    }

    public String getEmailLength() {
        return emailLength;
    }

    public void setEmailLength(String emailLength) {
        this.emailLength = emailLength;
    }

    public String getConfirmNotMatchPassword() {
        return confirmNotMatchPassword;
    }

    public void setConfirmNotMatchPassword(String confirmNotMatchPassword) {
        this.confirmNotMatchPassword = confirmNotMatchPassword;
    }


    public String getEmailDup() {
        return emailDup;
    }

    public void setEmailDup(String emailDup) {
        this.emailDup = emailDup;
    }
    
    
    
    
    
}
