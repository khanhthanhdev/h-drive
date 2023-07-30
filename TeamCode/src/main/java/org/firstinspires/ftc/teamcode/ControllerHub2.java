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


@TeleOp(name = "ControllerHub2")

public  class ControllerHub2 extends  OpMode {


    private DcMotor shooter;
    private DcMotor cascadeRight;
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
        front = hardwareMap.get(Servo.class, "frontServo");
        back = hardwareMap.get(Servo.class, "backServo");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        cascadeRight = hardwareMap.get(DcMotor.class, "cascadeRight");

        servoPosition = 0;
        oldServoPosition = false;
    }

    @Override
    public void loop() {
        boolean servoButton = gamepad1.square;

        // Shooter
        if(gamepad1.left_trigger > 0.1){
            shooter.setPower(gamepad1.left_trigger);
        } else if (gamepad1.right_trigger > 0.1) {
            shooter.setPower(-gamepad1.right_trigger);
        } else {
            shooter.setPower(0);
        }

        // Gamepad 2
        // Cascade

        if (gamepad1.left_stick_y > 0.1){
            cascadeRight.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < -0.1){
            cascadeRight.setPower(-gamepad2.left_stick_y);
        } else if (gamepad1.left_stick_y < 0.1|| gamepad1.left_stick_y > -0.1){
            cascadeRight.setPower(0);
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

    }
}


