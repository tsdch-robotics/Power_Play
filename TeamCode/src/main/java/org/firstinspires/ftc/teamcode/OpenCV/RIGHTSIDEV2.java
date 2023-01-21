package org.firstinspires.ftc.teamcode.OpenCV;

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
    private DcMotor Quadrant1 = null;
    private DcMotor Quadrant2 = null;
    private DcMotor Quadrant3 = null;
    private DcMotor Quadrant4 = null;
    private DcMotor LinearSlide = null;
    private Servo IntakeLeft;
    private Servo IntakeRight;

    private DcMotor VertOdo = null;
    private DcMotor HorizOdo = null;

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
        Quadrant1 = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        Quadrant2 = hardwareMap.get(DcMotor.class, "LeftVertical");
        Quadrant3 = hardwareMap.get(DcMotor.class, "BackHorizontal");
        Quadrant4 = hardwareMap.get(DcMotor.class, "RightVertical");
        LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        IntakeLeft = hardwareMap.servo.get("IntakeLeft");
        IntakeRight = hardwareMap.servo.get("IntakeRight");
        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Quadrant1.setDirection(DcMotor.Direction.FORWARD);
        Quadrant2.setDirection(DcMotor.Direction.FORWARD);
        Quadrant3.setDirection(DcMotor.Direction.REVERSE);
        Quadrant4.setDirection(DcMotor.Direction.REVERSE);


        VertOdo = hardwareMap.get(DcMotor.class, "verticalOdometry");
        HorizOdo = hardwareMap.get(DcMotor.class, "horizontalOdometry");

        VertOdo.setDirection(DcMotor.Direction.FORWARD);
        HorizOdo.setDirection(DcMotor.Direction.REVERSE);

        VertOdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HorizOdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



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







            IntakeLeft.setPosition(0);
            IntakeRight.setPosition(0);
            sleep(1000);

            LinearSlide.setTargetPosition(-300); //level at 0, grabbing
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            LinearSlide.setPower(.6);

            robot.forward(Quadrant1, Quadrant3, Quadrant2, Quadrant4, HorizOdo, 71703);  //1
            sleep(4000);

            robot.right(Quadrant1, Quadrant3, Quadrant2, Quadrant4, VertOdo, -44804);  //2
            sleep(3000);

            LinearSlide.setTargetPosition(-168); //level at 0, grabbing
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            LinearSlide.setPower(.6);
            sleep(1000);
            IntakeLeft.setPosition(1);
            IntakeRight.setPosition(1);
            sleep(2000);
            LinearSlide.setTargetPosition(-300); //level at 0, grabbing
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            LinearSlide.setPower(.6);
            sleep(1000);

            robot.left(Quadrant1, Quadrant3, Quadrant2, Quadrant4, VertOdo, 22885);  //3
            sleep(1000);

            LinearSlide.setTargetPosition(-1084); //level at 0, grabbing
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            LinearSlide.setPower(.6);

            robot.forward(Quadrant1, Quadrant3, Quadrant2, Quadrant4, HorizOdo, 76834);  //4
            sleep(1000);

            robot.right(Quadrant1, Quadrant3, Quadrant2, Quadrant4, VertOdo, 18307);  //5
            sleep(1000);

            IntakeLeft.setPosition(1);
            IntakeRight.setPosition(1);
            sleep(2500);

            robot.left(Quadrant1, Quadrant3, Quadrant2, Quadrant4, VertOdo, 23476);  //6
            sleep(1000);

            robot.backward(Quadrant1, Quadrant3, Quadrant2, Quadrant4, HorizOdo, 60811);  //7
            sleep(1000);

            LinearSlide.setTargetPosition(-1084); //level at 0, grabbing
            LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            LinearSlide.setPower(.6);

            robot.right(Quadrant1, Quadrant3, Quadrant2, Quadrant4, VertOdo, -41841);  //8
            sleep(1000);







            if(PARK == 1){
                robot.left(Quadrant1, Quadrant3, Quadrant2, Quadrant4, HorizOdo, 62199);  //1
                sleep(1000);
            }

            if(PARK == 2){
                //robot.forward(Quadrant1, Quadrant3, Quadrant2, Quadrant4);
                robot.left(Quadrant1, Quadrant3, Quadrant2, Quadrant4, HorizOdo, 62847);
                sleep(10000);


            }
            if(PARK == 3){
               // robot.forward(Quadrant1, Quadrant3, Quadrant2, Quadrant4);
                robot.left(Quadrant1, Quadrant3, Quadrant2, Quadrant4, HorizOdo, 63696);  //1
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