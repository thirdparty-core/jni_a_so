#继承上一层的CMakeLists.txt的变量 也可以在这里重新赋值

#设置头文件所在的位置,其中${CMAKE_SOURCE_DIR}代表当前CMakeLists.txt的目录
include_directories(${CMAKE_SOURCE_DIR}/hello/include)

#添加要编译的第三方库
add_library(libHello
            SHARED
            IMPORTED
)
#设置第三方库的属性
set_target_properties(
            libHello
            PROPERTIES IMPORTED_LOCATION
            ${CMAKE_SOURCE_DIR}/hello/include/${ANDROID_ABI}/libHello.a
)

#添加Android本地库
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

#创建一个静态库
add_library(
            hello
            SHARED
            #指定源文件的路径
             ${CMAKE_SOURCE_DIR}/hello/hello.cpp
)
target_link_libraries(
            hello
            libHello
            ${log-lib}
)

