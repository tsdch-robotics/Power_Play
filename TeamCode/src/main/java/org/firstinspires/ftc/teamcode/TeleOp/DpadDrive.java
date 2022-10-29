//IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS

package org.firstinspires.ftc.teamcode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;
//import org.firstinspires.ftc.teamcode.R;
import com.qualcomm.robotcore.hardware.Servo;
//import static java.lang.Thread.currentThread;
//import static java.lang.Thread.sleep;
//import java.text.Format;

//IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS IMPORTS

@TeleOp(name="Dpad Driving", group="LinearOpmode")
public class DpadDrive extends LinearOpMode {

    EncoderFunction robot = new EncoderFunction();

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
    //double speed = 0.75;
    int highestSlider = 0;
    int triggerTargetPosition = 0;

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
        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

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

            //Control Claw

            if (gamepad1.left_bumper) {
                intakeClaw.setPosition(.46); //Open
            }

            if (gamepad1.right_bumper) {
                intakeClaw.setPosition(.26); //Close
            }
//Close claw

           /* if (gamepad1.a) {
                robot.spinOneFourth(1, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 340);
               // currentThread(sleep(100););
               // robot.diagonalEncoderMove(1, 500, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            }

            if (gamepad1.x) {
                robot.spinOneFourth(1, 2, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical, 340);
               // robot.diagonalEncoderMove(1, 500, 1, FrontHorizontal, BackHorizontal, LeftVertical, RightVertical);
            }
*/
            if (!FrontHorizontal.isBusy() || !LeftVertical.isBusy() || !BackHorizontal.isBusy() || !RightVertical.isBusy()) {
                FrontHorizontal.setPower(0);
                BackHorizontal.setPower(0);
                LeftVertical.setPower(0);
                RightVertical.setPower(0);
            }


            //?

            while (Math.abs(gamepad1.right_stick_x) > 0 || Math.abs(gamepad1.right_stick_y) > 0 || Math.abs(gamepad1.left_stick_x) > 0) {

                FrontHorizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                BackHorizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                LeftVertical.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightVertical.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                double verticle = -gamepad1.right_stick_y;
                double horizontal = gamepad1.right_stick_x;
                double spin = 0.5 * gamepad1.left_stick_x;


                    //Needs to test
                double LeftVerticlePower = 0.80 * (verticle - spin); //(0.5 *)
                double FrontHorizontalPower =0.80 * (horizontal - spin); //(0.5 *)
                double RightVerticlePower = 0.80* (verticle + spin);//(0.5 *)
                double BackHorizontalPower = 0.80 * (horizontal + spin);//(0.5 *)
                    //Needs to test

                while (Math.abs(LeftVerticlePower) > 1) {
                    LeftVerticlePower = 1;
                }
                while (Math.abs(FrontHorizontalPower) > 1) {
                    FrontHorizontalPower = 1;
                }
                while (Math.abs(RightVerticlePower) > 1) {
                    RightVerticlePower = 1;
                }
                while (Math.abs(BackHorizontalPower) > 1) {
                    BackHorizontalPower = 1;
                }

                LeftVertical.setPower(LeftVerticlePower);
                RightVertical.setPower(RightVerticlePower);
                telemetry.addData("Left Stick Y: ", "%f", gamepad1.right_stick_y);

                FrontHorizontal.setPower(FrontHorizontalPower);
                BackHorizontal.setPower(BackHorizontalPower);
                telemetry.addData("Left Stick X: ", "%f", gamepad1.right_stick_x);

                telemetry.update();
            }


            if (gamepad1.left_trigger > 0 ){


                triggerTargetPosition = ((int) (gamepad1.left_trigger * -3000));

                if (LinearSlide.getCurrentPosition() >= triggerTargetPosition) {

                    LinearSlide.setTargetPosition(triggerTargetPosition);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    LinearSlide.setPower(0.5);
                }

            }

            if (gamepad1.right_trigger > 0){

                triggerTargetPosition = ((int) (-3000 - gamepad1.right_trigger * -3000));
                if (LinearSlide.getCurrentPosition() <= triggerTargetPosition){

                    LinearSlide.setTargetPosition(triggerTargetPosition);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    LinearSlide.setPower(0.5);
                }

            }




           /* highestSlider = LinearSlide.getCurrentPosition();

            if (gamepad1.left_trigger > 0.1 && LinearSlide.getCurrentPosition() < -3000){ //&& LinearSlide.getTargetPosition() > linearSlidePreviousPos) {


                triggerTargetPosition = ((int) (gamepad1.left_trigger * -3000));//3119
                //LinearSlide.setTargetPosition(triggerTargetPosition);

                if (triggerTargetPosition >= highestSlider){

                    LinearSlide.setTargetPosition(triggerTargetPosition);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    LinearSlide.setPower(0.25);
                }

                //linearSlidePreviousPos = LinearSlide.getCurrentPosition();

            }

            if (gamepad1.right_trigger > 0.1 && LinearSlide.getCurrentPosition() > 0) {

                triggerTargetPosition = ((int)(-3000 - gamepad1.right_trigger * -3000));

                if (triggerTargetPosition <= highestSlider){

                    LinearSlide.setTargetPosition(triggerTargetPosition);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    LinearSlide.setPower(0.25);

                }

            }*/




            //Insert Auto Score Code HEre:
/*
            if (gamepad1.a){
                int corner = 1;
                boolean stillHasToMove = true;
            }

            if (gamepad1.b){
                int corner = 2;
                boolean stillHasToMove = true;
            }

            if (gamepad1.x){
                int corner = 3;
                boolean stillHasToMove = true;
            }

            if (gamepad1.y){
                int corner = 4;
                boolean stillHasToMove = true;
            }

            if (!gamepad1.a && !gamepad1.b && !gamepad1.y && !gamepad1.x){
                if (LinearSlide.getCurrentPosition() >= 2000){
                     int height = 3;
                }
                else if (LinearSlide.getCurrentPosition() <2000 && LinearSlide.getCurrentPosition() > 800){
                    int height = 2;
                }
                else if (LinearSlide.getCurrentPosition() <= 800){
                    int height = 1;
                }
            }*/


        }

    }//-3119

}


