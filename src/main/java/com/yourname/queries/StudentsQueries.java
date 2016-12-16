package com.yourname.queries;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "yourname.database.students.query")
@Component
public class StudentsQueries {

    private String selectallbyid;
    private String delete;
    private String update;
    private String insert;
    private String selectall;

    public String getSelectallbyid() {
        return selectallbyid;
    }

    public void setSelectallbyid(String selectallbyid) {
        this.selectallbyid = selectallbyid;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getSelectall() {
        return selectall;
    }

    public void setSelectall(String selectall) {
        this.selectall = selectall;
    }
}
