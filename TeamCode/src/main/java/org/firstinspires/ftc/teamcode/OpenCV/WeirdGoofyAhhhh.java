package org.firstinspires.ftc.teamcode.OpenCV;

import android.icu.lang.UCharacter;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Hardware.justAutoFUNctions;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import java.util.ArrayList;
import org.firstinspires.ftc.teamcode.Hardware.justAutoFUNctions;

@Autonomous(name="WeirdGoofyAhhhh", group="Autonomous")
public class WeirdGoofyAhhhh extends LinearOpMode {

    justAutoFUNctions robot = new justAutoFUNctions();
    // Ensuring that the motors are initialized for later reference.
    private DcMotor Q1 = null;
    private DcMotor Q2 = null;
    private DcMotor Q3 = null;
    private DcMotor Q4 = null;
    private DcMotor LS = null;
    private Servo IL;
    private Servo IR;

    private DcMotor VRT = null;
    private DcMotor HRZ = null;

    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    // TODO: Recalibrate
    static final double FEET_PER_METER = 3.28084;
    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    // UNITS ARE METERS
    double tagsize = 0.166;
    //Tag IDs
    int Left = 5;
    int Middle = 8;
    int Right = 11;

    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode() {
        // Retrieve the needed information about each motor from the configuration.
        Q1 = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        Q2 = hardwareMap.get(DcMotor.class, "LeftVertical");
        Q3 = hardwareMap.get(DcMotor.class, "BackHorizontal");
        Q4 = hardwareMap.get(DcMotor.class, "RightVertical");
        LS = hardwareMap.get(DcMotor.class, "LinearSlide");
        IL = hardwareMap.servo.get("IntakeLeft");
        IR = hardwareMap.servo.get("IntakeRight");
        LS.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LS.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Q1.setDirection(DcMotor.Direction.FORWARD);
        Q2.setDirection(DcMotor.Direction.FORWARD);
        Q3.setDirection(DcMotor.Direction.REVERSE);
        Q4.setDirection(DcMotor.Direction.REVERSE);


        VRT = hardwareMap.get(DcMotor.class, "verticalOdometry");
        HRZ = hardwareMap.get(DcMotor.class, "horizontalOdometry");

        VRT.setDirection(DcMotor.Direction.FORWARD);
        HRZ.setDirection(DcMotor.Direction.REVERSE);

        VRT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HRZ.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        waitForStart();

        if (opModeIsActive()) {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            // Initialize variables for storing computed powers before applying.

            // Set all motor directions according to the chassis configuration.


            if (currentDetections.size() != 0) {
                boolean tagFound = false;

                for (AprilTagDetection tag : currentDetections) {
                    if (tag.id == Left || tag.id == Middle || tag.id == Right) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }
            }

            // BEGIN PROGRAMMING TAG-DEPENDENT INSTRUCTIONS



            int PARK = 0;


            switch (tagOfInterest.id) {
                case 5:
                    PARK = 1;
                    break;
                case 8:
                    PARK = 3;
                    break;
                case 11:
                    PARK = 2;
                    break;
                default:
                    PARK = 3;
                    break;


            }
            telemetry.addData("", PARK);
            telemetry.update();





            robot.snatch(IR);
            sleep(1500);
            robot.autoMoveSlide(LS, -100);
            sleep(100);
            robot.accF(Q1, Q3, Q2, Q4, .4);
            robot.forward(Q1, Q3, Q2, Q4, HRZ, 67000);
            sleep(500);
            robot.count(Q4, Q3, Q2, Q1, VRT, 18000);
            sleep(500);



            if(PARK == 1){
                //robot.forward(Q1, Q3, Q2, Q4, HRZ, 75172);//adjust
                robot.left(Q1, Q3, Q2, Q4, VRT, 34086);  //1
                sleep(3000);
                robot.autoMoveSlide(LS, 0);
                sleep(2000);
                robot.drop(IR);
                sleep(2000);
            }

            if(PARK == 2){
                sleep(3000);
                robot.autoMoveSlide(LS, 0);
                sleep(2000);
                robot.drop(IR);
                sleep(2000);


            }
            if(PARK == 3){
                robot.right(Q1, Q3, Q2, Q4, VRT, -33086);  //1
                sleep(2300);
                robot.autoMoveSlide(LS, 0);
                sleep(2000);
                robot.drop(IR);
                sleep(2000);
            }

            sleep(10000);



        }
    }
}