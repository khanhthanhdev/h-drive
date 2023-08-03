package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;


public class HardwarePushbot
{


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
        parameters.loggingEnabled = false;

        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);


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
