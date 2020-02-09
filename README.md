[![](https://jitpack.io/v/BaselHorany/RecentActivitiesMenu.svg)](https://jitpack.io/#BaselHorany/RecentActivitiesMenu)

# RecentActivitiesMenu
A menu that display recent visited activity on their paused stated, which also can be clicked to naviagte to it directly. Similar to recent apps menu but this is for in-app activities
This Lib not only nice addtition to make more unique design that may like users\client, but also used to navigate easier and faster between activities, for instance usually in android a main activity -> conversations list activity -> single chat activity, once user go back to main he can not go directly to single chat without revisiting list activity. but with this lib it is possible without losing extras


<p align="center">
  <img src="https://github.com/BaselHorany/RecentActivitiesMenu/blob/master/showcase.png?raw=true" />
</p>


## Setup
1- Add jitpack.io repositories to you project `build.gradle`
```groovy 
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
2- Add it as a dependency to your app `build.gradle`
```groovy
dependencies {
  compile 'com.github.BaselHorany:RecentActivitiesMenu:1.0.0'
}
```

## Usage
This Lib extends android support library DrawerLayout so you are free to deal with the menu as usual 

```xml
<com.basel.RecentActivitiesMenu.ActivitiesMenuDrawer xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:openDrawer="left">

    <!--include your main layout -->
    <include
        layout="@layout/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <com.google.android.material.navigation.NavigationView
        android:background="@drawable/nav_bg"
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:itemIconTint="@color/colorSecondary"
        app:itemTextColor="@color/colorPrimary"
        app:theme="@style/ThemeOverlay.AppCompat.Light"
        app:menu="@menu/navigation_menu"/>
    <!--app:headerLayout="@layout/nav_header"-->

</com.basel.RecentActivitiesMenu.ActivitiesMenuDrawer>
```

```java

/*Extend application class from this Lib. clone it and edite for multidexing*/
    public class AppController extends AMApplication {
       
    }

/*options*/
    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
    drawerLayout.setRecentBelowHeader(false);//show recent menu below drawer header if any.

```

## This lib uses AndroidX

## Author
Basel Horany 
[http://baselhorany.com](http://baselhorany.com)

