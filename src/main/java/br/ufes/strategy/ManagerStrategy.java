
package br.ufes.strategy;


public class ManagerStrategy {
   
    private EmployeeStategy log;

    public ManagerStrategy(EmployeeStategy log) {
        this.log = log;
    }
    
    public void setLog(EmployeeStategy log){
        this.log=log;
    }
    
    public EmployeeStategy getLog(){
        return this.log;
    } 
    
    
}
