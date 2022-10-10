package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;


@TeleOp(name="Score High", group="LinearOpmode")
public class ScoreHigh extends LinearOpMode {


    EncoderFunction robot = new EncoderFunction();

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontHorizontal;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        float heightPos = gamepad1.left_trigger * 2820;
        int targetHeight = (int)heightPos;

        waitForStart();
        runtime.reset();

        FrontHorizontal = hardwareMap.get(DcMotor.class, "FrontHorizontal");


        FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FrontHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("trigger value is", "%f", gamepad1.left_trigger);
        telemetry.update();



        FrontHorizontal.setTargetPosition(targetHeight);

        FrontHorizontal.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontHorizontal.setTargetPosition(100);

        FrontHorizontal.setPower(1);
         //   motor2.setPower(speed);







//2820
    }

}

