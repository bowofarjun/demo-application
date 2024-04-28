package in.ac.bitspilani.wilp.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppDetail {
    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.application.description}")
    private String appDescription;
    @Value("${spring.application.developer.name}")
    private String devName;
    @Value("${spring.application.developer.email}")
    private String devEmail;

    public AppDetail(){

    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevEmail() {
        return devEmail;
    }

    public void setDevEmail(String devEmail) {
        this.devEmail = devEmail;
    }

    @Override
    public String toString() {
        return "AppDetail{" +
                "appName='" + appName + '\'' +
                ", appDescription='" + appDescription + '\'' +
                ", devName='" + devName + '\'' +
                ", devEmail='" + devEmail + '\'' +
                '}';
    }
}
