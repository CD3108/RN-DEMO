## 1.安装  JDK SDK  ##
## 2.安装 python ##
从官网下载并安装 python 2.7.x（3.x版本不行）
[https://www.python.org/downloads/release/python-2710/](https://www.python.org/downloads/release/python-2710/ "python 官网")

## 3.安装node.js ##
从官网下载 node.js 的官方 5.x 版本或更高版本。

[https://nodejs.org/en/](https://nodejs.org/en/ "nodejs 官网")

建议设置 npm 镜像以加速后面的过程。（可以不用）

npm config set registry https://registry.npm.taobao.org --global

npm config set disturl https://npm.taobao.org/dist --global

## 4.安装 react-native 命令行工具 ##
npm install -g react-native-cli

## 5.创建项目 ##
进入你的工作目录，运行

react-native init MyProject
并耐心等待数（或数十）分钟。

此时若卡在 adb，需要配置 adb 环境变量

adb 环境变量配置：
[https://blog.csdn.net/y201314an/article/details/81022556](https://blog.csdn.net/y201314an/article/details/81022556 "adb 环境变量配置")

## 6.运行packager ##
react-native start

可以用浏览器访问 http://localhost:8081/index.bundle?platform=android 看看是否可以看到打包后的脚本（看到很长的js代码就对了）。第一次访问通常需要十几秒，并且在packager的命令行可以看到形如[====]的进度条。

注意老版本（低于0.49）的地址为 http://localhost:8081/index.android.bundle?platform=android

此步骤为创建热重载服务器。也是为什么安装 python 的理由。摇一摇（热重载）时，会看到服务器更新文件记录。

## 7.安卓运行 ##
保持 packager 开启，另外打开一个命令行窗口，然后在工程目录下运行

react-native run-android

不运行 react-native start 直接运行 react-native run-android 也可以，会自动创建服务器。

## 8.热重载调试  ##

修改 JS 后，摇一摇，会弹窗，点击 reload 即可
Native 修改需要重新跑

## 9.热更新
React Native 的加载启动机制：

RN 会将一系列资源打包成 js bundle 文件，系统加载 js bundle 文件，解析并渲染。所以，RN 热更新的根本原理就是更换 js bundle 文件，并重新加载。

加载流程如下：

![](https://i.imgur.com/VRL3p6T.png)

Native 部分热更新需要 Native 方案

# 常见坑： #


### 红屏出现:
unable to load script from assets index.android.bundle

新建 asset 文件夹,cmd 项目路径,运行

react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res


### 端口占用问题: ###

react-native start --port 9090  

### 摇一摇红屏信息 : 

adb devices  
loaclhost:8081/index.delta?plantform=android&dev=true&minify=false

解决:


adb reverse tcp:8081 tcp:8081 

react-native start



## Demo 地址：
https://github.com/CD3108/RN-DEMO