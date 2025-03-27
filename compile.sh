#!/bin/bash

# Define variables
SRC_DIR="src/main/java"
OUT_DIR="out"
JAR_FILE="uniquehands.jar"
MAIN_CLASS="edu.canisius.csc213.project1.UniqueHands"

# Clean previous build
echo "🧹 Cleaning previous build..."
# TODO You figure this out
rm -rf $OUT_DIR $JAR_FILE
mkdir $OUT_DIR

# Compile Java files
echo "🚀 Compiling Java files..."
# TODO You figure this out
javac -d $OUT_DIR $(find $SRC_DIR -name "*.java")

# Package into a JAR
echo "📦 Creating JAR file..."
# TODO You figure this out
jar cfe $JAR_FILE $MAIN_CLASS -C $OUT_DIR .


# Done!
echo "✅ Build complete! Run it with: java -jar $JAR_FILE"