package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

// define the teleop to appear on the phone
@TeleOp(name="Robo Camp (Click Me)", group="Linear Opmode")
public class TestDrive extends LinearOpMode {

    // defining all the variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeftDrive = null;
    private DcMotor FrontRightDrive = null;
    private DcMotor BackLeftDrive = null;
    private DcMotor BackRightDrive = null;

    private DcMotor DuckWheel = null;

   // private Servo BotServo = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // read the motors from the configuration
        FrontLeftDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        BackRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");

        DuckWheel = hardwareMap.get(DcMotor.class, "duck_wheel");

       // BotServo = hardwareMap.get(Servo.class, "servo");

        // setting the motor direction
        FrontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        FrontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        BackLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        BackRightDrive.setDirection(DcMotor.Direction.FORWARD);

        DuckWheel.setDirection(DcMotor.Direction.FORWARD);

        // reset runtime when user clicks play
        waitForStart();
        runtime.reset();

        // only start running after user confirmation
        while (opModeIsActive()) {

            // initializing all the wheel power values
            double FrontLeftPower = 0;
            double FrontRightPower = 0;
            double BackLeftPower = 0;
            double BackRightPower = 0;

            double DuckWheelPower = 0;

            // set this value to limit speed
            double Multiplier = 0.75;

            // god awful code if else if else if else
            if (!gamepad1.dpad_left & !gamepad1.dpad_right) {
                FrontLeftPower = gamepad1.left_stick_y;
                FrontRightPower = gamepad1.right_stick_y;
                BackLeftPower = gamepad1.left_stick_y;
                BackRightPower = gamepad1.right_stick_y;
            } else if (gamepad1.dpad_left & !gamepad1.dpad_right) {
                FrontLeftPower = 1;
                FrontRightPower = -1;
                BackLeftPower = -1;
                BackRightPower = 1;
            } else if (!gamepad1.dpad_left & gamepad1.dpad_right) {
                FrontLeftPower = -1;
                FrontRightPower = 1;
                BackLeftPower = 1;
                BackRightPower = -1;
            }

         //   if (gamepad1.left_bumper & !gamepad1.right_bumper) {
           //     BotServo.setPosition(0.0);
       //     } else if (!gamepad1.left_bumper & gamepad1.right_bumper) {
           //     BotServo.setPosition(1.0);
         //   } else if (!gamepad1.left_bumper & !gamepad1.right_bumper) {
         //       BotServo.setPosition(0.5);
          //  }

            DuckWheelPower = gamepad1.right_trigger - gamepad1.left_trigger;

            // setting the computed speed value multiplied by the limiter
            FrontLeftDrive.setPower(FrontLeftPower * Multiplier);
            FrontRightDrive.setPower(FrontRightPower * Multiplier);
            BackLeftDrive.setPower(BackLeftPower * Multiplier);
            BackRightDrive.setPower(BackRightPower * Multiplier);

            DuckWheel.setPower(DuckWheelPower);

            telemetry.addData("Run Time: ", runtime.toString());
            telemetry.addData("Speed Limiter: ", "%f", Multiplier);
            telemetry.addData("Duck Wheel Power: ", "%f", DuckWheelPower);
         //   telemetry.addData("Servo Position: ", "%f", BotServo.getPosition());
            telemetry.update();
        }
    }
}

