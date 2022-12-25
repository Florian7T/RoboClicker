package robot.instructions;

import robot.Instruction;
import robot.InstructionSet;
import window.MainWindow;

import java.io.IOException;

public class CommandInstruction extends Instruction {
    public String command;
    public boolean useRuntime = true;
    public CommandInstruction(InstructionSet instructionSet, String command) {
        super(instructionSet);
        this.command = command;
    }

    public CommandInstruction(InstructionSet instructionSet, String command, boolean useRuntime) {
        super(instructionSet);
        this.command = command;
        this.useRuntime = useRuntime;
    }

    @Override
    public void execute() {
        try {
            if (useRuntime) Runtime.getRuntime().exec(this.command);
            else {
                ProcessBuilder builder = new ProcessBuilder();
                if(MainWindow.isWindows) builder.command("cmd.exe","/c",this.command);
                else builder.command("sh","-c",this.command);
                builder.start();
            }
        } catch (IOException e) {
            instructionSet.error("CommandInstruction: IOException while trying to execute command!", true);
            e.printStackTrace();
        }
    }
}
