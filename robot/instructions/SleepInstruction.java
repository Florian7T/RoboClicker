package robot.instructions;

import robot.Instruction;
import robot.InstructionSet;

public class SleepInstruction extends Instruction {
    public int delay;
    public SleepInstruction (InstructionSet instructionSet, int i){
        super(instructionSet);
        this.delay = i;
    }

    @Override
    public void execute() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            instructionSet.error("SleepInstruction: Sleep was interrupted!", true);
            e.printStackTrace();
        }
    }
}
