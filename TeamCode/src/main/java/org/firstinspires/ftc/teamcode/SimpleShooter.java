package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp (name="Simple Shooter")
public class SimpleShooter extends OpMode {

    private DcMotor shooter;




    @Override
    public void init(){
        shooter = hardwareMap.get(DcMotor.class, "leftMotor");

    }



    @Override
    public void loop() {


        boolean intakeState = false;

        double shooterPower = 0.5;

        if (gamepad1.dpad_up){
            shooterPower = shooterPower + 0.05;
        } else if (gamepad1.dpad_down){
            shooterPower = shooterPower -0.05;
        }

        if (gamepad1.left_bumper){
            if (shooter.getPower() == 0){
                shooter.setPower(-shooterPower);
                intakeState = true;
            } else {
                shooter.setPower(0);
                intakeState = false;
            }
        } else if (gamepad1.right_bumper){
            if (shooter.getPower() == 0){
                shooter.setPower(shooterPower);
            } else {
                shooter.setPower(0);
                intakeState = false;
            }
        }



    }
}