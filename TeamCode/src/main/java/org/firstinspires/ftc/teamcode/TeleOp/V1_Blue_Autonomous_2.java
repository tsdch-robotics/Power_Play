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

@Autonomous(name="V1_Blue_Autonomous2", group="Autonomous")
public class V1_Blue_Autonomous_2 extends LinearOpMode {

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


        robot.encoderSetUp(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);

        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();
        runtime.reset();


        if (opModeIsActive()) {


            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -253, 57, 184, -369);
            sleep(500);
            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -826, 600, 815, -1019);
            sleep(500);
            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -1210, 810, 1002, -1412);
            sleep(500);
            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -907, 664, 1002, -1243);
            sleep(500);
            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -787, 843, 1761, -1649);
            sleep(500);
            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -759, 1526, 2469, -1635);
            sleep(500);
            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -1084, 1904, 2957, -2071);
            sleep(500);
            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -1165, 2167, 3253, -2178);
            sleep(500);
            robot.moveEachMotor(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -636, 1230, 2431, -1310);
            sleep(500);
        }
    }
}

