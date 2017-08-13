# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.6)


# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

set(CMAKE_VERBOSE_MAKEFILE on)
set(libs "${CMAKE_SOURCE_DIR}/../jniLibs")
include_directories(${CMAKE_SOURCE_DIR}/openCV/include/include)

#--------------------------------------------------- import ---------------------------------------------------#
add_library(libopencv_java3 SHARED IMPORTED )
set_target_properties(libopencv_java3 PROPERTIES
    IMPORTED_LOCATION "${libs}/${ANDROID_ABI}/libopencv_java3.so")

add_library(libopencv_java SHARED IMPORTED )
set_target_properties(libopencv_java PROPERTIES
    IMPORTED_LOCATION "${libs}/${ANDROID_ABI}/libopencv_java.so")


set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11 -fexceptions -frtti")



add_library( # Sets the name of the library.
             opencv

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             ${CMAKE_SOURCE_DIR}/openCV/opencv.cpp )

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log)

target_link_libraries(opencv android log
    libopencv_java3 libopencv_java
    ${log-lib}
    )

