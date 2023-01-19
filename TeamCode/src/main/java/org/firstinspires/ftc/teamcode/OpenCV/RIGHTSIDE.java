/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

import static java.lang.Math.PI;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;


@Autonomous(name="RIGHTSIDE", group="Autonomous")
public class RIGHTSIDE extends LinearOpMode {

    // Ensuring that the motors are initialized for later reference.
    private DcMotor Quadrant1 = null;
    private DcMotor Quadrant2 = null;
    private DcMotor Quadrant3 = null;
    private DcMotor Quadrant4 = null;

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


    AprilTagDetection tagOfInterest = null;

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

    public void haltRobot() {
        Quadrant1.setPower(0.0);
        Quadrant2.setPower(0.0);
        Quadrant3.setPower(0.0);
        Quadrant4.setPower(0.0);
    }

    @Override
    public void runOpMode() {

        // Retrieve the needed information about each motor from the configuration.
        Quadrant1 = hardwareMap.get(DcMotor.class, "FrontHorizontal");
        Quadrant2 = hardwareMap.get(DcMotor.class, "LeftVertical");
        Quadrant3 = hardwareMap.get(DcMotor.class, "BackHorizontal");
        Quadrant4 = hardwareMap.get(DcMotor.class, "RightVertical");

        VertOdo = hardwareMap.get(DcMotor.class, "verticalOdometry");
        HorizOdo = hardwareMap.get(DcMotor.class, "horizontalOdometry");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

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

            VertOdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            HorizOdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            if (currentDetections.size() != 0) {
                boolean tagFound = false;

                for (AprilTagDetection tag : currentDetections) {
                    if (tag.id == Left || tag.id == Middle || tag.id == Right) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if (tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        // BEGIN PROGRAMMING TAG-DEPENDENT INSTRUCTIONS
        if (tagOfInterest == null || tagOfInterest.id == Right) {
            /* what i will write:
            calibrate and find area of bounding box at a known distance
            get x-axis location of tag from camera view
            get area of bounding box around tag
            run motors until tag is centered in frame (y axis aligned) (strafing motion, no rotation)
            run motors until bounding box area meets calibration data (x axis aligned)

            initialize encoders (encoder values 0, 0)
            begin moving precalculated distance to parking zones from reference point
             */
            Quadrant1.setPower(0.3);
            Quadrant2.setPower(-0.3);
            Quadrant3.setPower(0.3);
            Quadrant4.setPower(-0.3);

            if (VertOdo.getCurrentPosition() > 10000) {
                haltRobot();
            }//"

        } else if(tagOfInterest.id == Middle) {

            Quadrant1.setPower(0.3);
            Quadrant2.setPower(-0.3);
            Quadrant3.setPower(0.3);
            Quadrant4.setPower(-0.3);

            if (VertOdo.getCurrentPosition() > 5000) {
                haltRobot();
            }//do other things

        } else if(tagOfInterest.id == Left) {
            Quadrant1.setPower(0.3);
            Quadrant2.setPower(-0.3);
            Quadrant3.setPower(0.3);
            Quadrant4.setPower(-0.3);

            if (VertOdo.getCurrentPosition() > 20000) {
                haltRobot();
            }
//"

        }

        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        while (opModeIsActive()) {sleep(20);}
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }
}