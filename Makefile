#directories
SRC = src
BUILD = build

#compilers
JAVAC = javac
CC = gcc

#path to java include
JAVA_INCLUDE = /usr/lib/jvm/java-1.17.0-openjdk-amd64/include

SOURCES = \
		  $(SRC)/main/mainClass.java\
		  $(SRC)/main/utils.java\
		  $(SRC)/ter/terminal.java\
		  $(SRC)/gui/gui.java\
		  $(SRC)/gui/button.java\
		  $(SRC)/gui/buttonAction.java\
		  $(SRC)/csv/csvReader.java\
		  $(SRC)/app/app.java\
		  $(SRC)/data/customer.java\
		  $(SRC)/data/product.java\
		  $(SRC)/data/sale.java\
		  $(SRC)/data/dataUtils.java\

all: classes libs

launch:
	java -Djava.library.path=build/ter/ -cp build main/mainClass

classes: $(SOURCES)
	@mkdir -p $(BUILD)
	$(JAVAC) -d $(BUILD) $(SOURCES)

libs:
	$(CC) -shared -o build/ter/libterminal.so\
		-I"$(JAVA_INCLUDE)"\
		-I"$(JAVA_INCLUDE)/linux"\
		-I"$(JAVA_INCLUDE)/win32"\
		src/ter/terminalJava.c\
		src/ter/terminal.c

clean:
	rm -rf build/*
