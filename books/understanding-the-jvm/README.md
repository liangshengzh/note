<<深入理解Java虚拟机>>笔记


Java虚拟机在运行Java程序的将所管理的内存分为以下几个数据区域
- 方法区
- 堆
- 虚拟机栈
- 本地方法栈
- 程序计数器

其中方法区和堆栈是有所有线程共享，而虚拟机栈，本地方法栈，程序计数器是线程私有的数据去

![JVM runtime data area](image/jvm-runtime-data-area.jpg)
