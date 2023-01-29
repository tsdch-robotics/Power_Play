package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
            motor1.setPower(.2);
            motor2.setPower(.2);
            motor3.setPower(.2);
            motor4.setPower(.2);
        }

        while (OD.getCurrentPosition() <= targ) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
        }

    }

    public void left(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ){


        /*while (OD.getCurrentPosition() > targ) {
            motor1.setPower(-allTimeSpeed );
            motor2.setPower(-allTimeSpeed );
            motor3.setPower(allTimeSpeed - .12);
            motor4.setPower(allTimeSpeed -.12); //correction term
        }*/


        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() < targ) {
            motor1.setPower(allTimeSpeed +0.2);
            motor2.setPower(allTimeSpeed + 0.2);
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
            motor1.setPower(allTimeSpeed - .2);
            motor2.setPower(allTimeSpeed - .2);
            motor3.setPower(-allTimeSpeed + .2);
            motor4.setPower(-allTimeSpeed + .2);
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

        while (OD.getCurrentPosition() < targ && !((sensorRange2.getDistance(DistanceUnit.MM)) >= 50 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 400)) {
            motor1.setPower(allTimeSpeed - .3);
            motor2.setPower(allTimeSpeed - .3);
            motor3.setPower(-allTimeSpeed + .3);
            motor4.setPower(-allTimeSpeed + .3);
        }

        while (Math.abs(OD.getCurrentPosition()) >= targ || ((sensorRange2.getDistance(DistanceUnit.MM)) >= 50 && (sensorRange2.getDistance(DistanceUnit.MM)) <=400)){
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }

        while (Math.abs(OD.getCurrentPosition()) >= targ){
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }


    }

    public void counter(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ, DistanceSensor sensorRange2){

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() > targ /*&& !((sensorRange2.getDistance(DistanceUnit.MM)) >= 50 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 400)*/) {
            motor1.setPower(-allTimeSpeed + .2);
            motor2.setPower(-allTimeSpeed + .2);
            motor3.setPower(allTimeSpeed - .2);
            motor4.setPower(allTimeSpeed - .2);
        }
/*
        while (Math.abs(OD.getCurrentPosition()) <= targ || ((sensorRange2.getDistance(DistanceUnit.MM)) >= 50 && (sensorRange2.getDistance(DistanceUnit.MM)) <=400)){
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }
*/
        while (Math.abs(OD.getCurrentPosition()) <= targ){
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }


    }


    public void counterS(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ, DistanceSensor sensorRange2){

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() > targ && !((sensorRange2.getDistance(DistanceUnit.MM)) >= 400 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 800)) {
            motor1.setPower(-allTimeSpeed + .3);
            motor2.setPower(-allTimeSpeed + .3);
            motor3.setPower(allTimeSpeed - .3);
            motor4.setPower(allTimeSpeed - .3);
        }

        while (Math.abs(OD.getCurrentPosition()) <= targ || ((sensorRange2.getDistance(DistanceUnit.MM)) >= 400 && (sensorRange2.getDistance(DistanceUnit.MM)) <=800)){
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }

        while (Math.abs(OD.getCurrentPosition()) <= targ){
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

    public void rightS(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ, DistanceSensor sensorRange2) {


        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (OD.getCurrentPosition() > targ && !((sensorRange2.getDistance(DistanceUnit.MM)) >= 5 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 90)) {
            motor1.setPower(-allTimeSpeed );
            motor2.setPower(-allTimeSpeed );
            motor3.setPower(allTimeSpeed - .12);
            motor4.setPower(allTimeSpeed -.12); //correction term
        }

        while (OD.getCurrentPosition() <= targ || (sensorRange2.getDistance(DistanceUnit.MM)) >= 5 && (sensorRange2.getDistance(DistanceUnit.MM)) <= 90) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }



    }


    public void rightIandB(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor ODhrz, DcMotor ODvrt, DcMotor slider, DistanceSensor sensorRange2, Servo servo, int Ci, int Cc) {

        autoMoveSlide(slider, Ci);
        sleepThread(200L);
        rightS(motor1, motor2, motor3, motor4, ODvrt, -35000, sensorRange2);
        sleepThread(500L);
        autoMoveSlide(slider, Cc);
        sleepThread(700L);
        snatch(servo);
        sleepThread(1500L);
        autoMoveSlide(slider, Ci);
        sleepThread(1000L);
        left(motor1, motor2, motor3, motor4, ODvrt, -3000);
        sleepThread(100L);

    }

    public void justGetUsTherePls(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor OD, int targ, double getThere, DcMotor slider, Servo servo){


        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        snatch(servo);
        sleepThread(1500L);
        autoMoveSlide(slider, -300);
        sleepThread(200L);

        accF(motor1, motor2, motor3, motor4, getThere); //build up speed casually
        while (OD.getCurrentPosition() < targ && (targ - OD.getCurrentPosition()) > 10000) {// targ - current
            motor1.setPower(-getThere);
            motor2.setPower(-getThere);
            motor3.setPower(-getThere + .11);
            motor4.setPower(-getThere + .11);//corection num, probably change, DO A PERCENTAGE INSTEAD
        }

        while (OD.getCurrentPosition() <  targ && (targ - OD.getCurrentPosition()) <= 100000) {// targ - current
            motor1.setPower(-.25);
            motor2.setPower(-.25);
            motor3.setPower(-.25 + .05);
            motor4.setPower(-.25 + .05);//corection num, probably change, DO A PERCENTAGE INSTEAD
        }

        //  could above code use to decelarate

        while (OD.getCurrentPosition() >= targ) {//normal, posi
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
            break;
        }

        //note the directions of the used motors*****

    }

    public void scorAFrickingConeRi(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, DcMotor ODhrz, DcMotor ODvrt, double accSpeedRL, double accSpeedFB, DistanceSensor sensorRange2, DcMotor slider, Servo servo){

        //(scores a frickin' cone) - the title says it all
        //assuming we are in the middle of tile, high pole, intake faces wall
        //iT'sNoTaCoNe,It'S,aSiGnal - FTC 'Judges Choice' quote of the year, 2nd place 'particular and tidy' award
                                                 //make sure we are strait
     /*   backward(motor1, motor2, motor3, motor4, ODhrz, 67000);
        sleepThread(200L);*/

        //count(motor4, motor2, motor3, motor1, ODvrt, 6000);     //clear low pole distance feedback

        left(motor1, motor2, motor3, motor4, ODvrt, 0);
        right(motor1, motor2, motor3, motor4, ODvrt, 0);

        sleepThread(500L);

        count(motor4, motor2, motor3, motor1, ODvrt, 13000);//13000
        countS(motor4, motor2, motor3, motor1, ODvrt, 15000, sensorRange2);//15000
        sleepThread(500L);

        autoMoveSlide(slider, -1130);   //scoring position
        sleepThread(500L);

        right(motor1, motor2, motor3, motor4, ODvrt, 0); //adjust valu//-100
        sleepThread(1000L);                     //move to score

        drop(servo);
        sleepThread(1500L); //drop the cone and pause

        left(motor1, motor2, motor3, motor4, ODvrt, 6000);//technically, but need to adjust, 1000 //7000
        sleepThread(500L);


        autoMoveSlide(slider, -300);   //scoring position
        sleepThread(100L);


        counter(motor4, motor2, motor3, motor1, ODvrt, 2000, sensorRange2);
      //  counterS(motor4, motor2, motor3, motor1, ODvrt, -10000, sensorRange2); //adjust, aswell     **SAYS RIGHTS, moves COUNTERCLOCK
        sleepThread(500L);

        forward(motor1, motor2, motor3, motor4, ODhrz, 67000);
        sleepThread(100L);

    }

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