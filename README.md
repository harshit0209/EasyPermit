# EasyPermit

#### Tired of writing boilerplate code for getting Runtime Permissions? This is for you

#### Traditional methods for getting Runtime Permissions seems to be lengthy/laborious? This is for you

With the introduction of Android 6.0 Marshmallow, Google has changed the way permissions are handled by the app. Well, everything comes for some reason. And you must have seen that some apps ask for multiple permission that are actually out of their context. So Google Solved this problem with Runtime Permission so that user manually permits the app for specific resources like CAMERA, Contacts, and …

Permission are essential part when we are talking of developing apps or using them. Thus have basic concepts all set with Permission would not get the cake. As traditional approach for Runtime Permission is lengthy and increases complexity of code.

But, as it was the only solution I followed it(Traditional Approach). I built multiple apps with it and found some common problems :

1. Too much of Boilerplate Code
1. Difficulty in managing Permissions
1. Increased Complexity of the Main Class (MainActivity in my case)
1. Last but not the least app crash(es)

This is powerful library with which one can manage runtime permissions 2 lines.
#### 1. Add repository to build.gradle (project level)
```
  allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
```
#### 2. Add dependency to build.gradle (app level)
```
implementation 'com.github.harshit0209:EasyPermit:0.12'
   
```
#### 3. Ask for permission in Manifest
Example:
```
    <uses-permission android:name="android.permission.READ_CONTACTS"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.CAMERA"/>
   
```
#### 4. Add following code to MainActivity or any other Activty
Example:
```
   val grantPermission: EasyPermit = InitPermissions()
        grantPermission.checkAndGetPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
        ))               
   
```
#### or
#### One can use **jugaad** method, that automatically asks for runtime permissions as decleared in Manifest.
```
val grantPermission: EasyPermit = InitPermissions()
grantPermission.jugaadAllPermissionsAutomatically(this)
```

#### Now Build and Run the app.
#### Demo app (link)[https://github.com/harshit0209/EasyPermit_Test]


-----------------------------------------------------------------------------

### MIT License

Copyright (c) 2020 Harshit Chaurasia

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
