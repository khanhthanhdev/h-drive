package org.firstinspires.ftc.teamcode;
// import lines were omitted. OnBotJava will add them automatically.


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Autonomous for Ticks")
public class EncoderExample extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    // P = 5.85
    // l = 0.585
    // D = 0
    // F = 58.5



    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        // Reset the encoder during initialization


        // While the Op Mode is running, show the motor's status via telemetry
        waitForStart();
        telemetry.addData("Status", "Staet");
        telemetry.update();

        // 1 rotation = 28
        this.move(3.53,3.53,.5); //move forward
        this.move(0,3.53,.5); //turn left
        this.move(3.53,3.53,.5); //move forward
        this.move(3.53,0,.5); //turn right
        this.move(-3.53,-3.53,.5); //move forward

    }

    void move(double leftRotations, double rightRotations, double motorPower){
        double gearingRatio = 1;
        double ticksPerRevolution = 2240;
        double leftTicks;
        double rightTicks;

        leftTicks = (leftRotations/gearingRatio)*ticksPerRevolution;
        rightTicks = (rightRotations/gearingRatio)*ticksPerRevolution;

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition((int) leftTicks);
        leftBack.setTargetPosition((int) leftTicks);
        rightFront.setTargetPosition((int) rightTicks);
        rightBack.setTargetPosition((int) rightTicks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        leftFront.setPower(motorPower);
        leftBack.setPower(motorPower);
        rightFront.setPower(motorPower);
        rightBack.setPower(motorPower);

        while(leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy() && opModeIsActive())
        {
            //do nothing, the motor is simply reaching its end point;
        }

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

    }
}
