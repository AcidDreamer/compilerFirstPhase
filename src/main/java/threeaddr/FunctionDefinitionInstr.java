/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threeaddr;

/**
 *
 * @author mitsos
 */
public class FunctionDefinitionInstr implements Instruction{

    private String functionName;
    
    public FunctionDefinitionInstr(String functionName){
        this.functionName=functionName;
    }
       public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    
    
    @Override
    public String emit(){
        return " -- " + functionName + " -- ";
    }

 
}
