//RightSide

package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        FrontHorizontal.setDirection(DcMotor.Direction.REVERSE);
        BackHorizontal.setDirection(DcMotor.Direction.FORWARD);
        LeftVertical.setDirection(DcMotor.Direction.FORWARD);
        RightVertical.setDirection(DcMotor.Direction.REVERSE);
        LinearSlide.setDirection(DcMotorSimple.Direction.FORWARD);


        robot.encoderSetUp(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);

        waitForStart();
        runtime.reset();


        if (opModeIsActive()) {



            robot.diagonalForward(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 800);
            sleep(2000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.scoreThisCorner(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, 4000, LinearSlide, intakeClaw);
            sleep(10000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
           // robot.spinByDegreeVal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -90, 350);
            //robot.diagonalForward(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 600);
            //currently at (5,3)

            //sleep(7000);
            //robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
           /* for (int i = 0; i < 4; i++) {
                robot.spinByDegreeVal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 90, 700);
                //in position to intake wall cones
               // Thread.sleep(1000);
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                robot.diagonalForward(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 1000);
                //move farther forwards, curently in (6,3)
                //intake
                sleep(1000);
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                robot.diagonalBackward(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 1000);
                //(5,3)
                sleep(1000);
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                robot.spinByDegreeVal(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -90, -700);
                sleep(1000);
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                robot.scoreThisCorner(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, 4040, LinearSlide, intakeClaw);
                sleep(1000);
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            }




            //robot.move(5,3);

            sleep(2000);
//park*/
        }
    }
}

