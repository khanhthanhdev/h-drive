package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp (name="HDriveCircle")
public class HDriveCircle extends OpMode {

    private DcMotor left;
    private DcMotor right;
    private DcMotor center;


    public void goStop() {
        left.setPower(0);
        right.setPower(0);
        center.setPower(0);
    }


    @Override
    public void init(){
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class,"right");
        center = hardwareMap.get(DcMotor.class, "center");

        right.setDirection(DcMotorSimple.Direction.REVERSE);


    }



    @Override
    public void loop() {

        double vertical_right  =  gamepad1.right_stick_x;
        double horizontal_right = gamepad1.right_stick_y;
        double pivot = gamepad1.left_stick_x;

        double circle = (vertical_right + horizontal_right);
        if ( circle >= 1) {
            circle = circle /2;
        }

        if (circle > 0.05){
            left.setPower(circle);
            right.setPower(circle);
            center.setPower(0.5);
        } else if (circle < -0.05) {
            left.setPower(circle);
            right.setPower(circle);
            center.setPower(-0.5);
        } else if (vertical_right <= 0.05 || horizontal_right >= -0.05){
            goStop();
        }

        if (gamepad1.left_stick_y > 0.05){
            right.setPower(gamepad1.left_stick_y);
            left.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.right_stick_y < -0.05){
            right.setPower(gamepad1.left_stick_y);
            left.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y <= 0.05 || gamepad1.left_stick_y >= -0.05){
            goStop();
        }

        if (pivot > 0.05){
            center.setPower(pivot);
        } else if (pivot < - 0.05){
            center.setPower(pivot);
        }

        if ((gamepad1.left_stick_y > 0.05 || gamepad1.right_stick_y > 0.05) && (gamepad1.right_stick_y < 0.1 && gamepad1.left_stick_y < 0.1)){
            right.setPower(gamepad1.right_stick_y);
            left.setPower(gamepad1.left_stick_y);
        } else if ((gamepad1.left_stick_y < -0.05 || gamepad1.right_stick_y < - 0.05) && (gamepad1.right_stick_y >- 0.1 && gamepad1.left_stick_y >- 0.1)){
            right.setPower(gamepad1.right_stick_y);
            left.setPower(gamepad1.left_stick_y);
        }


    }
}
