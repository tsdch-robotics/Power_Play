package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;

// define the teleop to appear on the phone
@TeleOp(name="One Stick Drive", group="Linear Opmode")
public class thisIsTheOne extends LinearOpMode {

    EncoderFunction robot = new EncoderFunction();

    // defining all the variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor Quadrant1 = null;
    private DcMotor Quadrant2 = null;
    private DcMotor Quadrant3 = null;
    private DcMotor Quadrant4 = null;
    private DcMotor LinearSlide = null;
    private Servo intakeClaw;
    private boolean already_closed = false;
    private int highest = 0;
    private double multiplier = 1.0;
    private boolean hasChangedHeight = false;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // read the motors from the configuration
        Quadrant1  = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        Quadrant2 = hardwareMap.get(DcMotor.class, "LeftVertical");
        Quadrant3 = hardwareMap.get(DcMotor.class, "BackHorizontal");
        Quadrant4 = hardwareMap.get(DcMotor.class, "RightVertical");
        intakeClaw = hardwareMap.servo.get("intakeClaw");

        Quadrant1.setDirection(DcMotor.Direction.FORWARD);
        Quadrant2.setDirection(DcMotor.Direction.FORWARD);
        Quadrant3.setDirection(DcMotor.Direction.REVERSE);
        Quadrant4.setDirection(DcMotor.Direction.REVERSE);
        LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        LinearSlide.setDirection(DcMotor.Direction.FORWARD);


        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // reset runtime when user clicks play
        waitForStart();
        runtime.reset();

        // only start running after user confirmation
        while (opModeIsActive()) {


            // initializing all the wheel power values
            double Quadrant1Power = 0;
            double Quadrant2Power = 0;
            double Quadrant3Power = 0;
            double Quadrant4Power = 0;

           /* while (Math.abs(gamepad1.left_stick_x) < 0.5 || Math.abs(gamepad1.left_stick_y) < 0.5){
                Double computedPower[] = robot.computePower((double) gamepad1.right_stick_x, (double) gamepad1.right_stick_y);
            }*/
            Double[] computedPower = robot.computePower((double) gamepad1.right_stick_x, (double) gamepad1.right_stick_y);

            telemetry.addData("Computing Powers Successful", "powerX: %f, powerY: %f", computedPower[0], computedPower[1]);

            Quadrant1Power = computedPower[0];
            Quadrant3Power = computedPower[0];

            Quadrant2Power = computedPower[1];
            Quadrant4Power = computedPower[1];

            while (!gamepad1.dpad_left && !gamepad1.dpad_right ){
                Quadrant1.setPower(multiplier * Quadrant1Power);
                Quadrant2.setPower(multiplier * Quadrant2Power);
                Quadrant3.setPower(multiplier * Quadrant3Power);
                Quadrant4.setPower(multiplier *Quadrant4Power);
            }

            double requestedAngle = computedPower[2];

            telemetry.addData("Driver Requested Angle", "%f", Math.toDegrees(requestedAngle));
            telemetry.update();

        //These two large "if" blocks allow Daniel to use triggers to go up and down without getting unwanted feedback from a trigger being realeased

            if (gamepad1.left_trigger >= 0 && gamepad1.right_trigger <= 0){ //&& LinearSlide.getTargetPosition() > linearSlidePreviousPos) {

                int triggerTargetPosition = ((int) (gamepad1.left_trigger *    3800));//3119
                //LinearSlide.setTargetPosition(triggerTargetPosition);

                if (triggerTargetPosition > highest){
                    LinearSlide.setTargetPosition(triggerTargetPosition);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    LinearSlide.setPower(.6);
                    highest = LinearSlide.getCurrentPosition();
                }
            }


            if (gamepad1.right_trigger > 0 && gamepad1.left_trigger <= 0) { //&& LinearSlide.getTargetPosition() > linearSlidePreviousPos) {
                int triggerTargetPosition = ((int) (highest - gamepad1.left_trigger * highest));//3119
                if (triggerTargetPosition < highest && triggerTargetPosition >= 0) {
                    LinearSlide.setTargetPosition(triggerTargetPosition);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    LinearSlide.setPower(.6);
                    highest = LinearSlide.getCurrentPosition();
                }
            }

            while (gamepad1.dpad_right){
                Quadrant1.setPower(0.5);
                Quadrant2.setPower(0.5);
                Quadrant3.setPower(-0.5);
                Quadrant4.setPower(-0.5);
            }

            while (gamepad1.dpad_left){
                Quadrant1.setPower(-0.5);
                Quadrant2.setPower(-0.5);
                Quadrant3.setPower(0.5);
                Quadrant4.setPower(0.5);
            }

            if (gamepad1.left_bumper) {
                intakeClaw.setPosition(.48);//close

            }

            if (gamepad1.right_bumper) {
                intakeClaw.setPosition(.3); //open

            }

            if (!already_closed && intakeClaw.getPosition() == .48) {
                robot.sleepThread(1000L);
                gamepad1.rumble(1000);
                already_closed = true;
            }

            if (already_closed && intakeClaw.getPosition() != .48) {
                //gamepad1.rumble(1000);
                already_closed = false;


            }

            //Note: Add in Emergency Callabration Mode, hold "A" and left stick at the same time to adjust
        }

        if (gamepad1.dpad_up && gamepad1.a){    //Emergency callabration, linear slide GOES UP
            LinearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            hasChangedHeight = true;
            while(gamepad1.dpad_up){
                LinearSlide.setPower(.25);
            }

        }

        else if (gamepad1.dpad_down && gamepad1.a){    //Emergency callabration, linear slide GOES DOWN
            LinearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            hasChangedHeight = true;
            while(gamepad1.dpad_down){
                LinearSlide.setPower(-.25);
            }

        }

        if (hasChangedHeight){
            LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hasChangedHeight = false;
        }


        if (gamepad1.dpad_up){
            multiplier = 0.5;
        }
        else if (gamepad1.dpad_down){
            multiplier = 0.25;
        }
        else{
            multiplier = 1.0;
        }











        //CREATE LINEAR SLIDE HEIGHT PRESETS


        if (gamepad1.b){
            robot.autoMoveSlide(LinearSlide, 1000);
        }

        if (gamepad1.x){

        }



    }
}

//cOdInG iS sO fUn!