# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.23

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

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/IDE/CLion-2022.2.4/clion-2022.2.4/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /opt/IDE/CLion-2022.2.4/clion-2022.2.4/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /opt/git_Pro/Daily_Code/untitled10

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /opt/git_Pro/Daily_Code/untitled10/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/untitled10.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/untitled10.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/untitled10.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/untitled10.dir/flags.make

CMakeFiles/untitled10.dir/main.cpp.o: CMakeFiles/untitled10.dir/flags.make
CMakeFiles/untitled10.dir/main.cpp.o: ../main.cpp
CMakeFiles/untitled10.dir/main.cpp.o: CMakeFiles/untitled10.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/opt/git_Pro/Daily_Code/untitled10/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/untitled10.dir/main.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/untitled10.dir/main.cpp.o -MF CMakeFiles/untitled10.dir/main.cpp.o.d -o CMakeFiles/untitled10.dir/main.cpp.o -c /opt/git_Pro/Daily_Code/untitled10/main.cpp

CMakeFiles/untitled10.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/untitled10.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /opt/git_Pro/Daily_Code/untitled10/main.cpp > CMakeFiles/untitled10.dir/main.cpp.i

CMakeFiles/untitled10.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/untitled10.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /opt/git_Pro/Daily_Code/untitled10/main.cpp -o CMakeFiles/untitled10.dir/main.cpp.s

CMakeFiles/untitled10.dir/fun.cpp.o: CMakeFiles/untitled10.dir/flags.make
CMakeFiles/untitled10.dir/fun.cpp.o: ../fun.cpp
CMakeFiles/untitled10.dir/fun.cpp.o: CMakeFiles/untitled10.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/opt/git_Pro/Daily_Code/untitled10/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/untitled10.dir/fun.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/untitled10.dir/fun.cpp.o -MF CMakeFiles/untitled10.dir/fun.cpp.o.d -o CMakeFiles/untitled10.dir/fun.cpp.o -c /opt/git_Pro/Daily_Code/untitled10/fun.cpp

CMakeFiles/untitled10.dir/fun.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/untitled10.dir/fun.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /opt/git_Pro/Daily_Code/untitled10/fun.cpp > CMakeFiles/untitled10.dir/fun.cpp.i

CMakeFiles/untitled10.dir/fun.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/untitled10.dir/fun.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /opt/git_Pro/Daily_Code/untitled10/fun.cpp -o CMakeFiles/untitled10.dir/fun.cpp.s

# Object files for target untitled10
untitled10_OBJECTS = \
"CMakeFiles/untitled10.dir/main.cpp.o" \
"CMakeFiles/untitled10.dir/fun.cpp.o"

# External object files for target untitled10
untitled10_EXTERNAL_OBJECTS =

untitled10: CMakeFiles/untitled10.dir/main.cpp.o
untitled10: CMakeFiles/untitled10.dir/fun.cpp.o
untitled10: CMakeFiles/untitled10.dir/build.make
untitled10: CMakeFiles/untitled10.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/opt/git_Pro/Daily_Code/untitled10/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable untitled10"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/untitled10.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/untitled10.dir/build: untitled10
.PHONY : CMakeFiles/untitled10.dir/build

CMakeFiles/untitled10.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/untitled10.dir/cmake_clean.cmake
.PHONY : CMakeFiles/untitled10.dir/clean

CMakeFiles/untitled10.dir/depend:
	cd /opt/git_Pro/Daily_Code/untitled10/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /opt/git_Pro/Daily_Code/untitled10 /opt/git_Pro/Daily_Code/untitled10 /opt/git_Pro/Daily_Code/untitled10/cmake-build-debug /opt/git_Pro/Daily_Code/untitled10/cmake-build-debug /opt/git_Pro/Daily_Code/untitled10/cmake-build-debug/CMakeFiles/untitled10.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/untitled10.dir/depend

