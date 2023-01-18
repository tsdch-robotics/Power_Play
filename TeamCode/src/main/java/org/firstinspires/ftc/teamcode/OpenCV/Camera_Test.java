package org.firstinspires.ftc.teamcode.OpenCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.firstinspires.ftc.teamcode.Hardware.EncoderFunction;
import java.util.ArrayList;

@Autonomous
public class Camera_Test extends LinearOpMode {

    // Ensuring that the motors are initialized for later reference.
    private DcMotor Quadrant1 = null;
    private DcMotor Quadrant2 = null;
    private DcMotor Quadrant3 = null;
    private DcMotor Quadrant4 = null;
    private DcMotor LinearSlide = null;
    private int zone = 3;

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

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode() {

        // Retrieve the needed information about each motor from the configuration.
        Quadrant1 = hardwareMap.get(DcMotor.class, "quadrant1");
        Quadrant2 = hardwareMap.get(DcMotor.class, "quadrant2");
        Quadrant3 = hardwareMap.get(DcMotor.class, "quadrant3");
        Quadrant4 = hardwareMap.get(DcMotor.class, "quadrant4");

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

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop: - FALSE
         * This REPLACES waitForStart!  - FALSE
         */
        waitForStart();
        while (opModeIsActive()) {

            // Initialize variables for storing computed powers before applying.
            double Quadrant1Power = 0.0;
            double Quadrant2Power = 0.0;
            double Quadrant3Power = 0.0;
            double Quadrant4Power = 0.0;

            // Set all motor directions according to the chassis configuration.
            Quadrant1.setDirection(DcMotor.Direction.FORWARD);
            Quadrant2.setDirection(DcMotor.Direction.FORWARD);
            Quadrant3.setDirection(DcMotor.Direction.REVERSE);
            Quadrant4.setDirection(DcMotor.Direction.REVERSE);


            sleep(1000);
            if (true){

                ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

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
                if (tagOfInterest == null || tagOfInterest.id == Right) {

                    int zone = 3;
            /* what i will write:    NO NO NO NO DON"T DO THIS PLEASE
            calibrate and find area of bounding box at a known distance NO NO NO NO DON"T DO THIS PLEASE
            get x-axis location of tag from camera viewNO NO NO NO DON"T DO THIS PLEASE
            get area of bounding box around tagNO NO NO NO DON"T DO THIS PLEASE
            run motors until tag is centered in frame (y axis aligned) (strafing motion, no rotation)NO NO NO NO DON"T DO THIS PLEASE
            run motors until bounding box area meets calibration data (x axis aligned)NO NO NO NO DON"T DO THIS PLEASE

            initialize encoders (encoder values 0, 0)NO NO NO NO DON"T DO THIS PLEASE
            begin moving precalculated distance to parking zones from reference point -- YES!
             */ } else if (tagOfInterest.id == Middle) {
                    final int zone = 2;

                }else if (tagOfInterest.id == Left) {
                    final int zone = 1;

                }

                //put all the camera code in here

            }

            if (zone == 3){
                //park in 3
            }else if (zone == 2){
//park in 2
            }else if (zone == 1){
//park in 1
            }
        }
    }
}
