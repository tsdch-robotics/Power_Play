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
public class justAutoFUNctions {

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



    //intaking functions
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


//end of intaking functions





    public void sleepThread(Long millis) {    //sleep function
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






    public void forward(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ){


        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() < targ) {// targ - current
            motor1.setPower(-allTimeSpeed);
            motor2.setPower(-allTimeSpeed);
            motor3.setPower(-allTimeSpeed + .11);
            motor4.setPower(-allTimeSpeed + .11);//corection num
        }

        while (OD.getCurrentPosition() >= targ) {//normal, posi
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }

        //note the directions of the used motors*****

    }

    public void backward(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ) {


        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() > targ) {
            motor1.setPower(allTimeSpeed);
            motor2.setPower(allTimeSpeed);
            motor3.setPower(allTimeSpeed);
            motor4.setPower(allTimeSpeed);
        }

        while (OD.getCurrentPosition() <= targ) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
        }

    }

    public void left(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ){

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() < targ) {
            motor1.setPower(allTimeSpeed);
            motor2.setPower(allTimeSpeed);
            motor3.setPower(-allTimeSpeed + 0.1);
            motor4.setPower(-allTimeSpeed + 0.1);
        }

        while (Math.abs(OD.getCurrentPosition()) >= targ) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }


        //note the directions of the used motors*****
    }


































    public void count(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ) {

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() < targ) {
            motor1.setPower(allTimeSpeed);
            motor2.setPower(allTimeSpeed);
            motor3.setPower(-allTimeSpeed);
            motor4.setPower(-allTimeSpeed);
        }

        while (Math.abs(OD.getCurrentPosition()) >= targ) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }

    }

    public void countS(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ, DistanceSensor sensorRange2){

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() < targ && !((sensorRange2.getDistance(DistanceUnit.MM)) >= 5 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 400)) {
            motor1.setPower(allTimeSpeed);
            motor2.setPower(allTimeSpeed);
            motor3.setPower(-allTimeSpeed);
            motor4.setPower(-allTimeSpeed);
        }

        while (Math.abs(OD.getCurrentPosition()) >= targ) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }


    }
    public void right(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ) {


        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() > targ) {
            motor1.setPower(-allTimeSpeed );
            motor2.setPower(-allTimeSpeed );
            motor3.setPower(allTimeSpeed - .12);
            motor4.setPower(allTimeSpeed -.12); //correction term
        }

        while (OD.getCurrentPosition() <= targ) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }



    }



    public void rightI(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ, DistanceSensor sensorRange2) {


        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() > targ && !((sensorRange2.getDistance(DistanceUnit.MM)) >= 5 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 65)) {
            motor1.setPower(-allTimeSpeed );
            motor2.setPower(-allTimeSpeed );
            motor3.setPower(allTimeSpeed - .12);
            motor4.setPower(allTimeSpeed -.12); //correction term
        }

        while (OD.getCurrentPosition() <= targ || (sensorRange2.getDistance(DistanceUnit.MM)) >= 5 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 65) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }



    }












//new functions

















    public void accF(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double speed){

        motor1.setPower(-0);
        motor2.setPower(-0);
        motor3.setPower(-0);
        motor4.setPower(-0);
        sleepThread(100L);
        motor1.setPower(-0.2 * speed);
        motor2.setPower(-0.2 * speed);
        motor3.setPower(-0.2 * speed);
        motor4.setPower(-0.2 * speed);
        sleepThread(200L);
        motor1.setPower(-0.3 * speed);
        motor2.setPower(-0.3 * speed);
        motor3.setPower(-0.3 * speed);
        motor4.setPower(-0.3 * speed);
        sleepThread(200L);
        motor1.setPower(-0.4 * speed);
        motor2.setPower(-0.4 * speed);
        motor3.setPower(-0.4 * speed);
        motor4.setPower(-0.4 * speed);
        sleepThread(200L);
        motor1.setPower(-0.5 * speed);
        motor2.setPower(-0.5 * speed);
        motor3.setPower(-0.5 * speed);
        motor4.setPower(-0.5 * speed);
        sleepThread(200L);
        motor1.setPower(-0.6 * speed);
        motor2.setPower(-0.6 * speed);
        motor3.setPower(-0.6 * speed);
        motor4.setPower(-0.6 * speed);
        sleepThread(200L);
        motor1.setPower(-0.7 * speed);
        motor2.setPower(-0.7 * speed);
        motor3.setPower(-0.7 * speed);
        motor4.setPower(-0.7 * speed);
        sleepThread(200L);
        motor1.setPower(-0.8 * speed);
        motor2.setPower(-0.8 * speed);
        motor3.setPower(-0.8 * speed);
        motor4.setPower(-0.8 * speed);
        sleepThread(200L);
       /* motor1.setPower(-0.5);
        motor2.setPower(-0.5);
        motor3.setPower(-0.5);
        motor4.setPower(-0.5);
        sleepThread(100L);
        */
    }


    public void accB(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double speed){

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
        sleepThread(100L);
        motor1.setPower(0.2 * speed);
        motor2.setPower(0.2 * speed);
        motor3.setPower(0.2 * speed);
        motor4.setPower(0.2 * speed);
        sleepThread(200L);
        motor1.setPower(0.3 * speed);
        motor2.setPower(0.3 * speed);
        motor3.setPower(0.3 * speed);
        motor4.setPower(0.3 * speed);
        sleepThread(200L);
        motor1.setPower(0.4 * speed);
        motor2.setPower(0.4 * speed);
        motor3.setPower(0.4 * speed);
        motor4.setPower(0.4 * speed);
        sleepThread(200L);
        motor1.setPower(0.5 * speed);
        motor2.setPower(0.5 * speed);
        motor3.setPower(0.5 * speed);
        motor4.setPower(0.5 * speed);
        sleepThread(200L);
        motor1.setPower(0.6 * speed);
        motor2.setPower(0.6 * speed);
        motor3.setPower(0.6 * speed);
        motor4.setPower(0.6 * speed);
        sleepThread(200L);
        motor1.setPower(0.7 * speed);
        motor2.setPower(0.7 * speed);
        motor3.setPower(0.7 * speed);
        motor4.setPower(0.7 * speed);
        sleepThread(200L);
        motor1.setPower(0.8 * speed);
        motor2.setPower(0.8 * speed);
        motor3.setPower(0.8 * speed);
        motor4.setPower(0.8 * speed);
        sleepThread(200L);
       /* motor1.setPower(-0.5);
        motor2.setPower(-0.5);
        motor3.setPower(-0.5);
        motor4.setPower(-0.5);
        sleepThread(100L);
        */
    }


    public void accR(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double speed){

        motor1.setPower(-0);
        motor2.setPower(-0);
        motor3.setPower(0);
        motor4.setPower(0);
        sleepThread(100L);
        motor1.setPower(-0.2 * speed);
        motor2.setPower(-0.2 * speed);
        motor3.setPower(0.2 * speed);
        motor4.setPower(0.2 * speed);
        sleepThread(50L);
        motor1.setPower(-0.3 * speed);
        motor2.setPower(-0.3 * speed);
        motor3.setPower(0.3 * speed);
        motor4.setPower(0.3 * speed);
        sleepThread(50L);
        motor1.setPower(-0.4 * speed);
        motor2.setPower(-0.4 * speed);
        motor3.setPower(0.4 * speed);
        motor4.setPower(0.4 * speed);
        sleepThread(50L);
        motor1.setPower(-0.5 * speed);
        motor2.setPower(-0.5 * speed);
        motor3.setPower(0.5 * speed);
        motor4.setPower(0.5 * speed);
        sleepThread(50L);
        motor1.setPower(-0.6 * speed);
        motor2.setPower(-0.6 * speed);
        motor3.setPower(0.6 * speed);
        motor4.setPower(0.6 * speed);
        sleepThread(50L);
        motor1.setPower(-0.7 * speed);
        motor2.setPower(-0.7 * speed);
        motor3.setPower(0.7 * speed);
        motor4.setPower(0.7 * speed);
        sleepThread(50L);
        motor1.setPower(-0.8 * speed);
        motor2.setPower(-0.8 * speed);
        motor3.setPower(0.8 * speed);
        motor4.setPower(0.8 * speed);
        sleepThread(50L);
    }


}