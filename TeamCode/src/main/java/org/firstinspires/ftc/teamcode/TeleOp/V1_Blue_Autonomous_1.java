//RightSide

package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;
import org.firstinspires.ftc.teamcode.R;
//import org.firstinspires.ftc.teamcode.R;
import static java.lang.Thread.sleep;

import java.lang.*;

@Autonomous(name="V1_Blue_Autonomous_1", group="Autonomous")
public class V1_Blue_Autonomous_1 extends LinearOpMode {

    EncoderFunction robot = new EncoderFunction();
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontHorizontal = null;
    private DcMotor BackHorizontal = null;
    private DcMotor LeftVertical = null;
    private DcMotor RightVertical = null;
    private DcMotor LinearSlide = null;

    private Servo intakeClaw;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        FrontHorizontal = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        BackHorizontal = hardwareMap.get(DcMotor.class, "BackHorizontal");
        LeftVertical = hardwareMap.get(DcMotor.class, "LeftVertical");
        RightVertical = hardwareMap.get(DcMotor.class, "RightVertical");
        LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        intakeClaw = hardwareMap.servo.get("leftClimberServo");


        FrontHorizontal.setDirection(DcMotor.Direction.FORWARD);
        BackHorizontal.setDirection(DcMotor.Direction.REVERSE);
        LeftVertical.setDirection(DcMotor.Direction.FORWARD);
        RightVertical.setDirection(DcMotor.Direction.REVERSE);
        LinearSlide.setDirection(DcMotor.Direction.FORWARD);

        robot.encoderSetUp(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);

        waitForStart();
        runtime.reset();


        if (opModeIsActive()) {





           //moveForwards
            robot.moveTileDiagonal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, "forwards");
            sleep(1000);
            robot.moveTileDiagonal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, "forwards");
            sleep(1000);

            //Spin to score and move diagonally
            robot.spinByDegreeVal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 45, 2000);
            robot.diagonalEncoderMove(1, 350, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            sleep(1000);
            //score

            //move back
            robot.diagonalEncoderMove(1, -350, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);


            robot.spinByDegreeVal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 315, 2000);
            sleep(1000);

            //currently at (5,2)
            //target: at (6,3)

            robot.moveTileDiagonal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, "forwards");
            //currently at (5,3)

            for (int i = 0; i < 4; i++) {
                robot.spinByDegreeVal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 90, 2000);
                //in position to intake wall cones
                robot.moveTileDiagonal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, "forwards");
                //move farther forwards, curently in (6,3)
                //intake
                robot.moveTileDiagonal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, "backwards");
                //(5,3)
                robot.spinByDegreeVal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 135, 2000);
                robot.diagonalEncoderMove(1, 350, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                //score
                robot.spinByDegreeVal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 315, 2000);
            }
//park
        }
    }
}

