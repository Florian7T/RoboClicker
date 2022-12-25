package robot;

import java.util.ArrayList;

public class InstructionSet {
    private ArrayList<Instruction> instructions;
    public Thread runThread;

    public InstructionSet(){
        instructions = new ArrayList<>();
    }

    public void addInstruction(Instruction instruction){
        instructions.add(instruction);
    }

    public void removeInstruction(int i){
        instructions.remove(i);
    }

    public Instruction getInstruction(int i){
        return instructions.get(i);
    }

    public ArrayList<Instruction> getInstructionArray(){
        return instructions;
    }

    public void error(String reason, boolean stop){

    }

    public static InstructionSet fromString(String setString){
        InstructionSet set = new InstructionSet();
        String[] split = setString.split(",");
        for (String s : split){

        }
        return set;
    }
}
