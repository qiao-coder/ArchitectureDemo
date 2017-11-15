# ArchitectureDemo
mvp+dagger2（dagger.android）+rxjava2+retrofit2的架构demo，</br>基于谷歌的架构demo（https://github.com/googlesamples/android-architecture） 封装的。

###dagger.android
dagger相关的注释，摘录自谷歌demo。</br>dagger全局单例的例子，在我优化代码时，被我干掉了。迟点再放上来。

###RxJava2+Retrofit2
1.有请求百度人脸识别的接口示例代码。注：人脸保存完毕后，生效时间一般为5s以内(通常一两秒)，之后便可以进行识别等操作。</br>2.有版本更新的示例代码，包括如何使用RxJava2+Retrofit2进行带下载进度监听的代码(暂无正式接口)。</br>3.相关的工具类，以及封装，在代码里也均有注释说明原因。


###单元测试
暂时只有使用robolectric测试网络接口的示例代码。