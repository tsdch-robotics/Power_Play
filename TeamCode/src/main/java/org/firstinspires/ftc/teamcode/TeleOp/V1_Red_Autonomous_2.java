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

@Autonomous(name="V1_Red_Autonomous_2", group="Autonomous")
public class V1_Red_Autonomous_2 extends LinearOpMode {

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

            intakeClaw.setPosition(0);
            sleep(1000);
            robot.EncoderDrive(.26, 0.5, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, 1010);
            LinearSlide.setTargetPosition(-3000);//need to ajust
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setPower(0.5);
            sleep(2000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.diagonalEncoderMove(0.5, 500, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            sleep(2000);
            LinearSlide.setTargetPosition(-2700);//need to ajust
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setPower(0.5);
            sleep(2000);
            intakeClaw.setPosition(.46);
            sleep(2000);
            robot.diagonalEncoderMove(0.5, -400, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            sleep(2000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.EncoderDrive(0, 0.5, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, -1160);
            sleep(2000);
            robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            robot.EncoderDrive(0.5, 0, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -1250, 0);
            intakeClaw.setPosition(.24);
            sleep(500);
            LinearSlide.setTargetPosition(0);//need to ajust
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setPower(0.5);
            sleep(3000);
            //end of working code





            //__ secs of autonomous left

            //current position = parking loc 2*/

        }
    }
}