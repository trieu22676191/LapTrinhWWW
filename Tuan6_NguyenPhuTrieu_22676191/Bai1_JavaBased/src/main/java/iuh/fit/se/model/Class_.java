package iuh.fit.se.model;

public class Class_ {
    private String classId;
    private String className;

    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    @Override
    public String toString() {
        return "Class [classId=" + classId + ", className=" + className + "]";
    }
}