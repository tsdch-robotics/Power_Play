package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="TestIntakeServos", group="Linear Opmode")
public class ServoTester extends LinearOpMode {
    private Servo IntakeLeft = null;
    private Servo IntakeRight = null;

    @Override
    public void runOpMode() {
        IntakeLeft = hardwareMap.servo.get("IntakeLeft");
        IntakeRight = hardwareMap.servo.get("IntakeRight");

        while (opModeIsActive()) {
            telemetry.addData("Left Servo Test One:", "Running");
            telemetry.update();
            IntakeLeft.setPosition(0.2);

            telemetry.addData("Left Servo Test One:", "Complete");
            telemetry.addData("Left Servo Test Two:", "Running");
            telemetry.update();
            IntakeLeft.setPosition(-0.2);

            telemetry.addData("Left Servo Test Two:", "Complete");
            telemetry.addData("Right Servo Test One:", "Running");
            telemetry.update();
            IntakeRight.setPosition(0.2);

            telemetry.addData("Right Servo Test One:", "Complete");
            telemetry.addData("Right Servo Test Two:", "Running");
            telemetry.update();
            IntakeRight.setPosition(-0.2);

            telemetry.addData("Right Servo Test Two:", "Complete");
            telemetry.update();
        }
    }
}
