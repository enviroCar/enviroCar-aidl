# enviroCar-aidl
An AIDL interface for enviroCar which allows the access to the recorded measurement and raw OBD-II data of the enviroCar app by other apps.

Currently, the app supports the following features:

* Information about the OBD connection state
* Getting the latest raw OBD-II value for a specific phenomenon (e.g. Speed, RPM)
* Getting the latest recorded measurement (aggregated with different recorded phenomenons; usually refreshes all 5 seconds)



## Download
The enviroCar-aidl library can be integrated via a jitpack dependency. 

Add in your root build.gradle (preferred at the end of the repositories section) the following snippit:

```
   allprojects {
      repositories {
         ...
         maven { url 'https://jitpack.io' }
      }  
   }
```

and then add the following dependency:

```
   dependencies {
      implementation 'com.github.enviroCar:enviroCar-aidl:d67cfa49f3'
   }
```


License
=======

    Copyright 2019 enviroCar Community

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
