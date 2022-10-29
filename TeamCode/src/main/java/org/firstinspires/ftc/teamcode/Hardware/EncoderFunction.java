package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.HardwareMap;

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

        if (!motor1.isBusy() || !motor2.isBusy()){
            motor1.setPower(0);
            motor2.setPower(0);
        }
        if (!motor3.isBusy() || !motor4.isBusy()){
            motor4.setPower(0);
            motor3.setPower(0);
        }
    }

    public void ScorePoleAuto (double speed, DcMotor linearMotor, int targetPos){

        linearMotor.setTargetPosition(targetPos);
        linearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearMotor.setPower(speed);
    }

    public void spinOneFourth (double speed, DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, int spinningDistance ){

        motor1.setTargetPosition(-spinningDistance);
        motor2.setTargetPosition(spinningDistance);
        motor3.setTargetPosition(spinningDistance);
        motor4.setTargetPosition(-spinningDistance);

        motor1.setPower(speed);
        motor2.setPower(speed);
        motor3.setPower(speed);
        motor4.setPower(speed);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);






        //motor1.setTargetPosition(targetPos);
        //motor2.setTargetPosition(targetPos);




//3.78 inches diameter     //540 ticks for one spin  //2.2 inches per 100
        //589 ticks per 4th turn


        //352 to hug pole

       // motor1.setPower(speed);
        //motor2.setPower(speed);

    }

    public void diagonalEncoderMove (double speed, int riseRun, int directional, DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        if (directional == 1){

            motor1.setTargetPosition(motor1.getCurrentPosition() - riseRun);
            motor2.setTargetPosition(motor2.getCurrentPosition() - riseRun);
            motor3.setTargetPosition(motor3.getCurrentPosition() + riseRun);
            motor4.setTargetPosition(motor4.getCurrentPosition() + riseRun);

            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motor1.setPower(speed);
            motor2.setPower(speed);
            motor3.setPower(speed);
            motor4.setPower(speed);
        }


    }

    public void reset (DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }


    /*public boolean hasReachedPosition(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){

        if (motor1.getCurrentPosition() == motor1.getTargetPosition() || motor2.getCurrentPosition() == motor2.getTargetPosition()){
            return (true);
        }
        else if (motor3.getCurrentPosition() == motor3.getTargetPosition() || motor4.getCurrentPosition() == motor4.getTargetPosition()){
            return (true);
        }

        else{
            return(false);
        }




    }*/




}
