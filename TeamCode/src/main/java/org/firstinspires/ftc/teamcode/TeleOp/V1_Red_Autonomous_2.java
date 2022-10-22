package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;
//import org.firstinspires.ftc.teamcode.R;
import static java.lang.Thread.sleep;

import java.lang.*;

@Autonomous(name="V1_Red_Autonomous_2", group="Autonomous")
public class V1_Red_Autonomous_2 extends LinearOpMode {

    EncoderFunction robot = new EncoderFunction();
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontHorizontal = null;
    private DcMotor BackHorizontal = null;
    private DcMotor LeftVertical = null;
    private DcMotor RightVertical = null;
    private DcMotor LinearSlide = null;

    //private Servo intakeClaw;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        FrontHorizontal = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        BackHorizontal = hardwareMap.get(DcMotor.class, "BackHorizontal");
        LeftVertical = hardwareMap.get(DcMotor.class, "LeftVertical");
        RightVertical = hardwareMap.get(DcMotor.class, "RightVertical");
        LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        //intakeClaw = hardwareMap.servo.get("leftClimberServo");


        FrontHorizontal.setDirection(DcMotor.Direction.FORWARD);
        BackHorizontal.setDirection(DcMotor.Direction.REVERSE);
        LeftVertical.setDirection(DcMotor.Direction.FORWARD);
        RightVertical.setDirection(DcMotor.Direction.REVERSE);
        LinearSlide.setDirection(DcMotorSimple.Direction.FORWARD);

        robot.encoderSetUp(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);

        waitForStart();
        runtime.reset();


        if (opModeIsActive()){

            robot.EncoderDrive(0, 1,FrontHorizontal, BackHorizontal, LeftVertical, RightVertical,  0, 150);
            sleep(1000);
            robot.EncoderDrive(1, 0,FrontHorizontal,BackHorizontal,LeftVertical,RightVertical,1010, 150);
            sleep(1000);
            robot.EncoderDrive(0,1,FrontHorizontal,BackHorizontal,LeftVertical,RightVertical,1010,1160);
            sleep(1000);
            //robot.ScorePoleAuto(1,LinearSlide,2000);
            robot.diagonalEncoderMove(.5,352,1,FrontHorizontal,BackHorizontal,LeftVertical,RightVertical);
            sleep(1000);
           // robot.ScorePoleAuto(1,LinearSlide,0);
        }
    }





}

