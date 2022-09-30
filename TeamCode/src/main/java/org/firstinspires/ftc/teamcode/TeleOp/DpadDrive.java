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

@TeleOp(name="Dpad Driving", group="LinearOpmode")
public class DpadDrive extends LinearOpMode {


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

        FrontHorizontal.setDirection(DcMotorSimple.Direction.FORWARD);
        BackHorizontal.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftVertical.setDirection(DcMotorSimple.Direction.FORWARD);
        RightVertical.setDirection(DcMotorSimple.Direction.REVERSE);


        FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FrontHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        runtime.reset();

        //double speed = 0.75;
        boolean movingToPosition = false;

        while (opModeIsActive()) {

 //MOVING RIGHT           //MOVING RIGHT

            if (gamepad1.dpad_right && !movingToPosition) {
                rightToMove = 1;
                FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            if (!gamepad1.dpad_right && rightToMove >= 1) {

                movingToPosition = true;
                robot.EncoderDrive(1, FrontHorizontal, BackHorizontal, 1000);

                if (FrontHorizontal.getCurrentPosition() == FrontHorizontal.getTargetPosition()) {
                    if (BackHorizontal.getCurrentPosition() == BackHorizontal.getTargetPosition()) {
                        telemetry.addData("Robot has", "arrived at target position!");
                        movingToPosition = false;
                        rightToMove -= 1;
                    }
                }
            }

            if (gamepad1.dpad_right && movingToPosition) {
                rightToMove = 2;
            }

            else {
                FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);
            }

/*
//MOVING LEFT            //MOVING LEFT


            if (gamepad1.dpad_left && !movingToPosition) {
                leftToMove = 1;
                FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }



            if (!gamepad1.dpad_left && leftToMove >= 1) {

                robot.EncoderDrive(-1, FrontHorizontal, BackHorizontal, 1000);
                movingToPosition = true;

                if (FrontHorizontal.getCurrentPosition() == FrontHorizontal.getTargetPosition()) {
                    if (BackHorizontal.getCurrentPosition() == BackHorizontal.getTargetPosition()) {
                        telemetry.addData("Robot has", "reached target positioning!");
                        movingToPosition = false;
                        leftToMove -= 1;
                    }
                }
            }

            if (gamepad1.dpad_left && movingToPosition) {
                leftToMove = 2;
            }


            else {
                FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);
            }

//MOVING FORWARDS

          //if(gamepad1.dpad_up &&

            if (gamepad1.dpad_up && !movingToPosition) {
                frontToMove = 1;
                LeftVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            if (!gamepad1.dpad_up && frontToMove >= 1) {

                robot.EncoderDrive(1, LeftVertical, RightVertical, 1000);
                movingToPosition = true;

                if (LeftVertical.getCurrentPosition() == LeftVertical.getTargetPosition()) {
                    if (RightVertical.getCurrentPosition() == RightVertical.getTargetPosition()) {
                        telemetry.addData("Robot has", "arrived at target position!");
                        movingToPosition = false;
                        frontToMove -= 1;
                    }
                }
            }

            if (gamepad1.dpad_right && movingToPosition) {
                frontToMove = 2;
            }




            if (gamepad1.dpad_down && !movingToPosition) {
                frontToMove = 1;
                LeftVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            if (!gamepad1.dpad_up && backToMove >= 1) {

                robot.EncoderDrive(-1, LeftVertical, RightVertical, 1000);
                movingToPosition = true;

                if (LeftVertical.getCurrentPosition() == LeftVertical.getTargetPosition()) {
                    if (RightVertical.getCurrentPosition() == RightVertical.getTargetPosition()) {
                        telemetry.addData("Robot has", "arrived at target position!");
                        movingToPosition = false;
                        backToMove -= 1;
                    }
                }
            }

            if (gamepad1.dpad_right && movingToPosition) {
                backToMove = 2;
            }


            else {
                FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);
            }

*/

        }





          //  if (gamepad1.left_trigger > 1){

         //   }



       // if (armPosition == robot.targetPos){
            //telemetry.addData("")
      //  }


    }

}