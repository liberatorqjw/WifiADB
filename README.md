###  操作说明
* 本项目主要实现的利用wifi 连接 adb。但是有人会问这个不需要程序吧，不是android本身就有的特性吗？
写程序的主要原因是因为wifi连接之前需要执行几条命令，但是每次手动真的很烦阿，写个程序吧。
### 项目要求
* 手机 root
### 运行说明
* 拉取代码，导入到android studio中
* 安装到手机上
* 点击按钮 （界面很丑，重在功能了）
* 会弹出ip地址
* 电脑上输入如下命令连接
```
 adb connect <ip address>
```
* 可以和有线连接一样进行操作了。
