# RobotController1

An experimental Android controller for robot.

This is designed to be a generic controller for a range of robotic devices each of which have different set of settings to control them.  Some settings may be fairly static (e.g. to toggle features on/off), other settings may be very dynamic (e.g. direction of travel, based on magnetic orientation of device).

The idea is for a robot to communicate with controller indicating which control values it is interested in and some meta data about each.  Then the controller app builds a custom UI for those control values.  When the user adjusts those values, they are notified back to the robot.  Some values may have custom UI controls, e.g. magnetic orientation may use a custom compass which responds to magnetometer in device.  

