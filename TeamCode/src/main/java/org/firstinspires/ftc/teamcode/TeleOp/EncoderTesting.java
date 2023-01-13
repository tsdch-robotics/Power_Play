package org.firstinspires.ftc.teamcode.TeleOp;

import android.text.method.BaseKeyListener;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;

import static java.lang.Thread.sleep;

@TeleOp(name="Encoder Driving", group="LinearOpmode")
public class EncoderTesting extends LinearOpMode {


        //EncoderFunction robot = new EncoderFunction();

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

                FrontHorizontal = hardwareMap.get(DcMotor.class, "FrontHorizontal");
                BackHorizontal = hardwareMap.get(DcMotor.class, "BackHorizontal");
                LeftVertical = hardwareMap.get(DcMotor.class, "LeftVertical");
                RightVertical = hardwareMap.get(DcMotor.class, "RightVertical");
                //fix this

                FrontHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                waitForStart();
                if (opModeIsActive()) {

                        FrontHorizontal.setPower(1);
                        FrontHorizontal.setTargetPosition(1000);
                        FrontHorizontal.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }




        }
}



