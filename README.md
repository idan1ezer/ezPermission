
# ezPermission 🔒🔒
[![](https://jitpack.io/v/idan1ezer/ezPermission.svg)](https://jitpack.io/#idan1ezer/ezPermission)

🔑🔒 This is a Java library for Android development that provides several utility functions and classes for common tasks such as handling permissions, working with the camera, playing sounds, and managing logs. 🔒🔑

## Description

The library we created is a Java library for Android development that provides a PermissionManager class to simplify the process of requesting runtime permissions in Android.
The library allows developers to request multiple permissions at once and handles the user's response to the request, 
calling appropriate methods to notify the developer whether the permissions were granted or denied.

## FAQ

The PermissionManager class includes the following functions:

requestPermissions(String[] permissions): This method is used to request the specified permissions from the user. It takes an array of permission strings as an argument.

onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults): This method should be called in the onRequestPermissionsResult method of the calling activity or fragment to handle the user's response to the permission request.

The PermissionManager class also includes an interface called PermissionResultListener that defines two methods:

onPermissionGranted(): This method is called when all the requested permissions have been granted.

onPermissionDenied(List<String> deniedPermissions): This method is called when any of the requested permissions have been denied. It takes a list of denied permissions as an argument.

In addition to the PermissionManager class, we also implemented a takePhoto method in our example that uses the device's camera to take a photo and saves it to the device's external storage.


<p align="center">
  <img src="https://user-images.githubusercontent.com/59417326/236693016-1b33aaa3-e06f-4a8f-9913-a81998aa5e21.png">
</p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/59417326/236693018-e35f002f-ab4f-43e5-8529-2f27976985a0.png">
</p>

## How to Use

1. Add it in your root build.gradle at the end of repositories:
   ```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
   ```
2. Add the dependency:
   ```
	dependencies {
	        implementation 'com.github.idan1ezer:ezPermission:V1.0'
	}
   ```
3. In your project, you can choose multiple ways to grant permissions 🔒. 
4. Go coding! 🤓.


