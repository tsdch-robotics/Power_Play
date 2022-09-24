package org.firstinspires.ftc.teamcode.TeleOp;

import android.text.method.BaseKeyListener;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Dpad Driving", group="Linear Opmode")
public class DpadDrive extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontHorizontal = null;
    private DcMotor BackHorizontal = null;
    private DcMotor LeftVertical = null;
    private DcMotor RightVertical = null;

    private DcMotor LinearSlide = null;

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

                FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                FrontHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                //FrontHorizontal.setPower(1);
                //BackHorizontal.setPower(1);

                FrontHorizontal.setTargetPosition(100);
                BackHorizontal.setTargetPosition(100);

               /* FrontHorizontal.setPower(1);
                BackHorizontal.setPower(1);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);

                */
            } else if (gamepad1.dpad_left) {

                FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                FrontHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                FrontHorizontal.setPower(-1);
                BackHorizontal.setPower(-1);

                FrontHorizontal.setTargetPosition(-100);
                BackHorizontal.setTargetPosition(-100);

                /*FrontHorizontal.setPower(-1);
                BackHorizontal.setPower(-1);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);*/
            }
            //
            else if (gamepad1.dpad_up) {

                LeftVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                LeftVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                LeftVertical.setPower(1);
                RightVertical.setPower(1);

                LeftVertical.setTargetPosition(100);
                RightVertical.setTargetPosition(100);

                /*FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(1);
                RightVertical.setPower(1);*/

            } else if (gamepad1.dpad_down) {

                LeftVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                LeftVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                LeftVertical.setPower(-1);
                RightVertical.setPower(-1);

                LeftVertical.setTargetPosition(-100);
                RightVertical.setTargetPosition(-100);

                /*FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(-1);
                RightVertical.setPower(-1);*/
            } else {

                FrontHorizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                FrontHorizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                //LeftVertical.setPower(0);
               // RightVertical.setPower(0);
              //  FrontHorizontal.setPower(0);
              //  BackHorizontal.setPower(0);




                /*FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);*/
            }


        }


    }

}
        /*int leftPressedValue = 0;
        int leftMovedValue = 0;

        int rightPressedValue =0;

        if (gamepad1.dpad_left) {
            leftPressedValue = leftPressedValue + 1;
        }


        else if (gamepad1.dpad_right) {
            rightPressedValue = rightPressedValue + 1;

        }

        //call function here
      // moveLeftByUnits();



        while (leftPressedValue != leftMovedValue) {



            FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            FrontHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BackHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            FrontHorizontal.setPower(1);
            BackHorizontal.setPower(1);

            FrontHorizontal.setTargetPosition(100);
            BackHorizontal.setTargetPosition(100);

            leftMovedValue = leftMovedValue + 1;

            }



        }


        }







        //if (gamepad1.dpad_left){
           // FrontHorizontal.setPower(motorSpeed);
           // BackHorizontal.setPower(motorSpeed);
       // }

        //else if (gamepad1.){

      // while gamepad1.dpad_left

  /*      if (gamepad1.dpad_right){

            while
            //moves by increment

            movingToTarget = true;

            FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            FrontHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BackHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            while (movingToTarget == true){
                FrontHorizontal.setPower(1);
                BackHorizontal.setPower(1);

                FrontHorizontal.setTargetPosition(100);
                BackHorizontal.setTargetPosition(100);

                FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);

                break;
            }
        }
*/
            // set variable from 0 to one
                // if variable = 1
                    //move to target position, one unit

            //step 1 move encoder positions one unit
            //if !gamepadpadright
                //set variable back to 0
                //break






/*
        // read the motors from the configuration
        FrontLeftDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        BackRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");

        //DuckWheel = hardwareMap.get(DcMotor.class, "duck_wheel");

        // BotServo = hardwareMap.get(Servo.class, "servo");

        // setting the motor direction
        FrontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        FrontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        BackLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        BackRightDrive.setDirection(DcMotor.Direction.FORWARD);

        DuckWheel.setDirection(DcMotor.Direction.FORWARD);

       //  reset runtime when user clicks play
        waitForStart();
        runtime.reset();

        // only start running after user confirmation
        while (opModeIsActive()) {

*/




