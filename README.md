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

<p align="center">
  <img src='https://github.com/ayhanunal/AndroidCustomFragmentManager/blob/main/images/app_init.gif' width=300 heihgt=300> 
</p>

When the application is first opened, it starts with MainActivity. The first fragment is displayed by calling the initFragment method under OnCreate.
The initFragment method sets the current fragment using the myFragmentManager base class.

```kotlin
private lateinit var binding: ActivityMainBinding

private val myFragmentManager = MyFragmentManager(this, supportFragmentManager)

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    initFragment()
}

private fun initFragment(){
    myFragmentManager.setCurrentFragmentType(MyFragmentType.HOME_FRAGMENT, null, false)
}
```

The fragment manager determines the current fragment with the setCurrentFragmentType method.

```kotlin
fun setCurrentFragmentType(fragmentType: MyFragmentType, arguments: Bundle?, addToBackStack: Boolean) {

  if (currentFragment?.fragmentType === fragmentType) return

  if (!addToBackStack) clearBackStack()

  currentFragment = when (fragmentType) {
      MyFragmentType.MY_FRAGMENT_TYPE_A -> FragmentA()
      MyFragmentType.MY_FRAGMENT_TYPE_B -> FragmentB()
      MyFragmentType.HOME_FRAGMENT -> HomeFragment()
  }

  currentFragment?.arguments = arguments

  if (addToBackStack){
      myFragmentStack.add(currentFragment)
  }else{
      baseFragment = currentFragment
  }

  val fragmentTransaction = fragmentManager.beginTransaction()
  if (addToBackStack){
      fragmentTransaction.replace(R.id.activity_main_fragment_container, currentFragment as Fragment)
          .addToBackStack(fragmentType.toString())
  }else{
      fragmentTransaction.replace(R.id.activity_main_fragment_container, currentFragment as Fragment)
  }


  try {
      fragmentTransaction.commit()
  }catch (e: Exception){
      e.printStackTrace()
  }

}
```

The setCurrentFragmentType method takes an argument of type MyFragmentType in order to traverse the fragment. In addition, if we want to send an argument (Bundle) while switching between fragments, we accept it as a parameter and if we want to add the relevant fragment to bacstack, we need to send the addToBackStack parameter as true. <br>

currentFragment and baseFragment are objects of type MyFragment. myFragmentStack is a Stack structure containing MyFragment.

```kotlin
private var currentFragment: MyFragment? = null
private var baseFragment: MyFragment? = null

private val myFragmentStack = Stack<MyFragment>()
```

myFragment is the class that determines the structure of all fragments.

```kotlin
abstract class MyFragment : Fragment() {

  abstract val fragmentType: MyFragmentType

}
```

Every fragment must have a type. The setCurrentFragmentType method determines the current fragment according to this type. The type here is specified with the enum class. There should be as many types as the number of fragments.

```kotlin
enum class MyFragmentType {
  HOME_FRAGMENT,
  MY_FRAGMENT_TYPE_A,
  MY_FRAGMENT_TYPE_B
}
```

### The scenario for displaying homeFragment is as above. Since the addToBackStack parameter is false in the initFragment, the homeFragment is not added to the backstack and the application exits when the back button is pressed.

Thanks to the buttons in homeFragment, transitions can be made between fragments. The OnFragmentInteraction interface runs the setCurrentFragmentType method in the fragment manager according to the command type.

<p align="center">
  <img src='https://github.com/ayhanunal/AndroidCustomFragmentManager/blob/main/images/fragment_transaction.gif' width=300 heihgt=300> 
  <img src='https://github.com/ayhanunal/AndroidCustomFragmentManager/blob/main/images/logcat_image.png' width=900 heihgt=500>
</p>

```kotlin
private var mListener: OnFragmentInteractionListener? = null

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val isUserPushed = arguments?.get("isUserPushedScreen")
    Log.e("TEST", "Fragment Home isUserPushed: ${isUserPushed}")

    binding.homeFragmentGotoFragmentA.setOnClickListener {
        mListener?.onFragmentInteraction(MyFragmentCommand.GO_TO_FRAGMENT_A, null, true)
    }

    binding.homeFragmentGotoFragmentB.setOnClickListener {
        mListener?.onFragmentInteraction(MyFragmentCommand.GO_TO_FRAGMENT_B, null, true)
    }
}
```

When the listener is triggered, the onFragmentInteraction method in MainActivity is called and fragment passage is provided.

```kotlin
override fun onFragmentInteraction(command: MyFragmentCommand, argumentData: Bundle?, addToBackStack: Boolean) {
  var argBundle: Bundle? = argumentData
  if (argBundle == null){
      argBundle = Bundle()
  }

  //default arguments
  argBundle.putBoolean("isUserPushedScreen", true)

  when(command){
      MyFragmentCommand.GO_TO_FRAGMENT_A -> {
          myFragmentManager.setCurrentFragmentType(MyFragmentType.MY_FRAGMENT_TYPE_A, argBundle, addToBackStack)
      }
      MyFragmentCommand.GO_TO_FRAGMENT_B -> {
          myFragmentManager.setCurrentFragmentType(MyFragmentType.MY_FRAGMENT_TYPE_B, argBundle, addToBackStack)
      }
  }
}
```











