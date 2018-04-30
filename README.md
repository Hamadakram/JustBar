# JustBar

![](https://github.com/Hamadakram/JustBar/blob/master/art/banner.gif?raw=true)
## Download
Grab via Gradle:
```java
implementation 'com.irozon.justbar:justbar:1.0.1'
```
## Usage
```java
    <com.irozon.justbar.JustBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <com.irozon.justbar.BarItem
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:icon="@drawable/ic_search"
            app:radius="25dp" />
        .
        .
        .

    </com.irozon.justbar.JustBar>
```
#### BarItem - Child for ` JustBar `:
Optional attributes for ` BarItem `
```java
    <com.irozon.justbar.BarItem
            android:id="@+id/barItem"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:selectedColor="@color/colorSelected"
            app:unSelectedColor="@color/colorUnselected"
            app:selectedIconColor="@color/colorIconSelected"
            app:unSelectedIconColor="@color/colorIconUnselected"
            app:selected="false"
            app:icon="@drawable/ic_search"
            app:radius="25dp" />
```
Attribute | Desription
--- | ---
`selectedColor` | Selected state color for the ` BarItem `
`unSelectedColor` | Unselected state color for the `BarItem`
`selectedIconColor` | Selected state color for the icon
`unSelectedIconColor` | Unselected state color for the icon
`selected` | Initial selected or unselected state for BarItem`
`icon` | Icon for `BarItem`
`radius` | Radius for the `BarItem`


#### Action for `BarItem`:
```java
    justBar.setOnBarItemClickListener(new OnBarItemClickListener() {
               @Override
               public void onBarItemClick(BarItem barItem, int position) {
                    // Your code here
               }
           });
```
## Authors

* **Hammad Akram** - (https://github.com/hamadakram)

## Contribution
Pull requests are welcome! Feel free to browse through open issues to look for things that need work. If you have a feature request or bug, please open a new issue so i can track it.

## Acknowledgments

* Built on [concept](https://www.uplabs.com/posts/function-bar-animation-asus-zenui-6-0-concept) by [Igor](https://www.uplabs.com/motionigor)

## Licence
```
Copyright 2018 Irozon, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
