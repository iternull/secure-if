# Secure I/F

Secure I/F This application is used to control the **Charge Connection** and **Data Connection** of the Android USB interface.

This application can prevent unauthorized devices from connecting to your phone via USB to implant malicious viruses, 
can protect the charging safety in public places.

This application requires **Root**.

[中文说明](README-zh.md)

## How is work

Control the USB function switch by modifying the kernel parameter parameters.

#### USB Charge

```bash
su -c 'echo 0 > /sys/class/power_supply/battery/charging_enabled'    // Disable USB Charge
su -c 'echo 1 > /sys/class/power_supply/battery/charging_enabled'    // Enable USB Charge
```

> Not all Android phones are this path, so this feature only supports some phones.

#### USB Data Connections

```bash
su -c 'echo 0 > /sys/devices/virtual/android_usb/android0/enable'    // Disable USB Connections
su -c 'echo 1 > /sys/devices/virtual/android_usb/android0/enable'    // Enable USB Connections
```

## Develop

This project is developed using [Android Studio](https://developer.android.com/studio/index.html).

Also provided a [Shell Script](susb), Used for command line interface.

<!--
| Stage    | Function                                           | Complete |
|:---------|:---------------------------------------------------|:---------|
| Stage 1  | USB Charge Switch                                  | Yes      |
|          | USB Connections Switch                             | Yes      |
| Stage 2  | Simulate a fake user file directory                | No       |
|          | Close the original USB connection notification bar | No       |
|          | Mimic fake USB connection notification bar         | No       |
| Stage 3  | Turn off ADB debug                                 | No       |
|          | Mimic fake ADB debug notification                  | No       |
| Stage 4  | Add the hidden app icon function                   | No       |
|          | Simulation app installation interface              | No       |
-->

## Download

Download the [latest release](https://github.com/iternull/secure-if/releases).

## License

This is a private project.

> ℉ūｃｋ  ｙōūｒ  ōｐéń  $ōūｒｃé  ｌīｃéń$é

----------

> [卍](https://en.wikipedia.org/wiki/Swastika)
