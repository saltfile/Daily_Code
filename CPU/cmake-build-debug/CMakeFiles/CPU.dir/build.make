# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/CLion-2020.2.4/clion-2020.2.4/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /opt/CLion-2020.2.4/clion-2020.2.4/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /opt/Cpro/CPU

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /opt/Cpro/CPU/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/CPU.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/CPU.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/CPU.dir/flags.make

CMakeFiles/CPU.dir/main.cpp.o: CMakeFiles/CPU.dir/flags.make
CMakeFiles/CPU.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/opt/Cpro/CPU/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/CPU.dir/main.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CPU.dir/main.cpp.o -c /opt/Cpro/CPU/main.cpp

CMakeFiles/CPU.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CPU.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /opt/Cpro/CPU/main.cpp > CMakeFiles/CPU.dir/main.cpp.i

CMakeFiles/CPU.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CPU.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /opt/Cpro/CPU/main.cpp -o CMakeFiles/CPU.dir/main.cpp.s

CMakeFiles/CPU.dir/mem_fun.cpp.o: CMakeFiles/CPU.dir/flags.make
CMakeFiles/CPU.dir/mem_fun.cpp.o: ../mem_fun.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/opt/Cpro/CPU/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/CPU.dir/mem_fun.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CPU.dir/mem_fun.cpp.o -c /opt/Cpro/CPU/mem_fun.cpp

CMakeFiles/CPU.dir/mem_fun.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CPU.dir/mem_fun.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /opt/Cpro/CPU/mem_fun.cpp > CMakeFiles/CPU.dir/mem_fun.cpp.i

CMakeFiles/CPU.dir/mem_fun.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CPU.dir/mem_fun.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /opt/Cpro/CPU/mem_fun.cpp -o CMakeFiles/CPU.dir/mem_fun.cpp.s

CMakeFiles/CPU.dir/str_fun.cpp.o: CMakeFiles/CPU.dir/flags.make
CMakeFiles/CPU.dir/str_fun.cpp.o: ../str_fun.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/opt/Cpro/CPU/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/CPU.dir/str_fun.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CPU.dir/str_fun.cpp.o -c /opt/Cpro/CPU/str_fun.cpp

CMakeFiles/CPU.dir/str_fun.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CPU.dir/str_fun.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /opt/Cpro/CPU/str_fun.cpp > CMakeFiles/CPU.dir/str_fun.cpp.i

CMakeFiles/CPU.dir/str_fun.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CPU.dir/str_fun.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /opt/Cpro/CPU/str_fun.cpp -o CMakeFiles/CPU.dir/str_fun.cpp.s

CMakeFiles/CPU.dir/cpu_fun.cpp.o: CMakeFiles/CPU.dir/flags.make
CMakeFiles/CPU.dir/cpu_fun.cpp.o: ../cpu_fun.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/opt/Cpro/CPU/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/CPU.dir/cpu_fun.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CPU.dir/cpu_fun.cpp.o -c /opt/Cpro/CPU/cpu_fun.cpp

CMakeFiles/CPU.dir/cpu_fun.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CPU.dir/cpu_fun.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /opt/Cpro/CPU/cpu_fun.cpp > CMakeFiles/CPU.dir/cpu_fun.cpp.i

CMakeFiles/CPU.dir/cpu_fun.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CPU.dir/cpu_fun.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /opt/Cpro/CPU/cpu_fun.cpp -o CMakeFiles/CPU.dir/cpu_fun.cpp.s

# Object files for target CPU
CPU_OBJECTS = \
"CMakeFiles/CPU.dir/main.cpp.o" \
"CMakeFiles/CPU.dir/mem_fun.cpp.o" \
"CMakeFiles/CPU.dir/str_fun.cpp.o" \
"CMakeFiles/CPU.dir/cpu_fun.cpp.o"

# External object files for target CPU
CPU_EXTERNAL_OBJECTS =

CPU: CMakeFiles/CPU.dir/main.cpp.o
CPU: CMakeFiles/CPU.dir/mem_fun.cpp.o
CPU: CMakeFiles/CPU.dir/str_fun.cpp.o
CPU: CMakeFiles/CPU.dir/cpu_fun.cpp.o
CPU: CMakeFiles/CPU.dir/build.make
CPU: CMakeFiles/CPU.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/opt/Cpro/CPU/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Linking CXX executable CPU"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/CPU.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/CPU.dir/build: CPU

.PHONY : CMakeFiles/CPU.dir/build

CMakeFiles/CPU.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/CPU.dir/cmake_clean.cmake
.PHONY : CMakeFiles/CPU.dir/clean

CMakeFiles/CPU.dir/depend:
	cd /opt/Cpro/CPU/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /opt/Cpro/CPU /opt/Cpro/CPU /opt/Cpro/CPU/cmake-build-debug /opt/Cpro/CPU/cmake-build-debug /opt/Cpro/CPU/cmake-build-debug/CMakeFiles/CPU.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/CPU.dir/depend

