
//IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS

package org.firstinspires.ftc.teamcode.TeleOp;

import android.text.method.BaseKeyListener;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;
//import org.firstinspires.ftc.teamcode.R;
import com.qualcomm.robotcore.hardware.Servo;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import java.text.Format;

//IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS






@TeleOp(name="Dpad Driving", group="LinearOpmode")
public class DpadDrive extends LinearOpMode {


    EncoderFunction robot = new EncoderFunction();
    //Thread.sleep robo = new se


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontHorizontal = null;
    private DcMotor BackHorizontal = null;
    private DcMotor LeftVertical = null;
    private DcMotor RightVertical = null;
    private DcMotor LinearSlide = null;

    private Servo intakeClaw;
    int rightToMove = 0;
    int leftToMove = 0;
    int frontToMove = 0;
    int backToMove = 0;
    boolean movingToPosition = false;











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
        LinearSlide.setDirection(DcMotorSimple.Direction.FORWARD);


        robot.encoderSetUp(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);

        waitForStart();
        runtime.reset();

        //double speed = 0.75;




        while (opModeIsActive()){

       /*     if (gamepad1.dpad_up && !movingToPosition){
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                frontToMove = 1;
            }

            if (!gamepad1.dpad_up && rightToMove >= 1){
                movingToPosition = true;
                robot.EncoderDrive(1, 0, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 1010, 0);

                if (robot.hasReachedPosition(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical) == true){
                    movingToPosition = false;
                    frontToMove-= 1;
                }
            }









            if (gamepad1.dpad_down && !movingToPosition){
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                backToMove = 1;
            }

            if (!gamepad1.dpad_down && backToMove >= 1){
                movingToPosition = true;
                robot.EncoderDrive(1, 0, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -1010, 0);

                if (robot.hasReachedPosition(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical) == true){
                    movingToPosition = false;
                    backToMove-= 1;
                }
            }








            if (gamepad1.dpad_left && !movingToPosition){
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                leftToMove = 1;
            }

            if (!gamepad1.dpad_left && leftToMove >= 1){
                movingToPosition = true;
                robot.EncoderDrive(0, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, 1010);

                if (robot.hasReachedPosition(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical) == true){
                    movingToPosition = false;
                    leftToMove-= 1;
                }
            }






            if (gamepad1.dpad_right && !movingToPosition){
                robot.reset(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
                rightToMove = 1;
            }

            if (!gamepad1.dpad_right && rightToMove >= 1){
                movingToPosition = true;
                robot.EncoderDrive(0,   1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, -1010);

                if (robot.hasReachedPosition(FrontHorizontal, BackHorizontal, LeftVertical, RightVertical) == true){
                    movingToPosition = false;
                    rightToMove-= 1;
                }
            }




*/


       /* while (opModeIsActive()) {*/

            //MOVING RIGHT           //MOVING RIGHT

            if (gamepad1.dpad_right && !movingToPosition) {
                FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightToMove = 1;
            }

            while (!gamepad1.dpad_right && rightToMove >= 1) {

                movingToPosition = true;
                robot.EncoderDrive(0.50, 0.0, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 1010, 0);
                //robot.EncoderDrive(0.50, FrontHorizontal, BackHorizontal, 300);
               // robot.EncoderDrive(0.75, FrontHorizontal, BackHorizontal, 500);
               // robot.EncoderDrive(0.75, FrontHorizontal, BackHorizontal, 1010);

                if (!FrontHorizontal.isBusy() || !BackHorizontal.isBusy()) {
                    telemetry.addData("Robot has", "arrived at target position!");
                    movingToPosition = false;

                    FrontHorizontal.setPower(0);
                    BackHorizontal.setPower(0);
                    LeftVertical.setPower(0);
                    RightVertical.setPower(0);

                    rightToMove -= 1;
                }
            }

            if (gamepad1.dpad_right && movingToPosition) {
                rightToMove = 2;
            }


            //MOVING LEFT


            if (gamepad1.dpad_left && !movingToPosition) {
                FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftToMove = 1;
            }

            while (!gamepad1.dpad_left && leftToMove >= 1) {

                movingToPosition = true;
                robot.EncoderDrive(0.50, 0.0, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, -1010, 0);

                if (!FrontHorizontal.isBusy() || !BackHorizontal.isBusy()) {
                    telemetry.addData("Robot has", "arrived at target position!");
                    movingToPosition = false;

                    FrontHorizontal.setPower(0);
                    BackHorizontal.setPower(0);
                    LeftVertical.setPower(0);
                    RightVertical.setPower(0);

                    leftToMove -= 1;
                }
            }

            if (gamepad1.dpad_left && movingToPosition) {
                leftToMove = 2;
            }

//MOVING FORWARDS


            if (gamepad1.dpad_up && !movingToPosition) {
                LeftVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontToMove = 1;
            }

            while (!gamepad1.dpad_up && frontToMove >= 1) {

                movingToPosition = true;
                robot.EncoderDrive(0.0, 0.5, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, 1010);

                if (!LeftVertical.isBusy() || !RightVertical.isBusy()) {
                    telemetry.addData("Robot has", "arrived at target position!");
                    movingToPosition = false;

                    FrontHorizontal.setPower(0);
                    BackHorizontal.setPower(0);
                    LeftVertical.setPower(0);
                    RightVertical.setPower(0);

                    frontToMove -= 1;
                }
            }

            if (gamepad1.dpad_up && movingToPosition) {
                frontToMove = 2;
            }



            //MOVING BACK


            if (gamepad1.dpad_down && !movingToPosition) {
                LeftVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backToMove = 1;
            }

            while (!gamepad1.dpad_down && backToMove >= 1) {

                movingToPosition = true;
                robot.EncoderDrive(0.0, 0.5, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 0, -1010);

                if (!LeftVertical.isBusy() || !RightVertical.isBusy()) {
                    telemetry.addData("Robot has", "arrived at target position!");
                    movingToPosition = false;

                    FrontHorizontal.setPower(0);
                    BackHorizontal.setPower(0);
                    LeftVertical.setPower(0);
                    RightVertical.setPower(0);

                    backToMove -= 1;
                }
            }

            if (gamepad1.dpad_down && movingToPosition) {
                backToMove = 2;
            }







            //END OF ENCODER DPAD








































            if (gamepad1.a) {
                robot.spinOneFourth(1, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 340);
               // currentThread(sleep(100););
               // robot.diagonalEncoderMove(1, 500, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            }

            if (gamepad1.x) {
                robot.spinOneFourth(1, 2, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 340);
               // robot.diagonalEncoderMove(1, 500, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            }


            if (!FrontHorizontal.isBusy() || !LeftVertical.isBusy() || !BackHorizontal.isBusy() || !RightVertical.isBusy()) {
                FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);
            }



            while (gamepad1.left_bumper){


                FrontHorizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                LeftVertical.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                double verticle   = -gamepad1.right_stick_y;
                double horizontal =  gamepad1.right_stick_x;
                //double spin     =  gamepad1.left_stick_x;

                double LeftVerticlePower  = verticle;
                double FrontHorizontalPower = horizontal;
                double RightVerticlePower   = verticle;
                double BackHorizontalPower  = horizontal;


                LeftVertical.setPower(LeftVerticlePower);
                RightVertical.setPower(RightVerticlePower);
                telemetry.addData("Left Stick Y: ", "%f", gamepad1.right_stick_y);

                FrontHorizontal.setPower(FrontHorizontalPower);
                BackHorizontal.setPower(BackHorizontalPower);
                telemetry.addData("Left Stick X: ", "%f", gamepad1.right_stick_x);

                telemetry.update();

            }

            while (gamepad1.right_bumper) {
                if (gamepad1.left_trigger > 0) {
                    LinearSlide.setTargetPosition((int) (gamepad1.left_trigger * -3119));//3119

                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    LinearSlide.setPower(0.5);

                }

                else {
                    LinearSlide.setTargetPosition(-10);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setPower(0.25);
                }


                if (gamepad1.a){
                    intakeClaw.setPosition(-1);
                }

               else {
                   intakeClaw.setPosition(0.5);
               }
               telemetry.addData("Servo Pos", "%f", intakeClaw.getPosition());
               telemetry.update();
            }


        }//-3119



    }

}
