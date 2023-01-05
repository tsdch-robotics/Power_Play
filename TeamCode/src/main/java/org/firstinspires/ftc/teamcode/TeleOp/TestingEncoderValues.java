package org.firstinspires.ftc.teamcode.TeleOp;

import android.text.method.BaseKeyListener;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;

import static java.lang.Thread.sleep;

@TeleOp(name="The Friendly, Useful OpMode!", group="LinearOpmode")
public class TestingEncoderValues extends LinearOpMode {


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

    double rightToMove = 0;
    double leftToMove = 0;
    double frontToMove = 0;
    double backToMove = 0;

    // @Override
    // public void init(){

    //    robot.init(HardwareMap);
    // }


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        FrontHorizontal = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        BackHorizontal = hardwareMap.get(DcMotor.class, "BackHorizontal");
        LeftVertical = hardwareMap.get(DcMotor.class, "LeftVertical");
        RightVertical = hardwareMap.get(DcMotor.class, "RightVertical");
        LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        intakeClaw = hardwareMap.get(Servo.class, "intakeCLaw");

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

        FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FrontHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LeftVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        runtime.reset();

        //double speed = 0.75;
        boolean movingToPosition = false;

        while (opModeIsActive()) {

            telemetry.addData("Encoder Value of FrontHorizontal:", "%d", FrontHorizontal.getCurrentPosition());
            telemetry.addData("Encoder Value of BackHorizontal:", "%d", BackHorizontal.getCurrentPosition());
            telemetry.addData("Encoder Value of LeftVertical:", "%d", LeftVertical.getCurrentPosition());
            telemetry.addData("Encoder Value of RightVertical:", "%d", RightVertical.getCurrentPosition());
            telemetry.addData("Encoder Value of LinearSlide:", "%d", LinearSlide.getCurrentPosition());
            telemetry.addData("Encoder Value of verticalOdometry:", "%d", verticaOdometry.getCurrentPosition());
            telemetry.addData("Encoder Value of horizontalOdometry:", "%d", horizontalOdometry.getCurrentPosition());
            telemetry.addData("Value of intakeServo:", "%d", intakeClaw.getPosition());

            telemetry.update();

        }

    }

}