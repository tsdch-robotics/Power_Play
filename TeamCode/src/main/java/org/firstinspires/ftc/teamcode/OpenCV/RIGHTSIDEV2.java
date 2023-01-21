package org.firstinspires.ftc.teamcode.OpenCV;

import android.icu.lang.UCharacter;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import java.util.ArrayList;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;

@Autonomous(name="RIGHTSIDE2", group="Autonomous")
public class RIGHTSIDEV2 extends LinearOpMode {

    EncoderFunction robot = new EncoderFunction();
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
            sleep(1000);







            robot.snatch(IL);
            sleep(1000);
            robot.autoMoveSlide(LS, -300);
            robot.forward(Q1, Q2, Q3, Q4, HRZ, 71703);  //1
            sleep(4000);
            //spin counter clockwise
            robot.spinUntil1(Q1, Q2, Q3, Q4, VRT, 71703);//might be spin2
//go to the cones
            robot.right(Q1, Q2, Q3, Q4, VRT, -44804);  //2
            sleep(3000);
            robot.autoMoveSlide(LS, -168);
            sleep(1000);
            robot.snatch(IL);
            sleep(2000);
            robot.autoMoveSlide(LS, -300);
            sleep(1000);
            robot.left(Q1, Q2, Q3, Q4, VRT, 22885);  //3
            sleep(1000);
            robot.autoMoveSlide(LS, -1084);
            robot.forward(Q1, Q2, Q3, Q4, HRZ, 76834);  //4
            sleep(1000);
            robot.right(Q1, Q2, Q3, Q4, VRT, 18307);  //5
            sleep(1000);

            robot.snatch(IL);
            sleep(2500);
            robot.left(Q1, Q2, Q3, Q4, VRT, 23476);  //6
            sleep(1000);
            robot.backward(Q1, Q2, Q3, Q4, HRZ, 60811);  //7
            sleep(1000);

            robot.autoMoveSlide(LS, 0);
            robot.right(Q1, Q2, Q3, Q4, VRT, -41841);  //8
            sleep(1000);





            if(PARK == 1){
                robot.left(Q1, Q2, Q3, Q4, HRZ, 62199);  //1
                sleep(1000);
            }

            if(PARK == 2){
                //robot.forward(Quadrant1, Quadrant3, Quadrant2, Quadrant4);
                robot.left(Q1, Q2, Q3, Q4, HRZ, 62847);
                sleep(10000);


            }
            if(PARK == 3){
               // robot.forward(Quadrant1, Quadrant3, Quadrant2, Quadrant4);
                robot.left(Q1, Q2, Q3, Q4, HorizOdo, 63696);  //1
                sleep(1000);
            }
            if(PARK == 4){
               // robot.forward(Quadrant1, Quadrant3, Quadrant2, Quadrant4);
                robot.left(Quadrant1, Quadrant3, Quadrant2, Quadrant4, HorizOdo, 63696);  //1
                sleep(1000);
            }
            sleep(100000);



        }
    }
}