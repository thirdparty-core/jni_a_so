#指定cmake的最小版本号
cmake_minimum_required(VERSION 3.6)

#设置最后生成so动态库的路径
#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

#添加子目录，将会调用子目录中的CMakeLists.txt
ADD_SUBDIRECTORY(hello)
ADD_SUBDIRECTORY(openCV)