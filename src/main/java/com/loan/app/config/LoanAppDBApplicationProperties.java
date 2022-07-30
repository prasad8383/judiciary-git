package com.loan.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanAppDBApplicationProperties {
    @Value(("${mysql.driver}"))
    private String driverName;

    @Value(("${mysql.url}"))
    private String url;

    @Value(("${mysql.user}"))
    private String userName;

    @Value(("${mysql.password}"))
    private String password;

    @Value(("${mysql.dialect}"))
    private String dialect;

    @Value(("${mysql.hbm2ddl.auto}"))
    private String hbm2ddlAuto;

    @Value(("${mysql.show.ddl}"))
    private String showDDL;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getHbm2ddlAuto() {
        return hbm2ddlAuto;
    }

    public void setHbm2ddlAuto(String hbm2ddlAuto) {
        this.hbm2ddlAuto = hbm2ddlAuto;
    }

    public String getShowDDL() {
        return showDDL;
    }

    public void setShowDDL(String showDDL) {
        this.showDDL = showDDL;
    }
}
