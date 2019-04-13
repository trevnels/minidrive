# minidrive
Software for controlling arduino with USB controllers. For use with [miniduino](https://github.com/20nelson/miniduino).

## Installation
Download and extract the lastest release zip from the releases page.

## Usage
Execute `run.sh` or `run.bat` from the terminal.
First, select a controller from the list by entering its number and pressing enter. 
Then select the serial port of your Arduino in a similar fashion (on a Mac, this starts with `cu`.)
You are now in the command line, where you can enter the following commands.

## Commands

| Command | Description | 
|---------|-------------|
| `e` | Enables the robot, starts sending controller data |
| `d` | Disables the robot, stops sending controller data |
| `q` | Disables the robot and quits the application      |

## Troubleshooting
On Linux systems, make sure you are a member of the groups associated with serial port access for your distribution. Some common ones are `dialout` and `tty`.