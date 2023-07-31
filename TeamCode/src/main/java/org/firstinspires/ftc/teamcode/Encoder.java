package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Encoder (Java)")
public class Encoder extends LinearOpMode {

    private DcMotor RightDrive;
    private DcMotor LeftDrive;
//    private DcMotor Arm;
//    private DcMotor Intake;

    //Convert from the counts per revolution of the encoder to counts per inch
    static final double HD_COUNTS_PER_REV = 28;
    static final double DRIVE_GEAR_REDUCTION = 20.15293;
    static final double WHEEL_CIRCUMFERENCE_MM = 90 * Math.PI;
    static final double DRIVE_COUNTS_PER_MM = (HD_COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE_MM;
    static final double DRIVE_COUNTS_PER_IN = DRIVE_COUNTS_PER_MM * 25.4;

    //Create elapsed time variable and an instance of elapsed time

    // Drive function with 3 parameters


    @Override
    public void runOpMode() {

        LeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftDrive = hardwareMap.get(DcMotor.class, "leftMotor");
        RightDrive = hardwareMap.get(DcMotor.class, "rightMotor");


        LeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        double rotationsNeeded = 18/DRIVE_COUNTS_PER_IN;
        int encoderDrivingTarget = (int)(rotationsNeeded*28);

        RightDrive.setTargetPosition(encoderDrivingTarget);
        LeftDrive.setTargetPosition(encoderDrivingTarget);

        RightDrive.setPower(0.5);
        LeftDrive.setPower(0.5);
        
        RightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (RightDrive.isBusy() || LeftDrive.isBusy()){
            telemetry.addData("Path", "Complete");
        }

        RightDrive.setPower(0);
        LeftDrive.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();

    }
}