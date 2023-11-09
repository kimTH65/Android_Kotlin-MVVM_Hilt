# Android(Kotlin)-MVVM_Hilt

 - MVVM Pattern,Observer Pattern사용<br>
 
 - Room(로컬 데이터베이스), LiveData, ViewModel, Observer메소드를 이용하여 실시간으로 UI변경<br>
 
 - Recyclerview, dataBinding 등등 사용<br>
 
 - DI(Dependency injection)으로 Hilt사용
 
 - <a href ="https://www.kobis.or.kr/kobisopenapi/homepg/main/main.do">영화 진흥 위원회 API</a>를 사용
 
# Hilt란

 - Dagger를 기반으로 만든 Dependency Injection 라이브러리<br>
 
 - 모든 Android 클래스에 컨테이너를 제공하고 수명 주기를 자동으로 관리함<br>
 
 - @HiltAndroidApp : 해당 애플리케이션에 Hilt 코드를 자동으로 생성할 수 있게함

 - @AndroidEntryPoint : Hilt로 의존성 주입을 사용할 컨테이너(컴포넌트)를 만듬 

 - @Inject : public 접근자의 경우 Inject 어노테이션으로 의존성 주입 가능

 - @Module : private 접근자의 경우 Module,IntallIn로 의존성 주입

 - @IntallIn : 어느 안드로이드 클래스(activity, fragemnt etc)를 사용할건지 설정

 - @Binds : abstract class(추상 클래스)에서 사용, 리턴값(구현체)를 하나만 가질 수 있음, static이 아님, 구현 필요 없음

 - @Provides : object에서 사용, 여러 파라미터를 가질 수 있음, 로직을 구현해야함, 사용 시 매번 의존성 호출

<br>
<hr>

<h3>1. Manifest</h3>

<h5>Manifest : Android 앱에 대한 중요한 메타 데이터가 포함된 XML</h5>

<div align="center">
 <h6>
  <a href="app/src/main/AndroidManifest.xml">
   app/src/main/AndroidManifest.xml
  </a>
 </h6>
</div>

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.memo">
    <uses-permission android:name="android.permission.INTERNET"/>
    <application
        android:name=".di.App"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar">
        <activity
            android:name=".ui.MainActivity"

            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

#

<h3>2. Application</h3>

<h5>ApplicationClass : 어플리케이션내의 모든 컴포넌트에서 접근 가능</h5>

<div align="center">
 <h6>
  <a href="app/src/main/java/com/example/memo/di/App.kt">
   app/src/main/java/com/example/memo/di/App.kt
  </a>
 </div>

```
package com.example.memo.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application()
```

#

<h3>3. DI Module</h3>

<h5>NetworkModule → DataSourceModule → RepositoryModule → UseCaseModule </h5>

<div align="center">
 <h6>
  <a href="">
  </a>
 </div>

```
```
