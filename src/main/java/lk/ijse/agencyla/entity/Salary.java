package lk.ijse.agencyla.entity;

public class Salary {
    private String salaryId;
    private String empId;
    private String name;
    private String month;
    private double amount;
    private String date;

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId='" + salaryId + '\'' +
                ", empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", month='" + month + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }

    public String getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Salary(String salaryId, String empId, String name, String month, double amount, String date) {
        this.salaryId = salaryId;
        this.empId = empId;
        this.name = name;
        this.month = month;
        this.amount = amount;
        this.date = date;
    }
}
