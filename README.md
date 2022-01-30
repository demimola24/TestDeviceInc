# Test Device Dummy Application

This app shows a list of devices by simulating a network call (data mocked locally) and showing it on the screen using two view
1. Home screen - shows list of devices
2. Detail screen - shows details of a device clicked


The design approach was to make the application modular while using MVVM with Clean Architecture.


#### The app has following modules:
1. *app*
2. *core*
3. *data*
4. domain*


##### Components/Libraries used included:
1. Dagger hilt
2. Android Jetpack's Navigation component
3. Retrofit
4. Coroutine
5. Mockito