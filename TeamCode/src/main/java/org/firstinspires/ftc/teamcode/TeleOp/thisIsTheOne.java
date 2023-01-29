package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.notFUNctions;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Hardware.notFUNctions;

// define the teleop to appear on the phone
@TeleOp(name="thisIsTheOne", group="Linear Opmode")
public class thisIsTheOne extends LinearOpMode {

    notFUNctions robot = new notFUNctions();

    // defining all the variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor Quadrant1 = null;
    private DcMotor Quadrant2 = null;
    private DcMotor Quadrant3 = null;
    private DcMotor Quadrant4 = null;
    private DcMotor LinearSlide = null;
    private Servo IntakeLeft;
    private Servo IntakeRight;
    private DistanceSensor sensorRange2;

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

        Quadrant1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Quadrant2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Quadrant3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Quadrant4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        IntakeLeft = hardwareMap.servo.get("IntakeLeft");
        IntakeRight = hardwareMap.servo.get("IntakeRight");
        LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        sensorRange2 = hardwareMap.get(DistanceSensor.class, "sensor_range2");
        Rev2mDistanceSensor sensorTimeOfFlight2 = (Rev2mDistanceSensor)sensorRange2;

        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // reset runtime when user clicks play
        waitForStart();
        runtime.reset();

        // only start running after user confirmation
        while (opModeIsActive()) {



            //consider using dpad with Ci1-4 values, up d l r, would need to ajust sensor range function


            telemetry.addData("range", String.format("%.01f mm", sensorRange2.getDistance(DistanceUnit.MM)));

            if (IntakeLeft.getPosition() != 1 && (sensorRange2.getDistance(DistanceUnit.MM)) >= 0 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 100  && LinearSlide.getCurrentPosition() <= -130 && LinearSlide.getCurrentPosition() >= -350 && !gamepad1.dpad_left){

                LinearSlide.setTargetPosition(LinearSlide.getCurrentPosition() + 155); //level at 0, grabbing b
                LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                LinearSlide.setPower(.6);
                highest = LinearSlide.getCurrentPosition();

            }

            if (IntakeLeft.getPosition() != 1 && (sensorRange2.getDistance(DistanceUnit.MM)) >= 0 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 100  && LinearSlide.getCurrentPosition() <= -130 && LinearSlide.getCurrentPosition() >= -350 && gamepad1.dpad_left){

                LinearSlide.setTargetPosition(-20); //level at 0, grabbing b
                LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                LinearSlide.setPower(.6);
                highest = LinearSlide.getCurrentPosition();

            }



            if(LinearSlide.getCurrentPosition() >= -15){
                IntakeLeft.setPosition(1); //Close
                IntakeRight.setPosition(1);

            }
/*
            if (IntakeLeft.getPosition() == 1 && (sensorRange2.getDistance(DistanceUnit.MM)) >= 25 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 44 && (LinearSlide.getCurrentPosition() == -520 || LinearSlide.getCurrentPosition() == -835 || LinearSlide.getCurrentPosition() == -1115)){
                IntakeLeft.setPosition(0); //Open
                IntakeRight.setPosition(0);
            }*/

            // initializing all the wheel power values
            double Quadrant1Power = 0;
            double Quadrant2Power = 0;
            double Quadrant3Power = 0;
            double Quadrant4Power = 0;
//questionable
            Quadrant1.setDirection(DcMotor.Direction.FORWARD);    //forward, forward, reverse, reverse:: .. Front, Back, Left, Right, but rotate 90 degrees on wires
            Quadrant2.setDirection(DcMotor.Direction.FORWARD);
            Quadrant3.setDirection(DcMotor.Direction.REVERSE);
            Quadrant4.setDirection(DcMotor.Direction.REVERSE);

            Double[] computedPower = robot.computePower((double) gamepad1.right_stick_x, (double) gamepad1.right_stick_y);

            telemetry.addData("Computing Powers Successful", "powerX: %f, powerY: %f", computedPower[0], computedPower[1]);

            Quadrant1Power = computedPower[0];
            Quadrant3Power = computedPower[0];

            Quadrant2Power = computedPower[1];
            Quadrant4Power = computedPower[1];



            if (gamepad1.dpad_up && !gamepad1.a){
                Quadrant1.setPower(0.5 * Quadrant1Power);
                Quadrant2.setPower(0.5 * Quadrant2Power);
                Quadrant3.setPower(0.5 * Quadrant3Power);
                Quadrant4.setPower(0.5 *Quadrant4Power);
            }
            else if (gamepad1.dpad_down && !gamepad1.a){
                Quadrant1.setPower(0.25 * Quadrant1Power);
                Quadrant2.setPower(0.25 * Quadrant2Power);
                Quadrant3.setPower(0.25 * Quadrant3Power);
                Quadrant4.setPower(0.25 *Quadrant4Power);
            }
            else{
                Quadrant1.setPower(multiplier * Quadrant1Power + ((.75) * (gamepad1.left_stick_x)));
                Quadrant2.setPower(multiplier * Quadrant2Power + ((.75) * (gamepad1.left_stick_x)));
                Quadrant3.setPower(multiplier * Quadrant3Power - ((.75) * (gamepad1.left_stick_x)));
                Quadrant4.setPower(multiplier * Quadrant4Power - ((.75) * (gamepad1.left_stick_x)));
            }

            double requestedAngle = computedPower[2];

            telemetry.addData("Driver Requested Angle", "%f", Math.toDegrees(requestedAngle));
            telemetry.update();

        //These two large "if" blocks allow Daniel to use triggers to go up and down without getting unwanted feedback from a trigger being realease





            if (gamepad1.left_trigger >= 0 && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && gamepad1.right_trigger < .1){ //&& LinearSlide.getTargetPosition() > linearSlidePreviousPos) {//change 0.1 to 0!

                int triggerTargetPosition = ((int) (gamepad1.left_trigger * -1100));//Top pole
                //LinearSlide.setTargetPosition(triggerTargetPosition);

                if (triggerTargetPosition < highest){
                    LinearSlide.setTargetPosition(triggerTargetPosition);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    LinearSlide.setPower(1);
                    highest = LinearSlide.getCurrentPosition();
                }
            }

       /*
            if (gamepad1.right_trigger >= .1 && gamepad1.right_trigger < .2){
                int target = //-520
                if (target < highest){
                    LinearSlide.setTargetPosition(triggerTargetPosition);
                    LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    LinearSlide.setPower(.6);
                    highest = LinearSlide.getCurrentPosition();
                }
            }*/

            if (gamepad1.a){
                LinearSlide.setTargetPosition(0); //level at 0, grabbing
                LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                LinearSlide.setPower(1);
                highest = LinearSlide.getCurrentPosition();
            }

            if (gamepad1.b){
                LinearSlide.setTargetPosition(-462);  //low
                LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                LinearSlide.setPower(1);
                highest = LinearSlide.getCurrentPosition();
            }

            if (gamepad1.y){
                LinearSlide.setTargetPosition(-155); //ground and intake
                LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                LinearSlide.setPower(1);
                highest = LinearSlide.getCurrentPosition();
            }

            if (gamepad1.x){
                LinearSlide.setTargetPosition(-773); //medium
                LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                LinearSlide.setPower(1);
                highest = LinearSlide.getCurrentPosition();
            }






















































            while (gamepad1.dpad_right){
                Quadrant1.setPower(0.5);
                Quadrant2.setPower(0.5);
                Quadrant3.setPower(-0.5);
                Quadrant4.setPower(-0.5);
            }
//yooooooooo
            while (gamepad1.dpad_left){
                Quadrant1.setPower(-0.5);
                Quadrant2.setPower(-0.5);
                Quadrant3.setPower(0.5);
                Quadrant4.setPower(0.5);
            }
















































            if (gamepad1.right_bumper) {   //Open
                IntakeLeft.setPosition(0.00);
                IntakeRight.setPosition(0.00);

            }

            if (gamepad1.left_bumper) {
                IntakeLeft.setPosition(1); //Close
                IntakeRight.setPosition(1);

            }






























/*
           if (!already_closed && intakeClaw.getPosition() == .48) {
                robot.sleepThread(1000L);
                gamepad1.rumble(1000);
                already_closed = true;
            }

            if (already_closed && intakeClaw.getPosition() != .48) {
                //gamepad1.rumble(1000);
                already_closed = false;


            }
*/
            //Note: Add in Emergency Callabration Mode, hold "A" and left stick at the same time to adjust
        }


        //CREATE LINEAR SLIDE HEIGHT PRESETS: DONE!




    }
}

//cOdInG iS sO fUn! - yes































































































/*THE GARBAGE DUMP OF OLD CODE:*/


/*/*  if (gamepad1.left_trigger >= 0 && gamepad1.right_trigger <= 0){ //&& LinearSlide.getTargetPosition() > linearSlidePreviousPos) {

                int triggerTargetPosition = ((int) (gamepad1.left_trigger * -3800));//3119
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
            }*/




/*
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
*/