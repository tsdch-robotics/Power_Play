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
import org.firstinspires.ftc.teamcode.R;

import static java.lang.Thread.sleep;

import java.text.Format;

@TeleOp(name="LinearSlide", group="LinearOpmode")
public class LinearSlideTesting extends LinearOpMode {


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
    boolean movingToPosition = false;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        FrontHorizontal = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        BackHorizontal = hardwareMap.get(DcMotor.class, "BackHorizontal");
        LeftVertical = hardwareMap.get(DcMotor.class, "LeftVertical");
        RightVertical = hardwareMap.get(DcMotor.class, "RightVertical");

        FrontHorizontal.setDirection(DcMotor.Direction.FORWARD);
        BackHorizontal.setDirection(DcMotor.Direction.REVERSE);
        LeftVertical.setDirection(DcMotor.Direction.FORWARD);
        RightVertical.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();
        runtime.reset();


        while (opModeIsActive()){

            FrontHorizontal.setPower(gamepad1.left_stick_y * 0.50);

            if (gamepad1.a){

                FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);

            }
        }


    }

}