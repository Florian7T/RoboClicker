package robot.instructions;

import robot.Instruction;
import robot.InstructionSet;
import window.MainWindow;

public class MouseClickInstruction extends Instruction {
    /* buttons:
    0:
    1: left click
    2: scroll click
    3: right click
    4: mouse 4
    5: mouse 5
    */
    public int button;
    public int time = 0;
    public boolean hold;
    public MouseClickInstruction(InstructionSet instructionSet, int button, boolean hold) {
        super(instructionSet);
        this.button = button;
        this.hold = hold;
    }
    public MouseClickInstruction(InstructionSet instructionSet, int button, int time, boolean wait) {
        super(instructionSet);
        this.hold = wait;
        this.button = button;
        this.time = time;
    }

    @Override
    public void execute() {
        if (time > 0){
            if (hold){
                try {
                    MainWindow.robot.mousePress(button);
                    Thread.sleep(time);
                    MainWindow.robot.mouseRelease(button);
                } catch (InterruptedException interruptedException) {
                    instructionSet.error("MouseClickInstruction: Sleep was Interrupted while sync holding button ("+button+")" ,true);
                    interruptedException.printStackTrace();
                }
            }else {
                new Thread(() -> {
                    try {
                        MainWindow.robot.mousePress(button);
                        Thread.sleep(time);
                        MainWindow.robot.mouseRelease(button);
                    } catch (InterruptedException interruptedException) {
                        instructionSet.error("MouseClickInstruction: Sleep was Interrupted while async holding button ("+button+")" ,true);
                        interruptedException.printStackTrace();
                    }
                }).start();
            }
        } else if (hold){

        } else {

        }
    }
}
