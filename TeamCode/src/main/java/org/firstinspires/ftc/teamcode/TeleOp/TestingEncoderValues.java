package org.firstinspires.ftc.teamcode.TeleOp;

import android.text.method.BaseKeyListener;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;

import static java.lang.Thread.sleep;

@TeleOp(name="Encoder Testing", group="LinearOpmode")
public class TestingEncoderValues extends LinearOpMode {


    EncoderFunction robot = new EncoderFunction();

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontHorizontal = null;
    private DcMotor BackHorizontal = null;
    private DcMotor LeftVertical = null;
    private DcMotor RightVertical = null;
    private DcMotor LinearSlide = null;
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
        //fix this

        //FrontHorizontal.setDirection(DcMotorSimple.Direction.FORWARD);
       // BackHorizontal.setDirection(DcMotorSimple.Direction.REVERSE);
       // LeftVertical.setDirection(DcMotorSimple.Direction.FORWARD);
       // RightVertical.setDirection(DcMotorSimple.Direction.REVERSE);


        FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FrontHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LeftVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        runtime.reset();

        //double speed = 0.75;
        boolean movingToPosition = false;

        while (opModeIsActive()) {

            telemetry.addData("Encoder Value of FrontHorizontal:", "%d", FrontHorizontal.getCurrentPosition());
            telemetry.addData("Encoder Value of BackHorizontal:", "%d", BackHorizontal.getCurrentPosition());
            telemetry.addData("Encoder Value of LeftVertical:", "%d", LeftVertical.getCurrentPosition());
            telemetry.addData("Encoder Value of RightVertical:", "%d", RightVertical.getCurrentPosition());

            telemetry.update();

        }

    }

}