# UnscientificLiveData

大三上基于开源和群智的软件工程实践作业。扩展Android Jetpack库中的LiveData功能。

[![](https://jitpack.io/v/UnscientificJsZhai/UnscientificLiveData.svg)](https://jitpack.io/#UnscientificJsZhai/UnscientificLiveData)

## 特点

[LiveData](https://developer.android.google.cn/topic/libraries/architecture/livedata?hl=zh_cn)是Jetpack库中一个组件。

> LiveData 是一种可观察的数据存储器类。与常规的可观察类不同，LiveData 具有生命周期感知能力，意指它遵循其他应用组件（如 Activity、Fragment 或 Service）的生命周期。这种感知能力可确保 LiveData 仅更新处于活跃生命周期状态的应用组件观察者。

一般的，LiveData存储的数据的**引用**发生变更时才会推送给观察者。但是有的时候我们可能要观察的是一个集合类，最常见的情况比如说是观察一个列表。
这种情况下我们一般需要重新调用setValue或者postValue方法重新传值。就像这样：

Java:
```java
    list.add(element);
    viewModel.liveData.setValue(list);
```

Kotlin:
```kotlin
    list.add(element)
    viewModel.liveData.value = list
```

而本项目则可以让你只需要对保存集合类的LiveData调用集合类的方法，就可以将内部数据变更推送到观察者。就像这样：

Java:
```java
    list.add(element);
```

Kotlin:
```kotlin
    list.add(element)
```

## 使用方法

将本项目依赖引入你的项目，只需要在模块级gradle脚本中添加依赖

```groovy
allprojects {
    repositories {
        //其它仓库...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    //其它依赖...
    implementation 'com.github.UnscientificJsZhai:UnscientificCourseParser:Tag'
}
```

需要把“Tag”改为当前版本号。当前版本：

[![](https://jitpack.io/v/UnscientificJsZhai/UnscientificLiveData.svg)](https://jitpack.io/#UnscientificJsZhai/UnscientificLiveData)
