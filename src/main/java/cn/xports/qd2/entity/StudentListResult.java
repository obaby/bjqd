package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class StudentListResult extends Response implements Serializable {
    private List<StudentInfo> studentList;

    public List<StudentInfo> getStudentList() {
        return this.studentList;
    }

    public void setStudentList(List<StudentInfo> list) {
        this.studentList = list;
    }
}
