package robot;

public class Instruction {
    public InstructionSet instructionSet;
    public Instruction(InstructionSet instructionSet){
        this.instructionSet = instructionSet;
    }

    public void execute(){ }

    public enum Instructions{
        MOUSE_CLICK,
        MOUSE_MOVE,
        KEYBOARD_EVENT,
        COMMAND_EVENT,
        SLEEP
    }
}
