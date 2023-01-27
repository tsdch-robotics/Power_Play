package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static java.lang.Math.*;
import static java.lang.Thread.sleep;

//import org.firstinspires.ftc.teamcode.TestFiles.PIDController;
//import org.firstinspires.ftc.teamcode.TestFiles.PIDControllerHor;

// Vision imports


/*
 * This is NOT an opmode. This file defines all the hardware on the robot
 * and some common helper functions (stop motors, reset encoders, etc.)
 */
public class notFUNctions {

    public double slidePower = 0.6;
    public double openPos = 0;
    public double closePos = 1;
    public DcMotor motor1;
    public DcMotor motor2;
    public DcMotor motor3;
    public DcMotor motor4;
    public DistanceSensor sensorRange2;

    public double allTimeSpeed = .4;//might be different
    public int targetPos;



    //private DcMotor LeftVertical ;
    //private DcMotor RightVertical ;

    public void init(HardwareMap ahwMap) {

    }

    public void prepMotors(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4) {
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        /*
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

         */
    }

    public Double[] computePower(Double x, Double y) {
        Double returnValues[] = new Double[3];

        Double hypotenuse = sqrt(x*x + y*y);

        Double angle = 0.0;

        Double powerX = 0.0;
        Double powerY = 0.0;

        if (x > 0) {
            angle = atan(y/x);
        } else if (x < 0) {
            angle = atan(y/x) + PI;
        } else if (x == 0) {
            if (y > 0) {
                angle = PI/2;
            } else if (y < 0) {
                angle = (3*PI)/2;
            } else if (y == 0) {
                angle = 0.0;
            }
        }

        powerX = sin(angle + PI/4)*hypotenuse;
        powerY = cos(angle + PI/4)*hypotenuse;

        powerX = 1.4 * powerX;
        powerY = 1.4 * powerY;

        returnValues[0] = powerX;
        returnValues[1] = powerY;
        returnValues[2] = angle;

        return returnValues;
    }

    public void encoderSetUp (DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //POSIBLE SOURCE FOR ERROR
//
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void autoMoveSlide(DcMotor slider, int height){
        slider.setTargetPosition(height);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        slider.setPower(slidePower);
    }

    public void drop(Servo intakeClaw){
        intakeClaw.setPosition(0);
    }
    public void snatch(Servo intakeClaw){
        intakeClaw.setPosition(1);
    }

    //when the blasted code DOESN'T WORK
    public void sleepThread(Long millis) {    //sleep function
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}