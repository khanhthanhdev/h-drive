package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp (name="HDriveTest")
public class HDriveTest extends OpMode {

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
        left = hardwareMap.get(DcMotor.class, "leftMotor");
        right = hardwareMap.get(DcMotor.class,"rightMotor");
        center = hardwareMap.get(DcMotor.class, "center");

        right.setDirection(DcMotorSimple.Direction.REVERSE);


    }



    @Override
    public void loop() {


        if (gamepad1.left_stick_y > 0.05){
            left.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < -0.05){
            left.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y <= 0.05 || gamepad1.left_stick_y >= -0.05){
            left.setPower(0);
        }

        if (gamepad1.right_stick_y > 0.05){
            right.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y < -0.05){
            right.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y <= 0.1|| gamepad1.right_stick_y >= -0.1){
            right.setPower(0);
        }

        if (gamepad1.right_trigger > 0.05){
            center.setPower(-gamepad1.right_trigger);
        } else if (gamepad1.left_trigger > 0.05){
            center.setPower(gamepad1.left_trigger);
        } else if (gamepad1.right_trigger <= 0.05 || gamepad1.left_trigger >= -0.05){
            center.setPower(0);
        } else if (gamepad1.right_trigger != 0 || gamepad1.left_trigger != -0) {
            center.setPower(0);
        }


    }
}
