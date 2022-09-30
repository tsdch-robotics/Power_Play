package org.firstinspires.ftc.teamcode.TeleOp;

//import android.text.method.BaseKeyListener;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;

//import static java.lang.Thread.sleep;

@TeleOp(name="Holonic Drive", group="LinearOpmode")
public class HolonomicDrivePlusBot extends LinearOpMode {

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

        //double speed = 0.75;
        boolean movingToPosition = false;

        while (opModeIsActive()) {

            double max;

            double verticle   = -gamepad1.left_stick_y;
            double horizontal =  gamepad1.left_stick_x;
            double spin     =  gamepad1.right_stick_x;

            double LeftVerticlePower  = verticle;// + spin;
            double FrontHorizontalPower = horizontal; //- spin;
            double RightVerticlePower   = verticle;// + spin;
            double BackVerticlePower  = horizontal;// - spin;

            //max = Math.max(Math.abs(LeftVerticlePower), Math.abs(RightVerticlePower));
           // max = Math.max(max, Math.abs(FrontHorizontalPower));
            //max = Math.max(max, Math.abs(BackVerticlePower));

            //if (max > 1.0) {

               // LeftVerticlePower  /= max;
               // RightVerticlePower /= max;
               // FrontHorizontalPower   /= max;
               // BackVerticlePower  /= max;

           // }

            if (Math.abs(gamepad1.left_stick_y) >= 0.1) {

                LeftVertical.setPower(LeftVerticlePower);
                RightVertical.setPower(RightVerticlePower);

            }

            if (Math.abs(gamepad1.left_stick_x) >= 0.1){

                FrontHorizontal.setPower(FrontHorizontalPower);
                BackHorizontal.setPower(BackVerticlePower);

            }

        }

    }

}