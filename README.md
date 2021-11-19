[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![Android-Studio](https://img.shields.io/badge/Android%20Studio-4.1+-orange.svg?style=flat)](https://developer.android.com/studio/)
![Language](https://img.shields.io/badge/language-Kotlin-orange.svg)

<h1 align="center"> Android Custom Fragment Manager </h1>

<p align="center">
It is an architectural infrastructure that establishes a relationship between activity and fragment in android, and also provides transitions and management between fragments.
</p>

<ul>
  <li> Existing fragment initialization according to fragment type </li>
  <li> Switching between fragments by command type </li>
  <li> Base class that runs and organizes the whole process: <b>MyFragmentManager.kt</b> </li>
  <li> Custom backstack management </li>
  <li> Interface for fragment interaction </li>
</ul>

## Introduction
<p align="center">
First of all, view binding, which is recommended by Google, was used in the project. <br>
</p>

In the `build.gradle` file, it is stated that we will use view binding.
```gradle
buildFeatures {
    viewBinding true
}
```

Binding is done in Activity and Fragment files
```kotlin
private lateinit var binding: ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
}
```
## Usage
<p align="center">
The application starts with MainActivity. It is used to hold frame layout fragments in activity_main.xml. <br>
All fragments must implement the MyFragment class. <br>
Types of all fragments are specified with enum class. <br>
OnFragmentInteractionListener interface is used to switch between fragments.
</p>
















