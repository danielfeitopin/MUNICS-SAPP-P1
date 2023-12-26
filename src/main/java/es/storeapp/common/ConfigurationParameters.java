package es.storeapp.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("server.cofiguration")
public class ConfigurationParameters {
    
    private String resources;
    private String mailHost;
    private Integer mailPort;
    private String mailUserName;
    private String mailPassword;
    private Boolean mailSslEnable;
    private Boolean mailStartTlsEnable;
    private String mailFrom;

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public Integer getMailPort() {
        return mailPort;
    }

    public void setMailPort(Integer mailPort) {
        this.mailPort = mailPort;
    }

    public String getMailUserName() {
        return mailUserName;
    }

    public void setMailUserName(String mailUserName) {
        this.mailUserName = mailUserName;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public Boolean getMailSslEnable() {
        return mailSslEnable;
    }

    public void setMailSslEnable(Boolean mailSslEnable) {
        this.mailSslEnable = mailSslEnable;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public Boolean getMailStartTlsEnable() {
        return mailStartTlsEnable;
    }

    public void setMailStartTlsEnable(Boolean mailStartTlsEnable) {
        this.mailStartTlsEnable = mailStartTlsEnable;
    }

    
}
