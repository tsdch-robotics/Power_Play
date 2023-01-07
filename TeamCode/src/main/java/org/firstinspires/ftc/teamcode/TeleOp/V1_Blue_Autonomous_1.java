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

    private DcMotor verticaOdometry = null; //new Odometry pods, use in the spot of motor ports
    private DcMotor horizontalOdometry = null;





    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        FrontHorizontal = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        BackHorizontal = hardwareMap.get(DcMotor.class, "BackHorizontal");
        LeftVertical = hardwareMap.get(DcMotor.class, "LeftVertical");
        RightVertical = hardwareMap.get(DcMotor.class, "RightVertical");
        LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        intakeClaw = hardwareMap.servo.get("intakeClaw");

        verticaOdometry = hardwareMap.get(DcMotor.class, "verticalOdometry");
        horizontalOdometry = hardwareMap.get(DcMotor.class, "horizontalOdometry");

        FrontHorizontal.setDirection(DcMotor.Direction.REVERSE);
        BackHorizontal.setDirection(DcMotor.Direction.FORWARD);
        LeftVertical.setDirection(DcMotor.Direction.FORWARD);
        RightVertical.setDirection(DcMotor.Direction.REVERSE);
        LinearSlide.setDirection(DcMotorSimple.Direction.FORWARD);


        verticaOdometry.setDirection(DcMotor.Direction.FORWARD);
        horizontalOdometry.setDirection(DcMotor.Direction.REVERSE);



        verticaOdometry.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//data only, do not set power!
        horizontalOdometry.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//data only, do not set power!



        //robot.encoderSetUp(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);

        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();
        runtime.reset();


        if (opModeIsActive()) {

            intakeClaw.setPosition(.5);

            sleep(2000);

            LinearSlide.setTargetPosition(-1900); //low
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            LinearSlide.setPower(.6);

            robot.moveForwardUntilPos(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, verticaOdometry, horizontalOdometry, 10, 5);  //will need to change
            sleep(1000);
            robot.moveForwardUntilPos(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, verticaOdometry, horizontalOdometry, 10, 10);
            sleep(800);

            intakeClaw.setPosition(.3);

            sleep(1000);
            robot.moveForwardUntilPos(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, verticaOdometry, horizontalOdometry, 10, 5);
            sleep(1000);

            LinearSlide.setTargetPosition(0); //low
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            LinearSlide.setPower(.6);

            sleep(1000);
            robot.moveForwardUntilPos(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, verticaOdometry, horizontalOdometry, 0, 5);
            sleep(2000);
            robot.moveForwardUntilPos(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, verticaOdometry, horizontalOdometry, 0, 20);
            sleep(3000);





            //then move right 1.5 and go forward 1, - OR - try to get another cone and score on high goal = risky :{



      //     robot.moveForwardUntilPos(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, )
/*
      //     robot.moveForwardUntilPos(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, inches vertical, inches horizzontal: 40)

//start facing to the right, move left 1 and a half, forward n score, left 1.5, forward 1, intake, back out 1.5, score quadrant 4, intake, back 2.5, score quad 4 high goal, park.

        //while != to horz, moveForward(), while not != to vert, moveLeft/Right
        //spinAndScore:::     vertical odometry move by x inches, sleep, move slider, sleep horz odometry forward by y inches, sleep, slider down a few, drop, slider up, sleep BRIEFLY, go back and then again move to previous positions, sleep
            //intake::      (input number of cones left, (a number) times the number of cones left = the encoder height of slider)  ------>    slider goes up a few, lasso = open, move forward less than half a tile, sleep, drop slider, grab, wait a second, slider up a bit, move back by a little less than half.






    THEREFORE:

        - right(1)

  */



        }
    }
}

