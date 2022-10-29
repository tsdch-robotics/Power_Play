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

            intakeClaw.setPosition(0.2);
            sleep(1000);
            robot.EncoderDrive(0, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, 150);
            LinearSlide.setTargetPosition(-2000);//need to ajust
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setPower(1);
            sleep(1000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.diagonalEncoderMove(1, 450, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            LinearSlide.setTargetPosition(-1000);//need to ajust
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setPower(1);
            sleep(1000);
            intakeClaw.setPosition(0.15);
            sleep(1000);
            robot.diagonalEncoderMove(1, -400, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            //end of working code
            robot.spinOneFourth(1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 295);
            sleep(1000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.diagonalEncoderMove(1, 800, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            //check
            sleep(1000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            intakeClaw.setPosition(-0.8);
            robot.EncoderDrive(0, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, 150);
            //center
            //check
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            sleep(1000);
            robot.spinOneFourth(1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -295);
            LinearSlide.setTargetPosition(-2500);//need to ajust
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setPower(1);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.diagonalEncoderMove(1, 350, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            sleep(1000);
            LinearSlide.setTargetPosition(-2000);//need to ajust/CHECK VALUE, SERVO RELEASE TIME!
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setPower(1);
            sleep(1000);
//check
            intakeClaw.setPosition(0);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            sleep(1000); //to prevent sliping
            robot.diagonalEncoderMove(1, -400, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            sleep(1000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.EncoderDrive(0, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, 2020);
            sleep(100);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.EncoderDrive(1, 0, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -1010, 0);

            //__ secs of autonomous left

            //current position = parking loc 2

        }
    }
}