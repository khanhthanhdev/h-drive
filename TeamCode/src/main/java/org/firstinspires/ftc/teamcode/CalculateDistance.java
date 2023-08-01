package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Encoder-based move in square with both turn and sidestep movement
 */

@Autonomous(name="CalculateDistance")

public class CalculateDistance extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 28 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.54 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.setDrivetrainMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.setDrivetrainMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // drive in square with sidestep
        encoderDrive(DRIVE_SPEED, 2, 2);
        encoderDrive(DRIVE_SPEED,2, -2);
        encoderDrive(DRIVE_SPEED, -2, -2);
        encoderDrive(DRIVE_SPEED,-2, 2);

        for (int i = 0; i < 2; i++) {
            encoderDrive(DRIVE_SPEED, 2, 2);
            encoderDrive(DRIVE_SPEED, -2, 2); // TODO: CHANGE "10" TO ACTUALLY MOVE 90 DEGREES
        }
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftBack, double rightBack) {
        int newLBTarget;
        int newRBTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLBTarget = robot.leftBack.getCurrentPosition() + (int)(leftBack * COUNTS_PER_INCH);
            newRBTarget = robot.rightBack.getCurrentPosition() + (int)(rightBack * COUNTS_PER_INCH);

            robot.leftBack.setTargetPosition(newLBTarget);
            robot.rightBack.setTargetPosition(newRBTarget);

            // Turn On RUN_TO_POSITION
            robot.setDrivetrainMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            robot.leftBack.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));



            while (opModeIsActive() &&
                    (robot.leftBack.isBusy() && robot.rightBack.isBusy()
                            && robot.leftBack.isBusy() && robot.rightBack.isBusy())) {
            }

            // Stop all motion;
            robot.setMotorPowers(0);

            // Turn off RUN_TO_POSITION
            robot.setDrivetrainMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
}