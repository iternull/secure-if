# Secure I/F

Secure I/F 是一个用于控制 Android 手机 USB 接口的**电源连接**与**数据连接**关闭与打开的一款应用。

可以防止未授权的设备通过 USB 连接到你的手机进行恶意操作或者植入病毒，可以保护你在公共场所的充电安全。

本程序需要 **Root** 后才能正常使用。

## 原理

原理就是通过修改内核参数参数控制 USB 功能的开关。

#### 控制 USB 充电连接

```bash
su -c 'echo 0 > /sys/class/power_supply/battery/charging_enabled'    // 关闭 USB 充电功能
su -c 'echo 1 > /sys/class/power_supply/battery/charging_enabled'    // 打开 USB 充电功能
```

注: 并非所有 Android 手机都是这个路径，所以这个功能只支持部分手机。

#### 控制 USB 数据连接

```bash
su -c 'echo 0 > /sys/devices/virtual/android_usb/android0/enable'    // 关闭 USB 数据通信功能
su -c 'echo 1 > /sys/devices/virtual/android_usb/android0/enable'    // 打开 USB 数据通信功能
```

## 开发

本项目使用 [Android Studio](https://developer.android.com/studio/index.html) 开发，目前还存在不少的 bug 。
特别是控制电源连接的功能只支持部分安卓手机，以及会受其他程序的影响导致已经关闭的选项被其他程序调用时打开。

另外还提供了一个原始的 [Shell 脚本](susb)，供命令行界面下使用。

| 阶段     | 功能                                 | 实现 |
|:---------|:-------------------------------------|:-----|
| 第一阶段 | USB 充电功能开关                     | Yes  |
|          | USB 数据传输功能开关                 | Yes  |
| 第二阶段 | 模拟一个假的的用户文件               | No   |
|          | 关闭原始 USB 连接通知栏              | No   |
|          | 模拟假的 USB 连接通知栏              | No   |
| 第三阶段 | 关闭 ADB 调试功能                    | No   |
|          | 模拟假的 ABD 调试通知框              | No   |
| 第四阶段 | 添加隐藏应用图标功能                 | No   |
|          | 模拟应用安装界面，弹出安装错误的提示 | No   |

## 下载

Download the [latest release](https://github.com/iternull/secure-if/releases).

## 许可

> 弆珎媽白勺閞源滸坷

## 信仰

<(ﾟ´(｡｡)`ﾟ)>

本项目代码以使用猪头猪血烧香做法，以保佑无 bug 出现。
