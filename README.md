# BreatheView
呼吸灯闪烁效果的自定义View


# 如何使用


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}Copy
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.jianfeng318:BreatheView:0.1.2'
	}

**效果**

![](https://github.com/jianfeng318/BreatheView/blob/master/screenshots/demo.gif)



#### 在使用时候直接findViewById,拿到view之后需要初始化

 ```
 brv.setInterval(2000) //设置闪烁间隔时间
                .setCoreRadius(5f)//设置中心圆半径
                .setDiffusMaxWidth(300f)//设置闪烁圆的最大半径
                .setDiffusColor(Color.parseColor("#0cf465"))//设置闪烁圆的颜色
                .setCoreColor(Color.parseColor("#f40c3a"))//设置中心圆的颜色
                .onStart();
 ```

##### 欢迎fork,欢迎star,有问题可以加QQ:262607095交流

[我的博客](http://www.jianshu.com/u/0b440912217a)
