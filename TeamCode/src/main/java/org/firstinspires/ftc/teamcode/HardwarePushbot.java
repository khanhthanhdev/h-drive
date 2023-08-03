package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.Telemetry;


public class HardwarePushbot
{
//    /* Public OpMode members. */
//    public DcMotor  leftBack = null;
//    public DcMotor  rightBack = null;
//
//    /* local OpMode members. */
//    HardwareMap hwMap           =  null;
//    private ElapsedTime period  = new ElapsedTime();
//
//    /* Constructor */
//    public HardwarePushbot(){
//
//    }
//
//    /* Initialize standard Hardware interfaces */
//    public void init(HardwareMap ahwMap) {
//        // Save reference to Hardware map
//        hwMap = ahwMap;
//
//        // Define and Initialize Motors
//        leftBack  = hwMap.get(DcMotor.class, "leftMotor");
//        rightBack  = hwMap.get(DcMotor.class, "rightMotor");
//
//        // Reversing left motors
//        rightBack.setDirection(DcMotor.Direction.FORWARD);
//        leftBack.setDirection(DcMotor.Direction.REVERSE);
//
//        // Set all motors to zero power'
//        setMotorPowers(0);
//
//        // Set all motors to run without encoders.
//        // May want to use RUN_USING_ENCODERS if encoders are installed.
//        setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }
//
//    public void setDrivetrainMode(DcMotor.RunMode mode) {
//        leftBack.setMode(mode);
//        rightBack.setMode(mode);
//    }
//
//    /**
//     * Sets all motor powers to given speeds.
//     * @param LBPower
//     * @param RBPower
//     */
//    public void setMotorPowers(double LBPower, double RBPower) {
//        leftBack.setPower(LBPower);
//        rightBack.setPower(RBPower);
//    }
//
//    public void setMotorPowers(double allPower) {
//        setMotorPowers(allPower, allPower);
//    }
//
//    public void moveByTime(double LBPower, double RBPower, ElapsedTime runtime, double time, LinearOpMode opMode, Telemetry telemetry) {
//        setMotorPowers(LBPower, RBPower);
//        runtime.reset();
//        while (opMode.opModeIsActive() && runtime.milliseconds() < time) {
//            telemetry.addData("moveByTime running", "for " + time + " seconds");
//            telemetry.addData("LBPower", LBPower);
//            telemetry.addData("RFPower", RBPower);
//            telemetry.update();
//        }
//        setMotorPowers(0);
//    }
//
//    public void moveByTime(double allPower, ElapsedTime runtime, double time, LinearOpMode opMode, Telemetry telemetry) {
//        moveByTime(allPower, allPower, runtime, time, opMode, telemetry);
//    }

    HardwareMap hwMap = null;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private Servo leftServo;
    private Servo rightServo;

    private ElapsedTime period = new ElapsedTime();
    BNO055IMU imu;
    private double servoPosition;
    private boolean oldServoPosition;

    public void init(HardwareMap ahwMap){

        hwMap = ahwMap;

        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class,"rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        imu = hwMap.get(BNO055IMU.class, "imu");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
//        parameters.loggingEnabled = true;
//        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();


    }


    public void setAllPower(double p) {setMotorPower(p,p,p,p);}

    public void setMotorPower(double lF, double lB, double rF, double rB){
        leftFront.setPower(lF);
        leftBack.setPower(lB);
        rightFront.setPower(rF);
        rightBack.setPower(rB);
    }




//        if (gamepad1.left_bumper){
//            if (intake.getPower() == 0){
//                intake.setPower(-1);
//                intakeState = true;
//            } else {
//                intake.setPower(0);
//                intakeState = false;
//            }
//        } else if (gamepad1.right_bumper){
//            if (intake.getPower() == 0){
//                intake.setPower(1);
//            } else {
//                intake.setPower(0);
//                intakeState = false;
//            }
//        }

}
