package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class HardwarePushbot
{
    /* Public OpMode members. */
    public DcMotor  leftBack = null;
    public DcMotor  rightBack = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwarePushbot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftBack  = hwMap.get(DcMotor.class, "leftMotor");
        rightBack  = hwMap.get(DcMotor.class, "rightMotor");

        // Reversing left motors
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power'
        setMotorPowers(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setDrivetrainMode(DcMotor.RunMode mode) {
        leftBack.setMode(mode);
        rightBack.setMode(mode);
    }

    /**
     * Sets all motor powers to given speeds.
     * @param LBPower
     * @param RBPower
     */
    public void setMotorPowers(double LBPower, double RBPower) {
        leftBack.setPower(LBPower);
        rightBack.setPower(RBPower);
    }

    public void setMotorPowers(double allPower) {
        setMotorPowers(allPower, allPower);
    }

    public void moveByTime(double LBPower, double RBPower, ElapsedTime runtime, double time, LinearOpMode opMode, Telemetry telemetry) {
        setMotorPowers(LBPower, RBPower);
        runtime.reset();
        while (opMode.opModeIsActive() && runtime.milliseconds() < time) {
            telemetry.addData("moveByTime running", "for " + time + " seconds");
            telemetry.addData("LBPower", LBPower);
            telemetry.addData("RFPower", RBPower);
            telemetry.update();
        }
        setMotorPowers(0);
    }

    public void moveByTime(double allPower, ElapsedTime runtime, double time, LinearOpMode opMode, Telemetry telemetry) {
        moveByTime(allPower, allPower, runtime, time, opMode, telemetry);
    }
}
