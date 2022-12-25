package robot.instructions;

import com.sun.tools.javac.Main;
import robot.Instruction;
import robot.InstructionSet;
import window.MainWindow;

import java.awt.*;

public class MoveMouseInstruction extends Instruction {
    public int time = 0;
    public Point pos;
    public boolean fromPos;
    public MoveMouseInstruction(InstructionSet instructionSet, Point pos, boolean fromPos) {
        super(instructionSet);
        this.pos = pos;
        this.fromPos = fromPos;
    }

    public MoveMouseInstruction(InstructionSet instructionSet, Point pos, boolean fromPos, int time) {
        super(instructionSet);
        this.pos = pos;
        this.fromPos = fromPos;
        this.time = time;
    }

    @Override
    public void execute() {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        if (time > 0){
            try {
                final double dist = Math.sqrt(Math.pow(fromPos ? pos.x : mousePos.x - pos.x, 2) + Math.pow(fromPos ? pos.y : mousePos.y - pos.y, 2));
                final int steps = (int) Math.ceil(dist / (Math.ceil(time / 5D)));
                final int stepX = mousePos.x - pos.x / steps;
                final int stepY = mousePos.y - pos.y / steps;
                while (time > -5) {
                    mousePos = MouseInfo.getPointerInfo().getLocation();
                    MainWindow.robot.mouseMove(mousePos.x + stepX, mousePos.y + stepY);
                    time -= 5;
                    Thread.sleep(5);
                }
            }catch (InterruptedException interruptedException){
                instructionSet.error("MouseMoveInstruction: Sleep Interrupted during timed MouseMove!",true);
            }
        } else if(fromPos) MainWindow.robot.mouseMove(mousePos.x+pos.x,mousePos.y+pos.y);
        else MainWindow.robot.mouseMove(pos.x,pos.y);
    }
}
