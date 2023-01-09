package src.customers;

import java.time.LocalDate;

public class Employment {
    private String employerName;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int yearlySalary;

    public Employment(String employerName, int yearlySalary) {
        this(employerName, "Title", LocalDate.now(), LocalDate.now(), yearlySalary);
    }
    public Employment(String employerName, String title, LocalDate startDate, LocalDate endDate, int yearlySalary) {
        this.employerName = employerName;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.yearlySalary = yearlySalary;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(int yearlySalary) {
        this.yearlySalary = yearlySalary;
    }
}
