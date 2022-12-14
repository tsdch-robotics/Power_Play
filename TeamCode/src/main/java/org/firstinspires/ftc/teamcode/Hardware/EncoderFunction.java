package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.*;
import java.lang.Thread.*;
import static java.lang.Thread.sleep;
import org.firstinspires.ftc.robotcore.external.Telemetry;

//import org.firstinspires.ftc.teamcode.TestFiles.PIDController;
//import org.firstinspires.ftc.teamcode.TestFiles.PIDControllerHor;

// Vision imports


/*
 * This is NOT an opmode. This file defines all the hardware on the robot
 * and some common helper functions (stop motors, reset encoders, etc.)
 */
public class EncoderFunction {

    public DcMotor motor1;
    public DcMotor motor2;
    public DcMotor motor3;
    public DcMotor motor4;

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

        returnValues[0] = powerX;
        returnValues[1] = powerY;
        returnValues[2] = angle;

        return returnValues;
    }

    /*
    public void oneStickDrive(DcMotor motor1,
                              DcMotor motor2,
                              DcMotor motor3,
                              DcMotor motor4,
                              Double gamepadX,
                              Double gamepadY) {
        prepMotors(motor1, motor2, motor3, motor4);

        Double powerValues[] = computePower(gamepadX, gamepadY);

        Double powerX = powerValues[0];
        Double powerY = powerValues[1];
        Double angle = powerValues[2];

        motor1.setPower(powerX);
        motor2.setPower(powerX);

        motor3.setPower(powerY);
        motor4.setPower(powerY);
    }
     */

    public void encoderSetUp (DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

       // motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    //    motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       // motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       // motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //POSIBLE SOURCE FOR ERROR
//
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void EncoderDrive (double speed, double speed2, DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, int targetPos1, int targetPos2 ) {

        motor1.setTargetPosition(targetPos1);
        motor2.setTargetPosition(targetPos1);
        motor3.setTargetPosition(targetPos2);
        motor4.setTargetPosition(targetPos2);

        motor1.setPower(speed);
        motor2.setPower(speed);
        motor3.setPower(speed2);
        motor4.setPower(speed2);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (!motor1.isBusy() && !motor2.isBusy()){
            motor1.setPower(0);
            motor2.setPower(0);
        }
        if (!motor3.isBusy() && !motor4.isBusy()){
            motor4.setPower(0);
            motor3.setPower(0);
        }
    }

    public void ScorePoleAuto (double speed, DcMotor linearMotor, int targetPos){

        linearMotor.setTargetPosition(targetPos);
        linearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearMotor.setPower(speed);
    }

    public void spinOneFourth (DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, int spinningDistance ){

        motor1.setTargetPosition(-spinningDistance);
        motor2.setTargetPosition(spinningDistance);
        motor3.setTargetPosition(spinningDistance);
        motor4.setTargetPosition(-spinningDistance);


        motor1.setPower(1);
        motor2.setPower(1);
        motor3.setPower(1);
        motor4.setPower(1);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }


    public void spinByDegreeVal (DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, int degreeVal, int distance){



            motor1.setTargetPosition(-distance);
            motor2.setTargetPosition(distance);
            motor3.setTargetPosition(distance);
            motor4.setTargetPosition(-distance);

            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motor1.setPower(0.25);
            motor2.setPower(0.25);
            motor3.setPower(0.25);
            motor4.setPower(0.25);




    }

    public void diagonalEncoderMove (double speed, int riseRun, int directional, DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        if (directional == 1){

            motor1.setTargetPosition(motor1.getCurrentPosition() - riseRun);
            motor2.setTargetPosition(motor2.getCurrentPosition() - riseRun);
            motor3.setTargetPosition(motor3.getCurrentPosition() + riseRun);
            motor4.setTargetPosition(motor4.getCurrentPosition() + riseRun);

            motor1.setPower(speed);
            motor2.setPower(speed);
            motor3.setPower(speed);
            motor4.setPower(speed);

            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        }
    }

    public void reset (DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    //public void moveTileDiagonal(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, String direction){

    public void diagonalForward(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, int targetPosition){

        motor1.setTargetPosition(targetPosition);
        motor2.setTargetPosition(targetPosition);
        motor3.setTargetPosition(targetPosition);
        motor4.setTargetPosition(targetPosition);


        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor1.setPower(0.25);
        motor2.setPower(0.25);
        motor3.setPower(0.25);
        motor4.setPower(0.25);

        /*if (!motor1.isBusy() || !motor2.isBusy()){
            motor1.setPower(0);
            motor2.setPower(0);
        }
        if (!motor3.isBusy() || !motor4.isBusy()){
            motor4.setPower(0);
            motor3.setPower(0);
        }*/
    }

    public void diagonalBackward(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, int targetPosition) {

        motor1.setTargetPosition(-targetPosition);
        motor2.setTargetPosition(-targetPosition);
        motor3.setTargetPosition(-targetPosition);
        motor4.setTargetPosition(-targetPosition);

        motor1.setPower(0.25);
        motor2.setPower(0.25);
        motor3.setPower(0.25);
        motor4.setPower(0.25);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (!motor1.isBusy() || !motor2.isBusy()) {
            motor1.setPower(0);
            motor2.setPower(0);
        }
        if (!motor3.isBusy() || !motor4.isBusy()) {
            motor4.setPower(0);
            motor3.setPower(0);
        }

    }

    public void diagonalLeft(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4) {

        motor1.setTargetPosition(1000);
        motor2.setTargetPosition(1000);
        motor3.setTargetPosition(-1000);
        motor4.setTargetPosition(-1000);

        motor1.setPower(1);
        motor2.setPower(1);
        motor3.setPower(1);
        motor4.setPower(1);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (!motor1.isBusy() || !motor2.isBusy()) {
            motor1.setPower(0);
            motor2.setPower(0);
        }
        if (!motor3.isBusy() || !motor4.isBusy()) {
            motor4.setPower(0);
            motor3.setPower(0);
        }

    }

    public void diagonalRight(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        motor1.setTargetPosition(-1000);
        motor2.setTargetPosition(-1000);
        motor3.setTargetPosition(1000);
        motor4.setTargetPosition(1000);

        motor1.setPower(1);
        motor2.setPower(1);
        motor3.setPower(1);
        motor4.setPower(1);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (!motor1.isBusy() || !motor2.isBusy()){
            motor1.setPower(0);
            motor2.setPower(0);
        }
        if (!motor3.isBusy() || !motor4.isBusy()){
            motor4.setPower(0);
            motor3.setPower(0);
        }

    }

    public void autoMoveSlide(DcMotor slider, int height){
        if (height < 4000 && height > 2700){
            slider.setTargetPosition(3500);
            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slider.setPower(1);
            //score

        }

        else if (height <= 2700 && height > 1000){
            //then do this
            slider.setTargetPosition(2500);
            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slider.setPower(1);
        }

        else if (height < 1000 && height > 50){
            //then do this
            slider.setTargetPosition(800);
            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slider.setPower(1);
        }

        else if (height < 50 && height > -10){
            //then do this
            slider.setTargetPosition(20);
            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slider.setPower(1);
        }
    }

    public void drop(Servo intakeClaw, double openPos){
        intakeClaw.setPosition(openPos);
    }

    public boolean hasReachePosition(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        if (motor1.getCurrentPosition() == motor1.getTargetPosition()){
            return true;
        }


        else if (motor2.getCurrentPosition() == motor2.getTargetPosition()){
            return true;
        }


        else if (motor3.getCurrentPosition() == motor3.getTargetPosition()){
            return true;
        }


        else if (motor4.getCurrentPosition() == motor4.getTargetPosition()){
            return true;
        }


        else{
            return false;
        }
    }


    public void sleepThread(Long millis) {    //sleep function
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scoreThisCorner(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, int Corner, int height, DcMotor slider, Servo intakeClaw){

        if (Corner == 0){

            spinByDegreeVal(motor1, motor2, motor3, motor4, 45, -280);
            sleepThread(1000L);
            reset(motor1, motor2, motor3, motor4);
            autoMoveSlide(slider, 2000);
            diagonalForward(motor1, motor2, motor3, motor4, 500);
            sleepThread(2000L);
            drop(intakeClaw, 1);
            sleepThread(5000L);
            reset(motor1, motor2, motor3, motor4);
            diagonalBackward(motor1, motor2, motor3, motor4, 500);
            sleepThread(2000L);
            reset(motor1, motor2, motor3, motor4);
            spinByDegreeVal(motor1, motor2, motor3, motor4, -45, 280);
            sleepThread(2000L);
            autoMoveSlide(slider, 100);
            sleepThread(2000L);
            //autoMoveSlide(slider, 60);
        }










    }



}
