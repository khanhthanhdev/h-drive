package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class TurnPIDController {

    private double targetAngle;
    private double kP,kI,kD;
    private double acculumateError = 0;
    private ElapsedTime timer = new ElapsedTime();

    private  double lastError = 0;
    private double lastTime = 0;
    public TurnPIDController(double target, double p, double i, double d){
        targetAngle = target;
        kP = p;
        kI = i;
        kD = d;
    }


    public double update(double currentAngle){
        // P
        double error = targetAngle - currentAngle;
        error %= 360;
        error += 360;
        error %= 360;

        if (error > 180){
            error -= 360;
        } else if (error < -180) {
            error += 360;
        }
        // I
        acculumateError += error;

        if (Math.abs(error) < 1){
            acculumateError = 0;
        }
        acculumateError = Math.abs(acculumateError) * Math.signum(error);

        // D
        double slope = 0;
        if (lastTime > 0){
            slope = (error - lastError) / (timer.milliseconds() - lastTime);
        }
        lastTime = timer.milliseconds();
        lastError = error;


        // Power Claculate
        double motorPower = 0.1 * Math.signum(error) + 0.9 * Math.tanh(
                kP * error + kI * acculumateError + kD * slope
        );
        return  motorPower;
    }

}
