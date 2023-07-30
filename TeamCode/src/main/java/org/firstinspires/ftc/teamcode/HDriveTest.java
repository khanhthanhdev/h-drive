package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp (name="HDrive")
public class HDriveTest extends OpMode {

    private DcMotor left;
    private DcMotor right;
    private DcMotor center;

    @Override
    public void init(){
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class,"right");
        center = hardwareMap.get(DcMotor.class, "center");

        right.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y > 0.1){
            left.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < -0.1) {
            left.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y <= 0.1 || gamepad1.left_stick_y >= -0.1){
            left.setPower(0);
        }

        if (gamepad1.right_stick_y > 0.1){
            right.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y < -0.1){
            right.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y <= 0.1 || gamepad1.right_stick_y >= -0.1){
            right.setPower(0);
        }

        if (gamepad1.right_stick_x > 0.1){
            right.setPower(gamepad1.right_stick_x);
        } else if (gamepad1.right_stick_x < -0.1){
            right.setPower(gamepad1.right_stick_x);
        } else if (gamepad1.right_stick_x <= 0.1 || gamepad1.right_stick_x >= -0.1){
            right.setPower(0);
        }
    }
}
