package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp (name="SixWheel")
public class SixWheelTest extends OpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private Servo leftServo;
    private Servo rightServo;

    BNO055IMU imu;
    private double servoPosition;
    private boolean oldServoPosition;


    public void goStop() {
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.getPower();
    }


    @Override
    public void init(){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BN0055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
    }



    @Override
    public void loop() {


        boolean intakeState = false;
        boolean reverseState = false;
        boolean servoButton = gamepad2.square;

        if (gamepad1.left_stick_y > 0.05){
            leftFront.setPower(gamepad1.left_stick_y);
            leftBack.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < -0.05){
            leftFront.setPower(gamepad1.left_stick_y);
            leftBack.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y <= 0.05 || gamepad1.left_stick_y >= -0.05){
            leftFront.setPower(0);
            leftBack.setPower(0);
        }

        if (gamepad1.right_stick_y > 0.05){
            rightFront.setPower(gamepad1.right_stick_y);
            rightBack.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y < -0.05){
            rightFront.setPower(gamepad1.right_stick_y);
            rightBack.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y <= 0.05|| gamepad1.right_stick_y >= -0.05){
            rightFront.setPower(0);
            rightBack.setPower(0);
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


        if (servoButton && !oldServoPosition) {
            if (servoPosition == 0){
                leftServo.setPosition(0);
                rightServo.setPosition(1);
                servoPosition= 1;
            } else {
                leftServo.setPosition(0.8);
                rightServo.setPosition(0.8);
                servoPosition = 0;
            }
        }

        oldServoPosition = servoButton;


        if (gamepad1.touchpad){
            rightFront.setDirection(DcMotor.Direction.REVERSE);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
            leftFront.setDirection(DcMotor.Direction.FORWARD);
            leftBack.setDirection(DcMotor.Direction.FORWARD);
        }

        if (gamepad1.touchpad && reverseState){
            rightFront.setDirection(DcMotor.Direction.REVERSE);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
            leftFront.setDirection(DcMotor.Direction.FORWARD);
            leftBack.setDirection(DcMotor.Direction.FORWARD);

            reverseState = !reverseState;
        } else if (gamepad1.touchpad && reverseState == !reverseState){
            rightFront.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setDirection(DcMotor.Direction.FORWARD);
            leftFront.setDirection(DcMotor.Direction.REVERSE);
            leftBack.setDirection(DcMotor.Direction.REVERSE);
        }



    }
}