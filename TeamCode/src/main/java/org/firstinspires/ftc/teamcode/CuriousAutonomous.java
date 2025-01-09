package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Disabled
@Autonomous
public class CuriousAutonomous extends LinearOpMode {
  @Override
  public void runOpMode() throws InterruptedException {

   long COUNTS_PER_REVOLUTION_OF_DRIVING_MOTORS = 525;
   double EncoderPositionsPerInch = 116.363636364;
   double WheelCircumferenceMM = 100 * Math.PI; 
   final int fullExtension = Math.round(REVOLUTIONS_FOR_FULL_EXTENSION * YELLOW_JACKET_223_PPR) - TweakableNumbers.NUMBERS.get("x ");
   long ViperCountsPerRevolution;
   double ViperEncoderPositionsPerInch;
   int upordown = 0;
   DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
   DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
   DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
   DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
   DcMotor viperMotor = hardwareMap.dcMotor.get("viperMotor");
   Servo bucketServo = hardwareMap.servo.get("bucketServo");
   Servo rampServo = hardwareMap.servo.get("rampServo");
   IMU imu = hardwareMap.get(IMU.class, "imu");


   imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingPosition.UP, RevHubOrientationOnRobot.USBFacingPosition.FORWARD)));
   imu.resetYaw();

   frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

   int frontLeftTarget = (int) (-96 * EncoderPositionsPerInch);
   int frontRightTarget = (int) (96 * EncoderPositionsPerInch);
   int backLeftTarget = (int) (96 * EncoderPositionsPerInch);
   int backRightTarget = (int) (-96 * EncoderPositionsPerInch);
   int viperTarget = 0;

   waitForStart();

   while (opModeIsActive()) {
     double yaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

     frontLeftMotor.setTargetPosition(frontLeftTarget);
     frontRightMotor.setTargetPosition(frontRightTarget);
     backLeftMotor.setTargetPosition(backLeftTarget);
     backRightMotor.setTargetPotion(backRightTarget);

     frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

     while (frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy()) {
       // wait for them to get to the position and stop moving
     }

     frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

     frontLeftTarget = (12 * EncoderPositionsPerInch);
     frontRightTarget = (12 * EncoderPositionsPerInch);
     backLeftTarget = (12 * EncoderPositionsPerInch);
     backRightTarget = (12 * EncoderPositionsPerInch);

     frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

     while (frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy()) {
       // wait for them to get to the position and stop moving
     }

     frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

     while (Math.abs(yaw - -45) > 1) {
       frontRightMotor.setPower(0.5);
       backRightMotor.setPower(0.5);
       frontLeftMotor.setPower(-0.5);
       backLeftMotor.setPower(-0.5);
     }


     viperMotor.setTargetPosition(viperTarget = fullExtension); 
     viperMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION); 
     uporDown = 0; 
     whlie (uporDown == 0) {
       if (motor.getCurrentPosition() - 20 > targetPosition && upordown == 0) {
          this.motor.setPower(0.0);
          uporDown = 2; 
       }
     }
     
     bucketServo.setPosition(123);

     bucketServo.setPosition(0);

     viperMotor.setTargetPosition(viperTarget = 0); 
     viperMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     uporDown = 1;
     while (upordown == 1){
       if (motor.getCurrentPosition() - 75 <= targetPosition && upordown == 1) {
         this.motor.setPower(0.0);
         upordown = 2;
       } else {
           this.motor.setPower(-1);
       }
     
     while (Yaw > math.abs(0)) {
       frontRightMotor.setPower(-0.5);
       backRightMotor.setPower(-0.5);
       frontLeftMotor.setPower(0.5);
       backLeftMotor.setPower(0.5);
     }
     frontLeftTarget = (-12 * EncoderPositionsPerInch);
     frontRightTarget = (-12 * EncoderPositionsPerInch);
     backLeftTarget = -(12 * EncoderPositionsPerInch);
     backRightTarget = (-12 * EncoderPositionsPerInch);

     frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

     while (frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy()) {
       // wait for them to get to the position and stop moving
     }

     frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

     int frontLeftTarget = (121 * EncoderPositionsPerInch);
     int frontRightTarget = (-121 * EncoderPositionsPerInch);
     int backLeftTarget = (-121 * EncoderPositionsPerInch);
     int backRightTarget = (121 * EncoderPositionsPerInch);
//// // Going for another sample
////       frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////       frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////       backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////       backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
////       while (frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy()) {
////       // wait for them to get to the position and stop moving
////       }
//
////       frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////       frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////       backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////       backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
////       int frontLeftTarget = (24 * EncoderPositionsPerInch);
////       int frontRightTarget = (-24 * EncoderPositionsPerInch);
////       int backLeftTarget = (-24 * EncoderPositionsPerInch);
////       int backRightTarget = (24 * EncoderPositionsPerInch);
//
////       frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////       frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////       backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////       backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
////       while (frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy()) {
////       // wait for them to get to the position and stop moving
////       }
//
//
//
//    }
  }
}
