.SUFFIXES: .class .java

TARG=laba5
JC=javac -g
JM=java -ea -jar
JR=jar -cfe

OBJ=NonLinearEquation.class \
    laba5.class

.java.class:
	$(JC) $<

run: build
	$(JM) $(TARG).jar -100 100

build: 	$(OBJ)
	$(JR) $(TARG).jar laba5 $(OBJ)

clean:
	for %%f in ($(OBJ)) do del %%f
	del $(TARG).jar

NonLinearEquation.class: NonLinearEquation.java makefile

laba5.class: laba5.java makefile