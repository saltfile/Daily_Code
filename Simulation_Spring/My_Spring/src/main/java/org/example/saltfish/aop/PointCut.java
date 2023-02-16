package org.example.saltfish.aop;

public class PointCut {

    private String ClassPattern;

    private String methodPattern;

    public String getClassPattern() {
        return ClassPattern;
    }

    public void setClassPattern(String classPattern) {
        ClassPattern = classPattern;
    }

    public String getMethodPattern() {
        return methodPattern;
    }

    public void setMethodPattern(String methodPattern) {
        this.methodPattern = methodPattern;
    }

    public PointCut(String classPattern, String methodPattern) {
        super();
        this.ClassPattern = classPattern;
        this.methodPattern = methodPattern;
    }
}
