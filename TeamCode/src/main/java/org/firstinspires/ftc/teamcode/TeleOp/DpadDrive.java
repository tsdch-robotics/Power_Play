package org.firstinspires.ftc.teamcode.TeleOp;

import android.text.method.BaseKeyListener;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;

@TeleOp(name="Dpad Driving", group="LinearOpmode")
public class DpadDrive extends LinearOpMode {


    EncoderFunction robot = new EncoderFunction();

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontHorizontal = null;
    private DcMotor BackHorizontal = null;
    private DcMotor LeftVertical = null;
    private DcMotor RightVertical = null;

    private DcMotor LinearSlide = null;


    // @Override
    // public void init(){

    //    robot.init(HardwareMap);
    // }


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        double motorSpeed = 1.0;
        boolean movingToTarget = false;

        FrontHorizontal = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        BackHorizontal = hardwareMap.get(DcMotor.class, "BackHorizontal");
        LeftVertical = hardwareMap.get(DcMotor.class, "LeftVertical");
        RightVertical = hardwareMap.get(DcMotor.class, "RightVertical");
        //fix this

        FrontHorizontal.setDirection(DcMotorSimple.Direction.FORWARD);
        BackHorizontal.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftVertical.setDirection(DcMotorSimple.Direction.FORWARD);
        RightVertical.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            if (gamepad1.dpad_right) {
                robot.moveByTileUnit(1, FrontHorizontal, BackHorizontal, 100);
            }

            else if (gamepad1.dpad_left) {
                robot.moveByTileUnit(-1, FrontHorizontal, BackHorizontal, 100);
            }

            else if (gamepad1.dpad_up) {
                robot.moveByTileUnit(1, LeftVertical, RightVertical, 100);
            }

            else if (gamepad1.dpad_down) {
                robot.moveByTileUnit(-1, FrontHorizontal, BackHorizontal, 100);
            }

            else {
                FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);

            }
        }
    }
}



