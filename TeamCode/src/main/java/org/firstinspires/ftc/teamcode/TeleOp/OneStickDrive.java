package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;

// define the teleop to appear on the phone
@TeleOp(name="One Stick Drive", group="Linear Opmode")
public class OneStickDrive extends LinearOpMode {

    EncoderFunction robot = new EncoderFunction();

    // defining all the variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor Quadrant1 = null;
    private DcMotor Quadrant2 = null;
    private DcMotor Quadrant3 = null;
    private DcMotor Quadrant4 = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // read the motors from the configuration
        Quadrant1  = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        Quadrant2 = hardwareMap.get(DcMotor.class, "LeftVertical");
        Quadrant3 = hardwareMap.get(DcMotor.class, "BackHorizontal");
        Quadrant4 = hardwareMap.get(DcMotor.class, "RightVertical");


        Quadrant1.setDirection(DcMotor.Direction.FORWARD);
        Quadrant2.setDirection(DcMotor.Direction.REVERSE);
        Quadrant3.setDirection(DcMotor.Direction.FORWARD);
        Quadrant4.setDirection(DcMotor.Direction.REVERSE);

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

            // god awful code if else if else if else
            robot.oneStickDrive(Quadrant1, Quadrant2, Quadrant3, Quadrant4, (double) gamepad1.right_stick_x, (double) gamepad1.right_stick_y);
        }
    }
}

