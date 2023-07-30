package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "ControllerHub1")

public  class OperatorTest extends  OpMode {

    private  DcMotor left;
    private DcMotor right;

    private DcMotor intake;
    private DcMotor elevator;

    private DcMotor shooter;
    private DcMotor cascade;


    private DistanceSensor distance;
    private Servo front;
    private Servo back;

    private double servoPosition;
    private boolean oldServoPosition;

//    static final double HD_COUNTS_PER_REV = 28;
//    static final double DRIVE_GEAR_REDUCTION = 20.15293;
//    static final double WHEEL_CIRCUMFERENCE_MM = 90 * Math.PI;
//    static final double DRIVE_COUNTS_PER_MM = (HD_COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) + WHEEL_CIRCUMFERENCE_MM;

    @Override

    public void init() {
        left = hardwareMap.get(DcMotor.class, "leftMotor");
        right = hardwareMap.get(DcMotor.class,"rightMotor");
        front = hardwareMap.get(Servo.class, "frontServo");
        back = hardwareMap.get(Servo.class, "backServo");
        intake = hardwareMap.get(DcMotor.class, "intake");
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        cascade = hardwareMap.get(DcMotor.class, "cascade");


        right.setDirection(DcMotorSimple.Direction.REVERSE);


        servoPosition = 0;
        distance = hardwareMap.get(DistanceSensor.class, "distance");
        oldServoPosition = false;
    }

    @Override
    public void loop() {
        boolean servoButton = gamepad2.square;


        if (gamepad1.left_stick_y > 0.1) {
            left.setPower(gamepad1.left_stick_y);

        } else if (gamepad1.left_stick_y < -0.1) {
            left.setPower(gamepad1.left_stick_y);

        } else if (gamepad1.left_stick_y < 0.1|| gamepad1.left_stick_y > -0.1){
            left.setPower(0);

        }

        if (gamepad1.right_stick_y > 0.1){
            right.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y < -0.1){
            right.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y < 0.1|| gamepad1.right_stick_y > -0.1){
            right.setPower(0);
        }

        // Intake
        if (gamepad1.left_bumper){
            intake.setPower(-0.75);
        } else if (gamepad1.right_bumper){
            intake.setPower(0.75);
        } else {
           intake.setPower(0);
        }

        // Elevator
        if (gamepad1.cross){
            elevator.setPower(0.8);
        } else  if (gamepad1.circle){
            elevator.setPower(-0.8);
        } else {
            elevator.setPower(0);
        }


        if(gamepad2.left_trigger > 0.1){
            shooter.setPower(gamepad1.left_trigger);
        } else if (gamepad1.right_trigger > 0.1) {
            shooter.setPower(-gamepad1.right_trigger);
        } else {
            shooter.setPower(0);
        }

        // Gamepad 2
        // Cascade

        if (gamepad2.left_stick_y > 0.1){
            cascade.setPower(gamepad1.left_stick_y);
        } else if (gamepad2.left_stick_y < -0.1){
            cascade.setPower(-gamepad2.left_stick_y);
        } else if (gamepad2.left_stick_y < 0.1|| gamepad2.left_stick_y > -0.1){
            cascade.setPower(0);
        }


        // Kep bong va bo bong
        // Servo ve vi tri 0 <--> kep bong
        if (servoButton && !oldServoPosition) {
            if (servoPosition == 0){
                front.setPosition(0);
                back.setPosition(1);
                servoPosition= 1;
            } else {
                front.setPosition(0.8);

                back.setPosition(0.8);
                servoPosition = 0;
            }
        }


        oldServoPosition = servoButton;


        // Kep bong va bo bong
        // Servo ve vi tri 0 <--> kep bong


        telemetry.update();


    }
}


